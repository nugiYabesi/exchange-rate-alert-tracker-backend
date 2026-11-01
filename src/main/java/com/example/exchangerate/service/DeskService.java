package com.example.exchangerate.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangerate.entity.Desk;
import com.example.exchangerate.repository.DeskRepository;


@Service 
public class DeskService {

    @Autowired 
    private DeskRepository deskRepository;

    // save a new desk and return a message
   public String save(Desk desk){
    deskRepository.save(desk);
    return "Desk saved successfully";

}

// return all desks
public List<Desk> getAll() {
    return deskRepository.findAll();
}

// return one desk, or an empty optional if the id is unknown
public Optional<Desk> getById(UUID id) {
    return  deskRepository.findById(id);
}

// change the name of an existing desk
public String update(UUID id, Desk newData) {
    Optional<Desk> found = deskRepository.findById(id);
    if (found.isEmpty()) {
        return "The desk with that id does not exist";
    }
    Desk desk = found.get();
    desk.setName(newData.getName());
    deskRepository.save(desk);
    return "desk updated successfully";
}

// delete a desk by id
    // Delete a desk by id
    public String delete(UUID id) {
        if (!deskRepository.existsById(id)) {
            return "The desk with that id does not exist";
        }
        deskRepository.deleteById(id);
        return "Desk deleted successfully";
    }

}
