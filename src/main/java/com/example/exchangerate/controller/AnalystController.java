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

import com.example.exchangerate.entity.Analyst;
import com.example.exchangerate.service.AnalystService;

@RestController
@RequestMapping("/api/analysts")
public class AnalystController {

    @Autowired
    private AnalystService analystService;

    // POST /api/analysts/save
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Analyst analyst) {
        String message = analystService.save(analyst);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // GET /api/analysts/all
    @GetMapping("/all")
    public ResponseEntity<List<Analyst>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(analystService.getAll());
    }

    // GET /api/analysts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Analyst> found = analystService.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The analyst with that id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }
    }

    // PUT /api/analysts/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Analyst analyst) {
        String message = analystService.update(id, analyst);
        if (message.equals("The analyst with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    // DELETE /api/analysts/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = analystService.delete(id);
        if (message.equals("The analyst with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
}