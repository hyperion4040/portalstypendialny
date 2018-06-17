package org.student.portalstypendialny.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.student.portalstypendialny.przedmiot.Przedmiot;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    public Student findByLogin(@RequestParam String login){
       return studentRepository.findByLogin(login);
    }

    @PostMapping("/registration")
    public ResponseEntity<Student> postRegister(@RequestBody Student student){
        studentRepository.save(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }



    @GetMapping("/")
    public String welcomePage(){
        return "Witaj Adrian";
    }


    @GetMapping("/test")
    public Student returnTestStudentObject(){
        return new Student();
    }

    @GetMapping("/courses")
    public List<Przedmiot> returnCoursesOfConcreteUser(@RequestParam String login) {
        return studentRepository.findByLogin(login).getPrzedmiotList();
    }

    @GetMapping("/studencik")
    public double returnAverageGrade(@RequestParam String login) {
        int size = studentRepository.findByLogin(login).getPrzedmiotList().size()-1;
        double result = 0;
        for (int i = 0; i <= size ; i++) {
            if (studentRepository.findByLogin(login).getPrzedmiotList().get(i).getOcenaWyklad()!=0){

                result+= studentRepository.findByLogin(login).getPrzedmiotList().get(i).getOcenaWyklad();
            }else
                result+= studentRepository.findByLogin(login).getPrzedmiotList().get(i).getOcenaCwiczenia();
        }
        return result/(size+1);

    }


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
