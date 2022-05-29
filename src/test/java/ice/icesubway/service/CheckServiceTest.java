package ice.icesubway.service;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;
import ice.icesubway.domain.Image;
import ice.icesubway.domain.Member;
import ice.icesubway.repository.CheckRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CheckServiceTest {

    @Autowired EntityManager em;
    @Autowired CheckService checkService;
    @Autowired CheckRepository checkRepository;

//    @Test
//    public void 이미지처리() throws Exception {
//        //given
//        Member member = new Member();
//        member.setName("kim");
//        member.setStation("강남역");
//        em.persist(member);
//
//        Image image = new Image();
//        image.setName("2348_in_3-2");
////        image.setDateTime();
//        em.persist(image);
//
//        String status = "기타/마스크 미착용";
//
//        //when
//        Long checkId = checkService.check(member.getId(), image.getId(), status);
//
//        //then
//        Check getCheck = checkRepository.findOne(checkId);
//
//        assertEquals(CheckStatus.기타, getCheck.getCheckStatus());
//        assertEquals("마스크 미착용", getCheck.getEtc());
//    }
}