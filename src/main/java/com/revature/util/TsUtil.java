package com.revature.util;

import java.sql.Timestamp;

public class TsUtil {

	public static Timestamp stampIt() {
		long unixTime = System.currentTimeMillis();
		Timestamp ts = new Timestamp(unixTime);		
		System.out.println(ts);
		return ts;
	}
	
	
	public static Timestamp expirationStampIt(int hours) {
		long unixTime = System.currentTimeMillis();
		long millseconds = hours * 3600000;
		Timestamp ts = new Timestamp(unixTime+millseconds);		
		System.out.println(ts);
		return ts;
	}
}
