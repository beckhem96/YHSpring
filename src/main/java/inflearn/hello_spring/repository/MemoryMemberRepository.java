package inflearn.hello_spring.repository;

import inflearn.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository // 컴포넌트 스캔으로 컨테이너에 빈 생성
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 동시성 문제 가능, 다른 방법 있음
    private static long sequence = 0L; // 여기도 동시성 문제 가능, 다른 방법 있음
    // 동시성 -> 공유 자원에 동시 접근

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
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
