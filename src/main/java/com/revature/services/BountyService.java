package com.revature.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.dto.AnswerDto;
import com.revature.dto.BountyDto;
import com.revature.models.Answer;
import com.revature.models.Bounty;
import com.revature.models.User;
import com.revature.repos.BountyRepo;
import com.revature.repos.SubjectRepo;
import com.revature.repos.SubjectToBountyRepo;
import com.revature.repos.UserRepo;
import com.revature.util.ResponseMap;
import com.revature.util.TsUtil;

@Service
public class BountyService {

	@Autowired
	private BountyRepo bountyRepo;

	@Autowired
	private SubjectRepo subjectRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SubjectToBountyRepo subjectToBountyRepo;

	public Map<String, Object> findAll(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list", getBountyDto(bountyRepo.findAll(pageable), pageable));
	}

	public Map<String, Object> findAllByOrderByVotes(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list",
				getBountyDto(bountyRepo.findAllByOrderByVotes(pageable,TsUtil.stampIt()), pageable));
	}

	public Map<String, Object> findAllByOrderByAmount(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list",
				getBountyDto(bountyRepo.findAllByOrderByAmountDesc(pageable), pageable));
	}

	public Map<String, Object> findAllByOrderByNewest(Pageable pageable) {
		return ResponseMap.getNewMap("bounty_list",
				getBountyDto(bountyRepo.findAllByOrderBySubmittedDesc(pageable), pageable));
	}

	public Map<String, Object> findAllBySubjectTag(Pageable pageable, List<String> subjectsList) {
		List<Integer> subjectIds = subjectRepo.getListSubjectId(subjectsList);
		List<Integer> bountyIds = subjectToBountyRepo.getListBountyIds(subjectIds);
		return ResponseMap.getNewMap("bounty_list",
				getBountyDto(bountyRepo.findByBountyIdIn(pageable, bountyIds), pageable));
	}

	/*
	 * EXAMPLE SAVE/UPDATE REQUEST { "amount": 100, will be filled in by user
	 * "bountyId": 0, //automatically set "description":
	 * "testing wallet subtraction", user entry "picture": "", user upload
	 * "subject": [ { "subjectId": 1 user entry } ], "timer": 7000000, millseconds
	 * enter by user based on Date.now - future date }
	 */
	// user id will be set in controller when verifying jwt
	// set Timestamp upon new bounty creation

	public Map<String, Object> save(Bounty bounty) {
		bounty.setSubmitted(TsUtil.stampIt());
		bounty.setStatusId(1);
		bounty.setCorrectAnswerId(null);
		bounty.setVotes(0);
		return ResponseMap.getNewMap("bounty", getBountyDto(bountyRepo.save(bounty)));
	}
	
	public Map<String, Object> update(Bounty bounty) {
		return ResponseMap.getNewMap("bounty", getBountyDto(bountyRepo.save(bounty)));
	}
	

	// Helper Methods for other Controllers/Services

	public Bounty findById(int id) {
		return bountyRepo.getOne(id);
	}

	private BountyDto getBountyDto(Bounty bounty) {
		if (bounty == null) {
			return null;
		}
		Integer id = bounty.getUserId();
		User username = userRepo.getOne(id);
		return new BountyDto(bounty, username.getUsername());
	}

	private List<BountyDto> getBountyDto(List<Bounty> bountyList) {
		if (bountyList.size() == 0) {
			return new ArrayList<BountyDto>();
		}
		Set<Integer> idSet = bountyList.stream().map(Bounty::getUserId).collect(Collectors.toSet());
		List<Integer> idList = new ArrayList<Integer>(idSet);
		System.out.println(idList.toString());
		List<String> usernames = userRepo.findUsernames(idList);
		System.out.println(usernames.toString());

		Map<Integer, String> map = new LinkedHashMap<Integer, String>(); // ordered

		for (int i = 0; i < idList.size(); i++) {
			map.put(idList.get(i), usernames.get(i)); // is there a clearer way?
		}

		Iterator<Bounty> bountyIterator = bountyList.iterator();
		List<BountyDto> bountyDtoList = new ArrayList<BountyDto>();
		while (bountyIterator.hasNext()) {
			Bounty bounty = bountyIterator.next();
			bountyDtoList.add(new BountyDto(bounty, map.get(bounty.getUserId())));
		}
		return bountyDtoList;
	}

	private Page<BountyDto> getBountyDto(Page<Bounty> pageBountyList, Pageable page) {
		List<Bounty> bountyList = pageBountyList.getContent();
		List<BountyDto> bountyDtoList = getBountyDto(bountyList);
		int start = (int) page.getOffset();
		int end = (start + page.getPageSize()) > bountyDtoList.size() ? bountyDtoList.size()
				: (start + page.getPageSize());
		Page<BountyDto> pages = new PageImpl<BountyDto>(bountyDtoList.subList(start, end), page, bountyDtoList.size());
		return pages;
	}



}
