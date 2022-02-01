package com.example.spring_data_jpa.repositery;

import com.example.spring_data_jpa.entity.Guardian;
import com.example.spring_data_jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .firstName("Elshan")
                .lastName("Hacaliyev")
                .email("elshan@mail.ru")
                //.guardianName("Eliko")
                //.guardianEmail("eliko@gmail.com")
                //.guardianPhone("1234567890")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Gukhi")
                .email("gukhi@mail.ru")
                .phone("789456123")
                .build();

        Student student = Student.builder()
                .firstName("Elshan")
                .lastName("Hacaliyev")
                .email("elshan.hajali@mail.ru")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList: "+studentList);
    }

    @Test
    public void findByFirstName(){
        List<Student> students =
                studentRepository.findByGuardianName("Gukhi");

        System.out.println(students);
    }

    @Test
    public void findByGuardianNameContaining(){
        List<Student> students = studentRepository.findByGuardianNameContaining("e");

        System.out.println(students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student =
                studentRepository.getStudentByEmailAddress("elshan@mail.ru");

        System.out.println("student = " + student);
    }

    @Test
    public void getStudentFirstNameByEmailAddress(){
        String student =
                studentRepository.getStudentFirstNameByEmailAddress("elshan@mail.ru");

        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative("elshan.hajali@mail.ru");

        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam("elshan.hajali@mail.ru");

        System.out.println("student = " + student);
    }

    @Test
    public void updateFirstNameByEmailAddressTest(){
        int student = studentRepository.updateFirstNameByEmailAddress(
                "Nuru","elshan@mail.ru"
        );
    }

}