package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//@Repository // @Configuration 클래스에세 빈 수동 등록으로 대체 가능함.
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 루프를 돌면서 이름이 같은게 나오면 반환한다.
                .findAny();
    }
    public void clearStore() {
        store.clear();
    }
}
