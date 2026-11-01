package com.example.exchangerate.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exchangerate.entity.Subscriber;
import com.example.exchangerate.service.SubscriberService;
import com.example.exchangerate.service.AnalystService;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

        @Autowired
        private AnalystService analystService;

    // POST /api/subscribers/save
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Subscriber subscriber) {
        String message = subscriberService.save(subscriber);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // GET /api/subscribers/all
    @GetMapping("/all")
    public ResponseEntity<List<Subscriber>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subscriberService.getAll());
    }

    // GET /api/subscribers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Subscriber> found = subscriberService.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The subscriber with that id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }
    }

    // PUT /api/subscribers/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Subscriber subscriber) {
        String message = subscriberService.update(id, subscriber);
        if (message.equals("The subscriber with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    // DELETE /api/subscribers/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = subscriberService.delete(id);
        if (message.equals("The subscriber with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
        // GET /api/subscribers/by-country?country=rwanda
    @GetMapping("/by-country")
    public ResponseEntity<List<Subscriber>> getByCountry(@RequestParam String country) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriberService.getByCountry(country));
    }


        // GET /api/subscribers/of-analyst/{analystId}
    @GetMapping("/of-analyst/{analystId}")
    public ResponseEntity<?> getOfAnalyst(@PathVariable UUID analystId) {
        if (analystService.getById(analystId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The analyst with that id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(subscriberService.getOfAnalyst(analystId));
        }
    }
}