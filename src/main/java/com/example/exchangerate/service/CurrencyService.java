package com.example.exchangerate.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangerate.entity.Currency;
import com.example.exchangerate.repository.CurrencyRepository;


@Service 
public class CurrencyService {

    @Autowired 
    private CurrencyRepository currencyRepository;

    

    // save a new currency and return a message
   public String save(Currency currency){
    currencyRepository.save(currency);
    return "Currency saved successfully";

}

// return all curencies
public List<Currency> getAll() {
    return currencyRepository.findAll();
}

// return one currency, or an empty optional if the id is unknown
public Optional<Currency> getById(UUID id) {
    return  currencyRepository.findById(id);
}

// change the name of an existing currency
public String update(UUID id, Currency newData) {
    Optional<Currency> found = currencyRepository.findById(id);
    if (found.isEmpty()) {
        return "The currency with that id does not exist";
    }
    Currency currency = found.get();
    currency.setName(newData.getName());
    currencyRepository.save(currency);
    return "Currency updated successfully";
}


// delete a currency by id
public String delete(UUID id) {
    if (!currencyRepository.existsById(id)) {
        return "The currency with that id does not exist";
    }
    currencyRepository.deleteById(id);
    return "Currency deleted successfully";
}
}