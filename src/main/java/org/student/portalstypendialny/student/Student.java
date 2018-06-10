package org.student.portalstypendialny.student;


import lombok.*;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(name = "login")
    private String login;

    @NonNull
    @Column(name = "emal", nullable = false, unique = true)
    @Email(message = "Please enter valid email")
    @NotNull(message = "Please enter email")
    private String email;
    @NonNull
    @Column(name = "password")
    @Transient
    private String password;
    @NonNull
    @Column(name = "first_name")
    @NotNull(message = "Please enter first name into")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    @NotNull(message = "Please enter a last name")
    private String lastName;

}


