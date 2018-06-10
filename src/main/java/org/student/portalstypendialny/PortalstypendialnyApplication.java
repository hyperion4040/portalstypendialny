package org.student.portalstypendialny;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.student.portalstypendialny.student.Student;
import org.student.portalstypendialny.student.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {StudentRepository.class})
public class PortalstypendialnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalstypendialnyApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final StudentRepository studentRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                studentRepository.save(new Student("hyperion","hyperion@com.pl","ro","Adrian","Koz"));

            }
        };
    }




}
