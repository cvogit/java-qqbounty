package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.revature.model.Bounty;

public class QQBountyApiApplicationTests {

	//demo test case for bounty model
	@Test
	public void bountyTest()  {
	        //  create mock
	        Bounty test = mock(Bounty.class);

	        // define return value for method getUniqueId()
	        when(test.getBounty_id()).thenReturn(42);

	        // use mock in test....
	        assertEquals(test.getBounty_id(), 42);
	}

}