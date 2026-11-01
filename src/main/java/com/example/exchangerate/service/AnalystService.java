package com.example.exchangerate.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.exchangerate.entity.Analyst;
import com.example.exchangerate.entity.Desk;
import com.example.exchangerate.entity.Currency;
import com.example.exchangerate.repository.AnalystRepository;
import com.example.exchangerate.repository.CurrencyRepository;
import com.example.exchangerate.repository.DeskRepository;


@Service 
public class AnalystService {

    @Autowired 
    private AnalystRepository analystRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private DeskRepository deskRepository;

    // save a new analyst and return a message
   public String save(Analyst analyst){
    analystRepository.save(analyst);
    return "analyst saved successfully";

}

// return all analysts
public List<Analyst> getAll() {
    return analystRepository.findAll();
}

// return one subscriber, or an empty optional if the id is unknown
public Optional<Analyst> getById(UUID id) {
    return  analystRepository.findById(id);
}

// change the name, the email, and the desk of an existing analyst
public String update(UUID id, Analyst newData) {
    Optional<Analyst> found = analystRepository.findById(id);
    if (found.isEmpty()) {
        return "The analyst with that id does not exist";
    }
    Analyst analyst = found.get();
    analyst.setFirstName(newData.getFirstName());
    analyst.setLastName(newData.getLastName());
    analyst.setEmail(newData.getEmail());
    analyst.setDesk(newData.getDesk());
    analystRepository.save(analyst);
    return "analyst updated successfully";
}

// delete an analyst by id
public String delete(UUID id) {
    if (!analystRepository.existsById(id)) {
        return "The analyst with that id does not exist";
    }

analystRepository.deleteById(id);
return "Subscriber deleted successfully";
}

    // C1: analysts who hold a currency
    public List<Analyst> getByCurrency(String name) {
        return analystRepository.searchByCurrencyName(name);
    }

    // D3: add one currency to an analyst, keeping the ones already there
    public String addCurrency(UUID analystId, UUID currencyId) {
        Optional<Analyst> foundAnalyst = analystRepository.findById(analystId);
        if (foundAnalyst.isEmpty()) {
            return "The analyst with that id does not exist";
        }
        Optional<Currency> foundCurrency = currencyRepository.findById(currencyId);
        if (foundCurrency.isEmpty()) {
            return "That currency does not exist";
        }
        Analyst analyst = foundAnalyst.get();
        Currency currency = foundCurrency.get();

        if (analyst.getCurrencies().contains(currency)) {
            return "The analyst already has that currency";
        }
        // Analyst owns the link (it has @JoinTable), so we change its list and save it
        analyst.getCurrencies().add(currency);
        analystRepository.save(analyst);
        return "Currency added to the analyst";
    }

    // D4: move an analyst to another desk
    public String moveToDesk(UUID analystId, int deskNumber) {
        Optional<Analyst> foundAnalyst = analystRepository.findById(analystId);
        if (foundAnalyst.isEmpty()) {
            return "The analyst with that id does not exist";
        }
        Optional<Desk> foundDesk = deskRepository.findByDeskNumber(deskNumber);
        if (foundDesk.isEmpty()) {
            return "The desk with that deskNumber does not exist";
        }
        Analyst analyst = foundAnalyst.get();
        Desk desk = foundDesk.get();

        // If the analyst already sits at this desk, there is nothing to change
        if (analyst.getDesk() != null && analyst.getDesk().getId().equals(desk.getId())) {
            return "Desk changed successfully";
        }
        if (analystRepository.existsByDesk(desk)) {
            return "That desk is taken by another analyst";
        }
        analyst.setDesk(desk);
        analystRepository.save(analyst);
        return "Desk changed successfully";
    }
}