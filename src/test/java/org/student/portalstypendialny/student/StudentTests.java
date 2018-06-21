package org.student.portalstypendialny.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.student.portalstypendialny.controller.StudentController;
import org.student.portalstypendialny.model.Student;
import org.student.portalstypendialny.repository.StudentRepository;

import java.util.LinkedList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentTests {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .build();
        student = new Student("hyperion", "hy@com", "password", "CS", 3,new LinkedList<>());

    }


    @Test
    void defaultMessege() throws Exception {

        when(studentController.welcomePage()).thenReturn("Witaj");

        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Witaj")));


    }

    @Test
    void defaultReturnStudent() throws Exception {
       when(studentController.returnTestStudentObject()).thenReturn(student);

       mockMvc
               .perform(get("/test"))
               .andExpect(status().isOk());
    }

    @Test
    void testRepository() throws Exception {


        when(studentRepository.findByLogin("hyperion")).thenReturn(student);
        mockMvc
                .perform(get("/student"))
                .andExpect(status().isOk());
    }


    void testReturnAverageGrade() throws Exception {
        when(studentController.returnAverageGrade("hyperion")).thenReturn(4.0);
        mockMvc
                .perform(get("/studencik"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("4.0")));
    }


}
