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

import com.example.exchangerate.entity.Desk;
import com.example.exchangerate.service.DeskService;

@RestController
@RequestMapping("/api/desks")
public class DeskController {

    @Autowired
    private DeskService deskService;

    // POST /api/desks/save
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Desk desk) {
        String message = deskService.save(desk);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // GET /api/desks/all
    @GetMapping("/all")
    public ResponseEntity<List<Desk>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(deskService.getAll());
    }

    // GET /api/desks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Desk> found = deskService.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The desk with that id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }
    }

    // PUT /api/desks/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Desk desk) {
        String message = deskService.update(id, desk);
        if (message.equals("The desk with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    // DELETE /api/desks/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = deskService.delete(id);
        if (message.equals("The desk with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
}