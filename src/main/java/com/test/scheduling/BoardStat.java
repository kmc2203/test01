/** 주기적인 작업 실행하는 클래스 */

package com.test.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BoardStat {
    // 초(0 - 59) 분(0 - 59) 시(0 - 23) 일(1 - 31) 월(1 - 12) 요일(0 - 6, 0 = 일요일)
    @Scheduled(cron = "0 0 1 * * *") // 매일 새벽 1시에 실행
    public void makeStat() {
        System.out.println("starts every 01:00AM");
    }

}
