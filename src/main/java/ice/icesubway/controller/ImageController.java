package ice.icesubway.controller;

import ice.icesubway.domain.Image;
import ice.icesubway.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/display")
    public ResponseEntity<Resource> display(@Param("filename") String filename) {

//        String path = "C:\\resources\\storage\\";
        String path = "\\root\\test\\";

        Resource resource = new FileSystemResource(path + filename);
        if (!resource.exists())
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);

        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

        try {
            filePath = Paths.get(path + filename);
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    @GetMapping("/images/monitor")
    public String getImage(Model model) {

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
            return "images/noImage";
        }
        return "images/homePage";
    }
}
