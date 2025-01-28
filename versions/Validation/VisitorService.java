package com.tpp.tpplab3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpp.tpplab3.models.Visitors;
import com.tpp.tpplab3.models.repository.VisitorsRepository;

@Service
public class VisitorService {

    @Autowired
    private VisitorsRepository visitorRepository;

    public List<Visitors> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Optional<Visitors> findById(int id) {
        return visitorRepository.findById(id);
    }

    public void addVisitor(Visitors visitor) {
        visitorRepository.save(visitor);
    }

    public void updateVisitor(Visitors updatedVisitor) {
        Visitors existingVisitor = visitorRepository.findById(updatedVisitor.getVisitorId())
                .orElseThrow(() -> new IllegalArgumentException("Відвідувача не знайдено"));

        existingVisitor.setName(updatedVisitor.getName());
        existingVisitor.setSurname(updatedVisitor.getSurname());
        existingVisitor.setPhone(updatedVisitor.getPhone());
        existingVisitor.setEmail(updatedVisitor.getEmail());

        existingVisitor.setOrders(updatedVisitor.getOrders());

        visitorRepository.save(existingVisitor);
    }

    public void deleteVisitor(int id) {
        visitorRepository.deleteById(id);
    }
}
