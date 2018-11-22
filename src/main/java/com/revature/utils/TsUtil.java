package com.revature.utils;

import java.sql.Timestamp;

public class TsUtil {

	public static Timestamp stampIt() {
		long unixTime = System.currentTimeMillis();
		Timestamp ts = new Timestamp(unixTime);		
		System.out.println(ts);
		return ts;
	}
}
