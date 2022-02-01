package com.example.spring_data_jpa.repositery;

import com.example.spring_data_jpa.entity.Course;
import com.example.spring_data_jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course courseDP = Course.builder()
                .title("DL")
                .credit(8)
                .build();
        Course courseJava = Course.builder()
                .title("Java")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Said")
                .lastName("Akhundov")
                /*.courses(
                        List.of(
                                courseDP,courseJava
                        )
                )*/
                .build();

        teacherRepository.save(teacher);
    }
}