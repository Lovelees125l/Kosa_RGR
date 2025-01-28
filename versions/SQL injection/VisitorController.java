package com.tpp.tpplab3.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpp.tpplab3.models.Visitors;
import com.tpp.tpplab3.service.VisitorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public String listVisitors(Model model) {
        model.addAttribute("visitors", visitorService.getAllVisitors());
        return "visitors";
    }

    @GetMapping("/add")
    public String addVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitors());
        return "add-visitor";
    }

    @PostMapping("/add")
    public String addVisitor(@Valid @ModelAttribute("visitor") Visitors visitor, BindingResult result) {
        if (result.hasErrors()) {
            return "add-visitor";
        }
        visitorService.addVisitor(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/edit/{id}")
    public String editVisitorForm(@PathVariable("id") int id, Model model) {
        Visitors visitor = visitorService.findById(id).orElse(null);
        if (visitor != null) {
            model.addAttribute("visitor", visitor);
            return "edit-visitor";
        } else {
            return "redirect:/visitors";
        }
    }

    @PostMapping("/update/{id}")
    public String updateVisitor(@PathVariable("id") int id, @Valid @ModelAttribute("visitor") Visitors visitor, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-visitor";
        }
        visitor.setVisitorId(id);
        visitorService.updateVisitor(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/delete/{id}")
    public String deleteVisitor(@PathVariable("id") int id) {
        visitorService.deleteVisitor(id);
        return "redirect:/visitors";
    }
    
    @PostMapping("/execute-query")
    public String executeQuery(@RequestParam("sqlQuery") String sqlQuery, Model model) {
        try {
            String trimmedQuery = sqlQuery.trim().toLowerCase();
            
            // Check if query is of type SELECT
            if (trimmedQuery.startsWith("select")) {
                List<Map<String, Object>> result = jdbcTemplate.queryForList(sqlQuery);
                model.addAttribute("queryResult", result);
            } else {
                // Execute update for INSERT, UPDATE, DELETE
                int rowsAffected = jdbcTemplate.update(sqlQuery);
                model.addAttribute("message", "Запит виконано успішно. Змінені рядки:" + rowsAffected);
            }
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Помилка виконання запиту: " + e.getMessage());
        }
        return "visitors";
    }
}
