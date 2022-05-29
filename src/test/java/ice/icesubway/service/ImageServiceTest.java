package ice.icesubway.service;

import ice.icesubway.domain.Image;
import ice.icesubway.repository.ImageRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ImageServiceTest {

    @Autowired EntityManager em;
    @Autowired ImageService imageService;
    @Autowired ImageRepository imageRepository;

//    @Test
//    public void 이미지저장() throws Exception {
//        //given
//        Image image = new Image();
//        image.setName("2348_in_3-2");
////        image.setDateTime(LocalDateTime.now());
//
//        //when
//        Long savedId = imageService.saveImage(image);
//
//        //then
//        Assertions.assertEquals(image, imageRepository.findOne(savedId));
//    }

}