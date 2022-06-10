package com.itc25.assignmentorder.repositories;

import com.itc25.assignmentorder.models.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty,Integer> {

}
