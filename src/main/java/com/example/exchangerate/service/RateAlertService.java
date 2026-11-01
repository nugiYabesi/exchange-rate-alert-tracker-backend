package com.example.exchangerate.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.exchangerate.entity.RateAlert;
import com.example.exchangerate.repository.RateAlertRepository;


@Service 
public class RateAlertService {

    @Autowired 
    private RateAlertRepository rateAlertRepository;

    // save a new rate alert and return a message
   public String save(RateAlert rateAlert){
    rateAlertRepository.save(rateAlert);
    return "rate alert saved successfully";

}

// return all rate alerts
public List<RateAlert> getAll() {
    return rateAlertRepository.findAll();
}

// return one rate alert, or an empty optional if the id is unknown
public Optional<RateAlert> getById(UUID id) {
    return  rateAlertRepository.findById(id);
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
    return "rate alert  updated successfully";
}

// delete a rate alert by id
public String delete(UUID id) {
    if (!rateAlertRepository.existsById(id)) {
        return "The rate alert with that id does not exist";
    }

rateAlertRepository.deleteById(id);
return "rate alert deleted successfully";
}
}