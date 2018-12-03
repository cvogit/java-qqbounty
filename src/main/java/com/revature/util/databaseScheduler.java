
package com.revature.util;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.controllers.BountyController;

@Component
public class databaseScheduler {


    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
    	bc.checkBounties();
    }
    
    @Autowired
    BountyController bc;
    
    
    
}