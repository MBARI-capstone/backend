package com.MBARI.controller;

import com.MBARI.dto.ExpeditionDto;
import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {

    @Autowired
    ExpeditionRepository ExpeditionRepository;
    ExpeditionEntity ExpeditionEntity;
    @PostMapping("/test")
    public void TestRequest() {
        System.out.println("Testing PreExpedition Controller.");
    }

    @PostMapping("/expedition")
    public ExpeditionEntity AddPreExpeditionRequest(  @RequestBody ExpeditionEntity ex){
            //ExpeditionEntity Ex = new ExpeditionEntity();
            //if id exists do we throw an exception?
            //post with approved as null
            //

            //TODO Create POST a Preexpedition Report for API To Transfer to DB
            //TODO These comments are references to build the method
            ExpeditionEntity savedExped = ExpeditionRepository.save(ex);
            //student.setName(student_name);
            //Student savedStudent = studentRepository.save(student);
           // System.out.println("Student successfully added.");
            //return savedStudent;
        return savedExped;
    }

    @GetMapping("/expedition_request")
    public ExpeditionDto RetrievePreExpeditionRequest(@RequestParam("expeditionId") Integer id){
        ExpeditionRepository.findbyID();
    }
    //TODO @PutMapping approval after retriving request
    //TODO @GetMapping approval or denial
}
