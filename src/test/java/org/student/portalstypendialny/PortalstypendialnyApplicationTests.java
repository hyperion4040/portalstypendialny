package org.student.portalstypendialny;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.student.portalstypendialny.student.Student;
import org.student.portalstypendialny.student.StudentRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalstypendialnyApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void defaultTest() {
        Assert.assertEquals(2,0+2);
    }


    @Test
    public void securityDefaultTest() throws Exception {
        mvc
                .perform(get("/student")
                        .with(user("hyperion").password("ro").roles("USER")))
                .andExpect(status().isOk());
    }
    @Test
    public void securityTest() throws Exception {
        mvc
                .perform(formLogin("/student").user("hyperion").password("ros"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void defaultMessege() throws Exception {
        mvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect( content().string(containsString("Witaj Adrian")));

    }

    @Test
//    @WithMockUser(username = "hyperion",password = "ro",roles = "USER")
    public void MessegeAfterSuccesLogin() throws Exception {
        mvc
                .perform(formLogin("/login").user("hyperion").password("ro"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    public void loginByStudentUrl() throws Exception {

        mvc
                .perform(formLogin("/login").password("ro").user("hyperion"))
                .andExpect(authenticated());
    }


    @Test
    public void loginWithWrongCredentials() throws Exception {
        mvc
                .perform(formLogin("/login").password("ros").user("hyperion"))
                .andExpect(unauthenticated());
    }





    @Test
    @WithUserDetails
    public void testStudentsList() throws Exception {
        mvc
                .perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(authenticated());
    }

    /*@Test
    @WithUserDetails
    public void testStudentsName() throws Exception {
        mvc
                .perform(get("/students"))
                .andExpect(content().string(containsString("[\"Sebastian\",\"Mateusz\"]")));

    }*/


    @Test
    public void testNewUser() throws Exception {
        mvc
                .perform(get("/registration").param("username","wojtek")
                .param("password","password")
                )
                .andExpect(content().string(containsString("Rejestracja ukończona sukcesem")));

    }


    @Test
    public void testPostController() throws Exception {

        Student student = new Student("Marcin","mar@com","password","CS",2);


       mvc
                .perform(post("/postregister").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

       Student student1 = studentRepository.findByLogin("Marcin");
       Assert.assertEquals(student.getLogin(),student1.getLogin());

       Assert.assertEquals(student.getStudentProgram(),student1.getStudentProgram());


    }
}
