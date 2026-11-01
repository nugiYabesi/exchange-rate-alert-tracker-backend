package com.example.exchangerate.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangerate.entity.AlertStatus;
import com.example.exchangerate.repository.AlertNotificationRepository;
import com.example.exchangerate.repository.AnalystRepository;
import com.example.exchangerate.repository.RateAlertRepository;
import com.example.exchangerate.repository.SubscriberRepository;

@Service
public class DashboardService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private AnalystRepository analystRepository;

    @Autowired
    private RateAlertRepository rateAlertRepository;

    @Autowired
    private AlertNotificationRepository alertNotificationRepository;

    // We reuse the C2 method of DeskService to count the free desks
    @Autowired
    private DeskService deskService;

    public Map<String, Long> getSummary() {
        // LinkedHashMap keeps the keys in the order we put them
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("subscribers", subscriberRepository.count());
        summary.put("analysts", analystRepository.count());
        summary.put("freeDesks", (long) deskService.getFree().size());
        summary.put("alertsExpiringToday", rateAlertRepository.countByExpiryDate(LocalDate.now()));
        summary.put("active", rateAlertRepository.countByStatus(AlertStatus.ACTIVE));
        summary.put("alertNotifications", alertNotificationRepository.count());
        return summary;
    }
}