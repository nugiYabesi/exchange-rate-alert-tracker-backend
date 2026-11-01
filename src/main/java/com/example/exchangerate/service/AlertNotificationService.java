package com.example.exchangerate.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangerate.entity.AlertNotification;
import com.example.exchangerate.entity.AlertStatus;
import com.example.exchangerate.entity.RateAlert;
import com.example.exchangerate.repository.AlertNotificationRepository;
import com.example.exchangerate.repository.RateAlertRepository;

@Service
public class AlertNotificationService {

    @Autowired
    private AlertNotificationRepository alertNotificationRepository;

    @Autowired
    private RateAlertRepository rateAlertRepository;

    // E1: write an alert notification for a NOTIFIED rate alert
    public String save(UUID rateAlertId, AlertNotification alertNotification) {
        Optional<RateAlert> found = rateAlertRepository.findById(rateAlertId);
        if (found.isEmpty()) {
            return "The rate alert with that id does not exist";
        }
        RateAlert rateAlert = found.get();
        if (rateAlert.getStatus() != AlertStatus.NOTIFIED) {
            return "Alert notifications are only for NOTIFIED rate alerts";
        }
        // The service (not the client) sets the date and the link to the rate alert
        alertNotification.setSentDate(LocalDate.now());
        alertNotification.setRateAlert(rateAlert);
        alertNotificationRepository.save(alertNotification);
        return "Alert notification saved successfully";
    }

    // Return all alert notifications (handy to find ids for testing)
    public List<AlertNotification> getAll() {
        return alertNotificationRepository.findAll();
    }

    // E2: history of a subscriber, newest first
    public List<AlertNotification> getHistory(String subscriberCode) {
        return alertNotificationRepository.findHistoryBySubscriberCode(subscriberCode);
    }

    // E3: delete an alert notification
    public String delete(UUID id) {
        if (!alertNotificationRepository.existsById(id)) {
            return "The alert notification with that id does not exist";
        }
        alertNotificationRepository.deleteById(id);
        return "Alert notification deleted successfully";
    }
}