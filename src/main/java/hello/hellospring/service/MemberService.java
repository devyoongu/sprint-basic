package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Service // @Configuration 클래스에세 빈 수동 등록으로 대체 가능함.
@Transactional(rollbackFor = Exception.class)
//Exception.class 를 선언할 경우 error 케이스를 제외하고 checked excepiton 을 포함해서 모든 exception을 롤백하려고 할때
public class MemberService {
    //    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 1차
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) throws IOException, SQLException {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        throw new SQLException("checked excepiton >> do not rollback "); // 예외 발생 시 롤백을 하지 않음
//        throw new NullPointerException("unchecked excepiton"); // 예외 발생 시 롤백 해야함
//        return member.getId();
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
