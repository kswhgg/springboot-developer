package me.hsw.springbootdeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-member.sql")
    @Test
    void getAllMembers() {
        //when
        List<Member> members = memberRepository.findAll();
        System.out.println("members: "+ members);

        //then
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-member.sql")
    @Test
    void getMemberById() {
        //when
        Member member = memberRepository.findById(2L).get();
        System.out.println("member: "+ member);

        //then
        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-member.sql")
    @Test
    void getMemberByName() {
        //when
        Member member = memberRepository.findByName("C").get();
        System.out.println("member: "+ member);

        //then
        assertThat(member.getId()).isEqualTo(3);
    }

    @Test
    void saveMember() {
        //given
        Member member = new Member(1L, "A");

        //when
        memberRepository.save(member);

        List<Member> findMembers = memberRepository.findAll();
        System.out.println("findMembers: "+ findMembers);

        //then
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    @Test
    void saveMembers() {
        //given
        List<Member> members = List.of(new Member(2L, "B"),
                new Member(3L, "C"));
        //when
        memberRepository.saveAll(members);

        List<Member> findMembers = memberRepository.findAll();
        System.out.println("findMembers: "+ findMembers);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Sql("/insert-member.sql")
    @Test
    void deleteMemberById() {
        //when
        memberRepository.deleteById(2L);

        //then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    @Sql("/insert-member.sql")
    @Test
    void deleteAll() {
        //when
        memberRepository.deleteAll();

        //then
        assertThat(memberRepository.findAll().size()).isZero();
    }

    @Sql("/insert-member.sql")
    @Test
    void update() {
        //given
        Member member = memberRepository.findById(2L).get();

        //when
        member.changeName("BC");

        //then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }

}