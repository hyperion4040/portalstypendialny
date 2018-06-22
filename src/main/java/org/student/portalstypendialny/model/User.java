package org.student.portalstypendialny.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {


    @Id
    private String username;

    private String program;
}
