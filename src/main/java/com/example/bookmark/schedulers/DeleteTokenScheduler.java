package com.example.bookmark.schedulers;

import com.example.bookmark.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;

@EnableScheduling
@Component
public class DeleteTokenScheduler {

    @Autowired TokenService tokenService;

    @Scheduled(fixedDelay = 1000L)
    public void deleteOldTokens() {
        System.out.println("Token deletion scheduler started at : " + new Date(System.currentTimeMillis()));
        tokenService.deleteOldTokens();
    }
}
