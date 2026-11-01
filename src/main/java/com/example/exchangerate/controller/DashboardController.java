package com.example.exchangerate.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangerate.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // GET /api/dashboard/summary
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Long>> getSummary() {
        return ResponseEntity.status(HttpStatus.OK).body(dashboardService.getSummary());
    }
}