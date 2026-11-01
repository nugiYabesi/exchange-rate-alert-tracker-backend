package com.example.exchangerate.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangerate.entity.AlertNotification;
import com.example.exchangerate.service.AlertNotificationService;

@RestController
@RequestMapping("/api/alert-notifications")
public class AlertNotificationController {

    @Autowired
    private AlertNotificationService alertNotificationService;

    // POST /api/alert-notifications/save?rateAlertId=...  (E1)
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID rateAlertId,
            @RequestBody AlertNotification alertNotification) {
        String message = alertNotificationService.save(rateAlertId, alertNotification);
        if (message.equals("The rate alert with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals("Alert notifications are only for NOTIFIED rate alerts")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
    }

    // GET /api/alert-notifications/all
    @GetMapping("/all")
    public ResponseEntity<List<AlertNotification>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(alertNotificationService.getAll());
    }

    // GET /api/alert-notifications/subscriber/{subscriberCode}  (E2)
    @GetMapping("/subscriber/{subscriberCode}")
    public ResponseEntity<List<AlertNotification>> getHistory(@PathVariable String subscriberCode) {
        return ResponseEntity.status(HttpStatus.OK).body(alertNotificationService.getHistory(subscriberCode));
    }

    // DELETE /api/alert-notifications/delete/{id}  (E3)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = alertNotificationService.delete(id);
        if (message.equals("The alert notification with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
}