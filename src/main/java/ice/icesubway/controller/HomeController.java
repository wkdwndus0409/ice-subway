package ice.icesubway.controller;

import ice.icesubway.domain.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {
        log.info("home controller");

//        String DATA_DIR = "C:\\resources\\raw\\";
        String DATA_DIR = "\\root\\test\\";
        File dir = new File(DATA_DIR);

        String[] filenames = dir.list();
        if (filenames.length != 0) {
            List<Image> images = new ArrayList<>();
            for (String filename : filenames) {
                Image image = new Image();
                image.setName(filename);

                String date = filename.split("_")[0];
                String year = "20" + date.substring(0, 2);
                String month = date.substring(2, 4);
                String day = date.substring(4, 6);
                String time = "T" + date.substring(6, 8) + ":" + date.substring(8, 10) + ":" + date.substring(10, 12);

//                image.setDateTime(LocalDateTime.now());
                image.setDateTime(year + "-" + month + "-" + day + " " + time);
                images.add(image);
            }

            Image firstImage = images.get(0);

            model.addAttribute("firstImage", firstImage);
            model.addAttribute("images", images);
        } else {
            return "noImage";
        }

        return "index";
    }
}
