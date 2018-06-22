package org.student.portalstypendialny.repository;

import org.springframework.data.repository.CrudRepository;
import org.student.portalstypendialny.model.Student;

public interface StudentRepository extends CrudRepository<Student,Long> {

    Student findByEmail(String email);
//    @PostAuthorize("returnObject.owner.username == principal.username")
    Student findByLogin(String login);
}
