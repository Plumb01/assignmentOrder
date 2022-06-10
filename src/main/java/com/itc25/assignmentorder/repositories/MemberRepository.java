package com.itc25.assignmentorder.repositories;

import com.itc25.assignmentorder.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {

}
