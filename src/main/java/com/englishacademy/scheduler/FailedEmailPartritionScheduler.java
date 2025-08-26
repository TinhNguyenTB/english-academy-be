package com.englishacademy.scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FailedEmailPartritionScheduler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "*/30 * * * * ?")
    public void createNextMonthPartition() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        LocalDate monthAfter = nextMonth.plusMonths(1);

        String partitionName = String.format("failed_mail_%d_%02d", nextMonth.getYear(), nextMonth.getMonthValue());
        String from = nextMonth.format(DateTimeFormatter.ISO_DATE);
        String to = monthAfter.format(DateTimeFormatter.ISO_DATE);

        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s PARTITION OF failed_mail FOR VALUES FROM ('%s') TO ('%s');",
                partitionName, from, to
        );

        jdbcTemplate.execute(sql);
    }
}