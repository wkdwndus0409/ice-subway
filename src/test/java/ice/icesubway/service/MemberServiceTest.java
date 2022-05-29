package ice.icesubway.service;

import ice.icesubway.domain.Member;
import ice.icesubway.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired EntityManager em;
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

//    @Test
//    public void 회원가입() throws Exception {
//        //given
//        Member member = new Member();
//        member.setName("kim");
//        member.setStation("강남역");
//
//        //when
//        Long savedId = memberService.join(member);
//
//        //then
//        assertEquals(member, memberRepository.findOne(savedId));
//    }
}