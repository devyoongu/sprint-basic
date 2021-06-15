package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스로 따로 구현체를 만들지 안아도 JpaRepository 만 있으면 spring jpa가 구현체를 자동으로만들어 빈에 등록해 준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
