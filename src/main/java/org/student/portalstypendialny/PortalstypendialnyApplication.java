package org.student.portalstypendialny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.student.portalstypendialny.model.Przedmiot;
import org.student.portalstypendialny.model.Student;
import org.student.portalstypendialny.repository.StudentRepository;
import org.student.portalstypendialny.model.User;
import org.student.portalstypendialny.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {StudentRepository.class})
//@PropertySource("classpath:cloudant.properties")
public class PortalstypendialnyApplication {

    @Autowired
    public PortalstypendialnyApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PortalstypendialnyApplication.class, args);
    }

    private final UserRepository userRepository;

    @Bean
    CommandLineRunner init(final StudentRepository studentRepository){
        return args -> {
            Przedmiot przedmiot = new Przedmiot("Programowanie w języku java");
            Przedmiot przedmiot2 = new Przedmiot("Programowanie w języku c++");
            przedmiot.setOcenaCwiczenia(4);
            przedmiot2.setOcenaWyklad(4);
            przedmiot2.setOcenaCwiczenia(5);
            List<Przedmiot> list = new LinkedList<>();
            list.add(przedmiot);
            list.add(przedmiot2);
            studentRepository.save(
                    new Student("hyperion","hyp@com","ro","CS",2,list));

            userRepository.deleteAll();
            userRepository.save(new User("ultronik","Light"));
            User user1 = userRepository.findByUsername("ultronik");
            System.out.println(user1.getUsername() + " a program to "+user1.getUsername());

//                personRepository.save(new Person("Adrian","Adrianos"));
//                System.out.println(personRepository.findByFirstName("Adrianos"));

        };
    }


}
