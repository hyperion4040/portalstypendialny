package org.student.portalstypendialny;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalstypendialnyApplicationTests {


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



}
