package org.student.portalstypendialny.student;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.student.portalstypendialny.repository.StudentRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    void testUnauthenticatedAccessToRegistrationPage() throws Exception {
        mockMvc
                .perform(get("/registration"))
                .andExpect(unauthenticated());
    }

    @Test
    void testAuthorizationOnLoginPageWithWrongPassword() throws Exception {
        mockMvc
                .perform(formLogin("/login")
                        .user("hyperion")
                        .password("rot"))
                .andExpect(unauthenticated());
    }

    @Test
    @WithUserDetails(value = "hyperion")
    void testAuthenticatonOnStudentPageWithCorrectCredentials() throws Exception {
        mockMvc
                .perform(get("/student"))
                .andExpect(authenticated());
    }

    @Test
    void testAuthenticationOnLoginPageWithUsernameWhichDoNotExist() throws Exception {
        mockMvc
                .perform(formLogin("/login")
                        .user("hyp")
                        .password("ro"))
                .andExpect(unauthenticated());
    }

    @Test
    @WithUserDetails(value = "hyperion")
    void testAuthenticationOnStudentPageWithCorrectRoleOfUser() throws Exception {
            mockMvc
                    .perform(get("/student"))
                    .andExpect(authenticated().withUsername("hyperion").withRoles("USER"));
    }

}
