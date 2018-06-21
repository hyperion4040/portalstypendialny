package org.student.portalstypendialny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.student.portalstypendialny.model.Przedmiot;
import org.student.portalstypendialny.model.Student;
import org.student.portalstypendialny.service.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public Student findByLogin(@RequestParam String login) {
        return studentService.findByEmail(login);
    }

    @PostMapping("/registration")
    public ResponseEntity<Student> postRegister(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @GetMapping("/")
    public String welcomePage() {
        return "Witaj Adrian";
    }


    @GetMapping("/test")
    public Student returnTestStudentObject() {
        return new Student();
    }

    @GetMapping("/courses")
    public List<Przedmiot> returnCoursesOfConcreteUser(@RequestParam String login) {
        return studentService.returnStudentCourses(login);
    }

    @GetMapping("/studencik")
    public double returnAverageGrade(@RequestParam String login) {
        return studentService.returnAverageGrade(login);

    }

    @GetMapping("/biblioteka")
    public ModelAndView returnLibrary() {
        final String url = "https://biblioteka.wsb.gda.pl/cgi-bin/wspd_cgi.sh/WService=wsbroker1/wo2_search.p?R=1&IDBibl=93&ID1=IOIFINNLNMKEFHIRKON&ln=pl";
        return new ModelAndView("redirect:" + url);
    }


  /*  @PostMapping("/wniosek")
    public ResponseEntity<Podanie> napiszWniosek(
            @RequestParam String login,
            @RequestParam String uzasadnienie,
            @RequestParam List<String> zalaczniki) {


    }*/


   /* @GetMapping("/send")
    public void addStudentMock(){
        final String url = "http://localhost:8080/postregister";

        Student student = new Student("wojciech","woj@com","password","Wojciech","Grunt");
        RestTemplate restTemplate = new RestTemplate();
        Student result = restTemplate.postForObject(url,student,Student.class);
        studentRepository.save(student);
    }*/

   /*@GetMapping(value = "/send")
   public ResponseEntity loginStudent() throws URISyntaxException {
       final String url = "http://localhost:8080/login";
       URI uri = new URI(url);
       HttpHeaders httpHeaders = new HttpHeaders();
       httpHeaders.setLocation(uri);
       return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
   }*/

    /*@PostMapping("/login")
    public String loginMethod(@RequestParam("username") String username,
     @RequestParam("password") String password
    ){
         if (password.equals(studentRepository.findByLogin(username).getPassword())){
             UserDetails.class.

         }


    }*/


}