package org.student.portalstypendialny.student;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.student.portalstypendialny.przedmiot.Przedmiot;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "STUDENT")
public class Student {



    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Wprowadź email ze wszystkimi niezbędnymi elementami ")
    @NotNull(message = "Wprowadź email")
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


    @OneToMany(cascade = CascadeType.ALL)
    private List<Przedmiot> przedmiotList;


}


