package org.student.portalstypendialny.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService {


    private StudentRepository studentRepository;

    @Autowired
    public StudentService(@Qualifier("studentRepository") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

}
