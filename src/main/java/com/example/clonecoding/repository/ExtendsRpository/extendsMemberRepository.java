package com.example.clonecoding.repository.ExtendsRpository;

import com.example.clonecoding.api.MemberApiController;
import com.example.clonecoding.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface extendsMemberRepository extends JpaRepository<Member, Long> {


    List<Member> findByUsername(String username);

//    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
//        return em.createQuery("select m " +
//                        "from Member m " +
//                        "where m.username = :username and m.age > :age", Member.class)
//                .setParameter("username", username)
//                .setParameter("age", age).getResultList();
//    }  이 복잡한 메서드를 아래 한줄로 처리가능, 똑같은 회원의 이름과 나이로 조회하는 예제임, 차이가 느껴지심?

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new studt,datajpa.repository.MemberDto(m.id,m.username,t.name)" +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @Query("select m from Member m")
    @EntityGraph(attributePaths = {"team"})
    List<Member> findMemberEntityGraph();

//    @EntityGraph(attributePaths = {"team"})
//    List<Member> findByUsername(@Param("username") String username);
}
