package org.student.portalstypendialny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.student.portalstypendialny.student.Student;
import org.student.portalstypendialny.student.StudentRepository;
import org.student.portalstypendialny.user.User;
import org.student.portalstypendialny.user.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {StudentRepository.class})
//@PropertySource("classpath:cloudant.properties")
public class PortalstypendialnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalstypendialnyApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner init(final StudentRepository studentRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                studentRepository.save(
                        new Student("hyperion","hyp@com","ro","CS",2)
                );
                userRepository.deleteAll();
                userRepository.save(new User("ultronik","Light"));
                User user1 = userRepository.findByUsername("ultronik");
                System.out.println(user1.getUsername() + " a program to "+user1.getUsername());

//                personRepository.save(new Person("Adrian","Adrianos"));
//                System.out.println(personRepository.findByFirstName("Adrianos"));

            }
        };
    }


}
