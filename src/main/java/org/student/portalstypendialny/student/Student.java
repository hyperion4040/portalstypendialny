package org.student.portalstypendialny.student;


import lombok.*;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {



    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "emal", nullable = false, unique = true)
    @Email(message = "Please enter valid email")
    @NotNull(message = "Please enter email")
    private String email;
    @Column(name = "password")
    @Transient
    private String password;
    @Column(name = "first_name")
    @NotNull(message = "Wprowadz kierunek studiów")
    private String studentProgram;

    @Column(name = "year")
    @NotNull(message = "Wprowadz rok studiów na jakich jesteś")
    private int year;

}


