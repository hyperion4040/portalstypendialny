package org.student.portalstypendialny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.student.portalstypendialny.model.Student;
import org.student.portalstypendialny.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("Blocked");
        }
        try {


            Student student = studentRepository.findByLogin(login);
            if (login != null && login.equals("hyperion")){
                return new User(student.getLogin(),"{noop}"+student.getPassword(),
                        true,true,true,true,AuthorityUtils.createAuthorityList("USER","ADMIN")
                        );
            }

            if (student != null) {

                return new User(student.getLogin(), "{noop}"+student.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList("USER"));
            }else {
                throw new UsernameNotFoundException("User not found");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
