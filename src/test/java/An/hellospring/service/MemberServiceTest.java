package An.hellospring.service;

import An.hellospring.domain.Member;
import An.hellospring.repository.MemoryMemberRespository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRespository memberRespository;

    @BeforeEach
    public void beforeEach(){
        memberRespository = new MemoryMemberRespository();
        memberService = new MemberService(memberRespository);

    }

    @AfterEach
    public void afterEach(){
        memberRespository.clearStore();        // test가 끝날때마다 클리어 해줌
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("string");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
//        }


        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}