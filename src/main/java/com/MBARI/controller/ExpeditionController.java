package com.MBARI.controller;

import com.MBARI.entity.ExpeditionEntity;
import com.MBARI.repository.ExpeditionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1.1")
public class ExpeditionController {
    @PostMapping("/test")
    public void TestRequest() {
        System.out.println("Testing PreExpedition Controller.");
    }

    @PostMapping("/expedition")
    public ExpeditionEntity AddPreExpeditionRequest( ExpeditionEntity ex){
            ExpeditionEntity Ex = new ExpeditionEntity();
            Ex.setShipId(Ex.getShipId());
            //TODO Create POST a Preexpedition Report for API To Transfer to DB
            //TODO These comments are references to build the method
            //student.setName(student_name);
            //Student savedStudent = studentRepository.save(student);
           // System.out.println("Student successfully added.");
            //return savedStudent;
        return null;
    }
}
