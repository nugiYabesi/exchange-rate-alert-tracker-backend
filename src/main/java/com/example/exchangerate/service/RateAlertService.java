package com.example.exchangerate.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangerate.entity.AlertStatus;
import com.example.exchangerate.entity.RateAlert;
import com.example.exchangerate.repository.RateAlertRepository;

@Service
public class RateAlertService {

    @Autowired
    private RateAlertRepository rateAlertRepository;

    // D1: safe saving
    public String save(RateAlert rateAlert) {
        // Rule 1: the date cannot be before today
        if (rateAlert.getExpiryDate().isBefore(LocalDate.now())) {
            return "Rate alert date cannot be in the past";
        }
        // Rule 2: the analyst must be free on that date (a DEACTIVATED alert does not count)
        boolean busy = rateAlertRepository.existsByAnalystAndExpiryDateAndStatusNot(
                rateAlert.getAnalyst(), rateAlert.getExpiryDate(), AlertStatus.DEACTIVATED);
        if (busy) {
            return "Analyst already has a rate alert expiring on that date";
        }
        // Rule 3: a new rate alert always starts as ACTIVE, whatever the JSON says
        rateAlert.setStatus(AlertStatus.ACTIVE);
        rateAlertRepository.save(rateAlert);
        return "Rate alert saved successfully";
    }

    // Return all rate alerts
    public List<RateAlert> getAll() {
        return rateAlertRepository.findAll();
    }

    // Return one rate alert, or an empty Optional if the id is unknown
    public Optional<RateAlert> getById(UUID id) {
        return rateAlertRepository.findById(id);
    }

    // Change the data of an existing rate alert (the status is NOT changed here)
    public String update(UUID id, RateAlert newData) {
        Optional<RateAlert> found = rateAlertRepository.findById(id);
        if (found.isEmpty()) {
            return "The rate alert with that id does not exist";
        }
        RateAlert rateAlert = found.get();
        rateAlert.setExpiryDate(newData.getExpiryDate());
        rateAlert.setCurrencyPair(newData.getCurrencyPair());
        rateAlert.setTargetRate(newData.getTargetRate());
        rateAlert.setSubscriber(newData.getSubscriber());
        rateAlert.setAnalyst(newData.getAnalyst());
        rateAlertRepository.save(rateAlert);
        return "Rate alert updated successfully";
    }

    // Delete a rate alert by id
    public String delete(UUID id) {
        if (!rateAlertRepository.existsById(id)) {
            return "The rate alert with that id does not exist";
        }
        rateAlertRepository.deleteById(id);
        return "Rate alert deleted successfully";
    }

    // B2: rate alerts in one status, earliest expiry date first
    public List<RateAlert> getByStatus(AlertStatus status) {
        return rateAlertRepository.findByStatusOrderByExpiryDateAsc(status);
    }

    // B3: rate alerts between two dates (both included)
    public List<RateAlert> getBetween(LocalDate start, LocalDate end) {
        return rateAlertRepository.findByExpiryDateBetweenOrderByExpiryDateAsc(start, end);
    }

    // D2: status flow
    public String updateStatus(UUID id, AlertStatus newStatus) {
        Optional<RateAlert> found = rateAlertRepository.findById(id);
        if (found.isEmpty()) {
            return "The rate alert with that id does not exist";
        }
        RateAlert rateAlert = found.get();
        AlertStatus current = rateAlert.getStatus();

        boolean allowed = false;
        if (current == AlertStatus.ACTIVE) {
            if (newStatus == AlertStatus.TRIGGERED || newStatus == AlertStatus.DEACTIVATED) {
                allowed = true;
            }
        }
        if (current == AlertStatus.TRIGGERED) {
            if (newStatus == AlertStatus.NOTIFIED || newStatus == AlertStatus.DEACTIVATED) {
                allowed = true;
            }
        }
        // NOTIFIED and DEACTIVATED are final, so they never become allowed

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        rateAlert.setStatus(newStatus);
        rateAlertRepository.save(rateAlert);
        return "Rate alert status updated successfully";
    }
}