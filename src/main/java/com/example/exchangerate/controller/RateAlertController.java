package com.example.exchangerate.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.exchangerate.entity.AlertStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangerate.entity.RateAlert;
import com.example.exchangerate.service.RateAlertService;

@RestController
@RequestMapping("/api/rate-alerts")
public class RateAlertController {

    @Autowired
    private RateAlertService rateAlertService;

    // POST /api/rate-alerts/save
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody RateAlert rateAlert) {
        String message = rateAlertService.save(rateAlert);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // GET /api/rate-alerts/all
    @GetMapping("/all")
    public ResponseEntity<List<RateAlert>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(rateAlertService.getAll());
    }

    // GET /api/rate-alerts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<RateAlert> found = rateAlertService.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The rate alert with that id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }
    }

    // PUT /api/rate-alerts/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody RateAlert rateAlert) {
        String message = rateAlertService.update(id, rateAlert);
        if (message.equals("The rate alert with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

        // DELETE /api/rate-alerts/delete/{id}  (E3: 409 when it has alert notifications)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = rateAlertService.delete(id);
        if (message.equals("The rate alert with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals("That rate alert has alert notifications, delete them first")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
        // GET /api/rate-alerts/by-status?status=ACTIVE
    @GetMapping("/by-status")
    public ResponseEntity<List<RateAlert>> getByStatus(@RequestParam AlertStatus status) {
        return ResponseEntity.status(HttpStatus.OK).body(rateAlertService.getByStatus(status));
    }

    // GET /api/rate-alerts/between?start=2026-10-01&end=2026-10-31
    @GetMapping("/between")
    public ResponseEntity<List<RateAlert>> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ResponseEntity.status(HttpStatus.OK).body(rateAlertService.getBetween(startDate, endDate));
    }

    // PATCH /api/rate-alerts/{id}/status?status=TRIGGERED  (D2: status flow)
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam AlertStatus status) {
        String message = rateAlertService.updateStatus(id, status);
        if (message.equals("The rate alert with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.startsWith("Cannot change status")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

        // GET /api/rate-alerts/stats/by-status  (F1)
    @GetMapping("/stats/by-status")
    public ResponseEntity<List<Object[]>> getStatsByStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(rateAlertService.getStatsByStatus());
    }
}