package hello.basic.service;

import hello.basic.domain.Member;
import hello.basic.repository.MemberRepository;
import hello.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // @Configuration 클래스에세 빈 수동 등록으로 대체 가능함.
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 1차
    private final MemberRepository memberRepository; //2차 DI를 받기위한 필드만 선언하고 생성자로 주입받는다.

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
