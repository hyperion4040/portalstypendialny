package org.student.portalstypendialny.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {


    @GetMapping("/student")
    public String defaultPageSecurity(){
        return "Witaj Adrian, to jest zabezpieczenie";
    }

    @GetMapping("/")
    public String defaultPage(){
        return "Witaj Adrian";
    }
}
