package org.student.portalstypendialny.person;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@RequiredArgsConstructor
public class Person {

    @Id
    private String id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
