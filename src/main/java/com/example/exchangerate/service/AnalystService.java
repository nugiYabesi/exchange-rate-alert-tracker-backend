package com.example.exchangerate.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.exchangerate.entity.Analyst;
import com.example.exchangerate.repository.AnalystRepository;


@Service 
public class AnalystService {

    @Autowired 
    private AnalystRepository analystRepository;

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
}