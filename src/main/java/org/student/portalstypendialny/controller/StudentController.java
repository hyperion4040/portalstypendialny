package org.student.portalstypendialny.controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.student.portalstypendialny.model.Przedmiot;
import org.student.portalstypendialny.model.Student;
import org.student.portalstypendialny.service.StudentService;

import java.security.Principal;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/rola")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().toString();
    }


    @GetMapping(value = "/student",produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findByLogin(@RequestParam String login) {
        return studentService.findByLogin(login);
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
//    @PostAuthorize("returnObject.body.username == principal.username")
    @GetMapping("/studencik")
    @PostAuthorize("#login == authentication.principal ")
    public double returnAverageGrade(@RequestParam String login) {

        return studentService.returnAverageGrade(login);


    }

    @GetMapping("/tenStudent")
    @PostAuthorize("#login == principal")
    public double returnCurrentStudent(@RequestParam(defaultValue = "",required = false) String login, Principal principal) {
        if (login.isEmpty()) {

            return studentService.returnAverageGrade(principal.getName());
        }else
            return studentService.returnAverageGrade(login);
    }

    @GetMapping("/biblioteka")
    public ModelAndView returnLibrary() {
        final String url = "https://biblioteka.wsb.gda.pl/cgi-bin/wspd_cgi.sh/WService=wsbroker1/wo2_search.p?R=1&IDBibl=93&ID1=IOIFINNLNMKEFHIRKON&ln=pl";
        return new ModelAndView("redirect:" + url);
    }


    @GetMapping(value = "/Podanie", produces = "application/pdf")
    public String returnPodanieView(@RequestParam String login) throws DocumentException {
        Document document = studentService.returnPodaniePdfView("Jakies", login);
        document.open();
        document.getHtmlStyleClass();
        return String.valueOf(document);

    }



    /*@GetMapping("/loginPage")
    public void login( @RequestParam String username, @RequestParam String password) {
        AuthenticationManager am = authentication -> {
            if (authentication.getName().equals(authentication.getCredentials())) {
                return new UsernamePasswordAuthenticationToken(authentication.getName()
                ,authentication.getCredentials()
                );
            }
            throw new BadCredentialsException("Bad Credentials");
        };

        Authentication request = new UsernamePasswordAuthenticationToken(username,password);
        Authentication result = am.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);

    }*/


/*@GetMapping("/login")
    public String loginPage(@RequestParam String username, @RequestParam String password) {
        MyUserDetailsService service = new MyUserDetailsService();
        if (
        service.loadUserByUsername(username).getUsername().equals(username)&&
        service.loadUserByUsername(username).getPassword().equals(password)){

            return "redirect:/";
        }else
            return "redirect:/login?error";
    }*/



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


