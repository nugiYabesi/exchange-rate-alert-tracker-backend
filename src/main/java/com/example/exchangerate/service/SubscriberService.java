package com.example.exchangerate.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.exchangerate.entity.Subscriber;
import com.example.exchangerate.repository.SubscriberRepository;


@Service 
public class SubscriberService {

    @Autowired 
    private SubscriberRepository subscriberRepository;

    // save a new Subscriber and return a message
   public String save(Subscriber subscriber){
    subscriberRepository.save(subscriber);
    return "subscriber saved successfully";

}

// return all Subscribers
public List<Subscriber> getAll() {
    return subscriberRepository.findAll();
}

// return one subscriber, or an empty optional if the id is unknown
public Optional<Subscriber> getById(UUID id) {
    return  subscriberRepository.findById(id);
}

// change the firstname , last name , subsciber code, email, country of an existing subscriber
public String update(UUID id, Subscriber newData) {
    Optional<Subscriber> found = subscriberRepository.findById(id);
    if (found.isEmpty()) {
        return "The Subscriber with that id does not exist";
    }
    Subscriber subscriber = found.get();
    subscriber.setFirstName(newData.getFirstName());
    subscriber.setLastName(newData.getlastName());
    subscriber.setSubscriberCode(newData.getSubscriberCode());
    subscriber.setEmail(newData.getEmail());
    subscriber.setCountry(newData.getCountry());
    subscriberRepository.save(subscriber);
    return "Subscriber updated successfully";
}

// delete a subsciber by id
public String delete(UUID id) {
    if (!subscriberRepository.existsById(id)) {
        return "The subscriber with that id does ot exist";
    }

subscriberRepository.deleteById(id);
return "Subscriber deleted successfully";
}

    // B1: subscribers of a country, sorted by last name
    public List<Subscriber> getByCountry(String country) {
        return subscriberRepository.findByCountryIgnoreCaseOrderByLastNameAsc(country);
    }

        // C4: subscribers who have at least one rate alert with this analyst
    public List<Subscriber> getOfAnalyst(UUID analystId) {
        return subscriberRepository.findSubscribersOfAnalyst(analystId);
    }
}