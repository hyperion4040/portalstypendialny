package org.student.portalstypendialny.student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {

    Student findByEmail(String email);

    Student findByLogin(String login);
}
