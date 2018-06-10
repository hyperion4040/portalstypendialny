package org.student.portalstypendialny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.student.portalstypendialny.student.Student;
import org.student.portalstypendialny.student.StudentRepository;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {


    @Autowired
    public StudentRepository studentRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
    @Bean
    UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                Student student = studentRepository.findByLogin(s);
                if (student != null){
                    return new User(student.getLogin(),"{noop}"+student.getPassword(),true,true,true,true
                    ,AuthorityUtils.createAuthorityList("USER"));
                }else {
                    throw new UsernameNotFoundException("Uuser not found");
                }
            }
        };



    }



}