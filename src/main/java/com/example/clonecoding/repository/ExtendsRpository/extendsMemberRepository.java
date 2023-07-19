package com.example.clonecoding.repository.ExtendsRpository;

import com.example.clonecoding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface extendsMemberRepository extends JpaRepository<Member, Long> {


    List<Member> findByUsername(String username);
}
