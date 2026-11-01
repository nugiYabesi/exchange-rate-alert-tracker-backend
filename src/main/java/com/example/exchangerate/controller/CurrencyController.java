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

import com.example.exchangerate.entity.Currency;
import com.example.exchangerate.service.CurrencyService;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

  @Autowired 
  private CurrencyService currencyService; 

// POST /api/currencies/save
  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Currency currency) {
    String message = currencyService.save(currency);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // GET /api/currencies/all
    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(currencyService.getAll());
    }

    // GET /api/currencies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Currency> found = currencyService.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The currency with that id does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(found.get());
        }
    }

    // PUT /api/currencies/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Currency currency) {
        String message = currencyService.update(id, currency);
        if (message.equals("The currency with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    // DELETE /api/currencies/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = currencyService.delete(id);
        if (message.equals("The currency with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
}

  