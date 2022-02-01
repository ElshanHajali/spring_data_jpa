package com.example.spring_data_jpa.repositery;

import com.example.spring_data_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByGuardianName(String firstName);

    List<Student> findByGuardianNameContaining(String name);

    // JPQL
    @Query("select s from Student s where s.email=?1")
    Student getStudentByEmailAddress(String emailAddress);

    @Query("select s.firstName from Student s where s.email =?1")
    String getStudentFirstNameByEmailAddress(String email);

    // Native Query
    @Query(
            value = "SELECT * FROM student_db s where s.email_address=?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailAddress);

    // Native Named Param
    @Query(
            value = "SELECT * FROM student_db s where s.email_address=:emailAddress",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailAddress") String emailAddress
    );

    // Update operation
    @Modifying
    @Transactional
    @Query(
            value = "UPDATE student_db set first_name=?1 where email_address=?2",
            nativeQuery = true
    )
    int updateFirstNameByEmailAddress(String name, String email);

}
