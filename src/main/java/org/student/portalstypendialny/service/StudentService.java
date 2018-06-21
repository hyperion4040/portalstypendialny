package org.student.portalstypendialny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.student.portalstypendialny.model.Przedmiot;
import org.student.portalstypendialny.model.Student;
import org.student.portalstypendialny.repository.StudentRepository;

import java.util.List;

@Service("studentService")
public class StudentService {


    private StudentRepository studentRepository;

    @Autowired
    public StudentService(@Qualifier("studentRepository") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Przedmiot> returnStudentCourses(String login) {
        return studentRepository.findByLogin(login).getPrzedmiotList();
    }

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }


    public double returnAverageGrade(String login) {
        int size = studentRepository.findByLogin(login).getPrzedmiotList().size();
        List<Przedmiot> listaPrzedmiotow = returnStudentCourses(login);

        double result = 0;
        for (Przedmiot przedmiot : listaPrzedmiotow) {
            if (przedmiot.getOcenaWyklad() != 0) {
                result += przedmiot.getOcenaWyklad();
            }else {
                result += przedmiot.getOcenaCwiczenia();
            }

        }
        return result/size;
    }

}
