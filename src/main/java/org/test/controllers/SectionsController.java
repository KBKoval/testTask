package org.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.models.SectionsDto;
import org.test.services.interfaces.SectionsService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("sections")
public class SectionsController {
    private final SectionsService sectionsService;

    @Autowired
    public SectionsController(SectionsService sectionsService){
        this.sectionsService = sectionsService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SectionsDto>> getAllSections(){
        return new ResponseEntity<>( sectionsService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/by-id/{id}")
    public SectionsDto getSectionByID(@NotNull @PathVariable("id") String id){
        return null;
    }

    @GetMapping(value = "/by-code")
    public SectionsDto getSectionByCode(@NotNull @RequestParam String code){
        return null;
    }
}
