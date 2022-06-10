package com.itc25.assignmentorder.repositories;

import com.itc25.assignmentorder.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,String> {

    //SQL
    @Query(value = """
            Select *
            FROM Assignment
            WHERE AssignmentID LIKE %:tahun%
            """, nativeQuery = true)
    List<Assignment> getAssignmentByYear(@Param("tahun") String tahun);


    @Query(value = """
            Select *
            FROM Assignment
            WHERE Status = :status
            """, nativeQuery = true)
    List<Assignment> getAssignmentByStatus(@Param("status") String status);
}
