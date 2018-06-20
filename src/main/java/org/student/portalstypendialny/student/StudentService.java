package org.student.portalstypendialny.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.student.portalstypendialny.przedmiot.Przedmiot;

import java.util.List;

@Service("studentService")
public class StudentService {


    private StudentRepository studentRepository;

    @Autowired
    public StudentService(@Qualifier("studentRepository") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    List<Przedmiot> returnStudentCourses(String login) {
        return studentRepository.findByLogin(login).getPrzedmiotList();
    }

    Student findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    void saveStudent(Student student){
        studentRepository.save(student);
    }


    double returnAverageGrade(String login) {
        int size = studentRepository.findByLogin(login).getPrzedmiotList().size()-1;
        double result = 0;
        for (int i = 0; i <= size ; i++) {
            if (studentRepository.findByLogin(login).getPrzedmiotList().get(i).getOcenaWyklad()!=0){

                result+= studentRepository.findByLogin(login).getPrzedmiotList().get(i).getOcenaWyklad();
            }else
                result+= studentRepository.findByLogin(login).getPrzedmiotList().get(i).getOcenaCwiczenia();
        }
        return result/(size+1);
    }

}
