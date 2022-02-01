package com.example.spring_data_jpa.repositery;

import com.example.spring_data_jpa.entity.Course;
import com.example.spring_data_jpa.entity.Student;
import com.example.spring_data_jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> course = courseRepository.findAll();

        System.out.println("course = " + course);
    }

    @Test
    public void saveCourse(){
        Teacher teacher = Teacher.builder()
                .firstName("Rufat")
                .lastName("Manafli")
                .build();

        Course course = Course.builder()
                .title("CMS")
                .credit(8)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findByPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);

        List<Course> courses =
                courseRepository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements =
                courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        long totalPages =
                courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);

    }

    @Test
    public void sortByTitleTest(){
        Pageable sortByTitle = PageRequest.of(
                0,
                2,
                Sort.by("title")
        );

        Pageable sortByCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("credit").descending()
        );

        Pageable sortByCreditAndTitle = PageRequest.of(
                0,
                2,
                Sort.by("credit")
                        .descending()
                        .and(Sort.by("title"))
        );

        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printfindByTitleContaining(){
        Pageable firstTenTitleRecords =
                PageRequest.of(0,10);

        List<Course> courses = courseRepository
                .findByTitleContaining("D",firstTenTitleRecords)
                .getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveStudentAndCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Xalid")
                .lastName("Mushfiq")
                .build();

        Student student = Student.builder()
                .firstName("ELshan")
                .lastName("Hacali")
                .email("elshan.hac@outlook.com")
                .build();

        Course course = Course.builder()
                .title("DMS")
                .credit(8)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}