package org.student.portalstypendialny.student;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private CloudantClient cloudantClient;

    private Database db;

    @GetMapping("/student")
    public String defaultPageSecurity(){
        return "Witaj Adrian, to jest zabezpieczenie";
    }

    @GetMapping("/")
    public String defaultPage(){


        return "Witaj Adrian";
    }

    @GetMapping("/data")
    public @ResponseBody List<String> data(){
        List<String> allDs = cloudantClient.getAllDbs();
        Student student1 = new Student("adi","pass","pass","Adrian","Koz");
        Student student2 = new Student("adis","pass1","pass2","Adam","Kozol");
        db = cloudantClient.database("mydb",false);
        db.post(student1);
        return allDs;
    }



}
