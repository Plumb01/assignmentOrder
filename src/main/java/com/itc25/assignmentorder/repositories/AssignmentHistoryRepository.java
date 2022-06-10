package com.itc25.assignmentorder.repositories;

import com.itc25.assignmentorder.models.AssignmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentHistoryRepository extends JpaRepository<AssignmentHistory,String> {
}
