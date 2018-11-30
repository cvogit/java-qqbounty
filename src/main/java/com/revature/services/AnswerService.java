package com.revature.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.dto.AnswerDto;
import com.revature.models.Answer;
import com.revature.models.User;
import com.revature.models.Vote;
import com.revature.repos.AnswerRepo;
import com.revature.repos.UserRepo;
import com.revature.util.ResponseMap;
import com.revature.util.TsUtil;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepo answerRepo;

	@Autowired
	private VoteService voteService;

	@Autowired
	private UserRepo userRepo;

	public Map<String, Object> findAll() {
		List<Answer> answerList = answerRepo.findAll();

		return ResponseMap.getNewMap("answers", getAnswerDto(answerList));
	}

	public Map<String, Object> findById(int id) {
		Answer answer = answerRepo.getOne(id);
		return ResponseMap.getNewMap("answers", getAnswerDto(answer));
	}

	public Map<String, Object> findByBountyId(int id) {
		List<Answer> answerList = answerRepo.findByBountyId(id);
		return ResponseMap.getNewMap("answers", getAnswerDto(answerList));
	}

	public Map<String, Object> findByBountyId(int id, Pageable page) {
		Page<Answer> answerList = answerRepo.findByBountyId(id, page);
		return ResponseMap.getNewMap("answers", getAnswerDto(answerList, page));
	}

	public Map<String, Object> save(Answer answer) {
		answer.setStatusId(1);
		answer.setVotes(0);
		answer.setSubmitted(TsUtil.stampIt());

		Answer updatedAnswer = answerRepo.save(answer);
		return ResponseMap.getNewMap("answers", getAnswerDto(updatedAnswer));
	}

//	public  Map<String,Object>  update(Answer answer) {
//		Answer updatedAnswer = answerRepo.save(answer);
//		return ResponseMap.getNewMap("answer",updatedAnswer);
//	}

	public Map<String, Object> updateVote(int answerId, int userId, int voteValue) {
		Answer answer = answerRepo.getOne(answerId);
		List<Vote> votes = voteService.findByAnswerIdAndUserId(answerId, userId);

		if (votes.size() >= 1) {
			System.out.println("User: " + userId + "already voted for answer: " + answerId);
			return ResponseMap.getNewMap("vote_status", false);
		}

		if (voteValue == 1 || voteValue == -1) {
			answer.setVotes(answer.getVotes() + voteValue);
			answerRepo.save(answer);
			voteService.save(new Vote(0, userId, answerId));
			return ResponseMap.getNewMap("vote_status", true);
		}
		System.out.println("voteValue not -1 or 1, voteValue is:" + voteValue);
		return ResponseMap.getNewMap("vote_status", false);
	}

	private AnswerDto getAnswerDto(Answer answer) {
		Integer id = answer.getUserId();
		User username = userRepo.getOne(id);
		return new AnswerDto(answer, username.getUsername());
	}

	private List<AnswerDto> getAnswerDto(List<Answer> answerList) {
		List<Integer> idList = answerList.stream().map(Answer::getAnswerId).collect(Collectors.toList());
		List<String> usernames = userRepo.findUsernames(idList);
		Iterator<Answer> answerIterator = answerList.iterator();
		Iterator<String> usernameIterator = usernames.iterator();
		List<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
		while (answerIterator.hasNext() && usernameIterator.hasNext()) {
			answerDtoList.add(new AnswerDto(answerIterator.next(), usernameIterator.next()));
		}
		return answerDtoList;
	}

	private Page<AnswerDto> getAnswerDto(Page<Answer> pageAnswerList, Pageable page) {
		List<Answer> answerList = pageAnswerList.getContent();
		List<AnswerDto> answerDtoList = getAnswerDto(answerList);
		int start = (int) page.getOffset();
		int end = (start + page.getPageSize()) > answerDtoList.size() ? answerDtoList.size()
				: (start + page.getPageSize());
		Page<AnswerDto> pages = new PageImpl<AnswerDto>(answerDtoList.subList(start, end), page, answerDtoList.size());
		return pages;
	}

}
