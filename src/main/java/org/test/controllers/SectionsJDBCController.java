package org.test.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.models.SectionsDto;
import org.test.services.interfaces.SectionsService;
import org.test.services.interfaces.SectionsServiceJDBC;

import java.util.List;

@RestController
@RequestMapping("sections")
public class SectionsJDBCController {
    private final SectionsServiceJDBC service;

    @Autowired
    public SectionsJDBCController(SectionsServiceJDBC service) {
        this.service = service;
    }

    @GetMapping(value = "/all_sections")
    public ResponseEntity<List<SectionsDto>> getAllSections(){
        return new ResponseEntity<>( service.findAll(), HttpStatus.OK);
    }

}
