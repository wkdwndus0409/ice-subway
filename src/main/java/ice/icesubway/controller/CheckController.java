package ice.icesubway.controller;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;

import ice.icesubway.domain.Image;
import ice.icesubway.repository.CheckRepository;
import ice.icesubway.repository.PageRepository;
import ice.icesubway.service.CheckService;
import ice.icesubway.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CheckController {

    private final CheckService checkService;
    private final CheckRepository checkRepository;
    private final ImageService imageService;
    private final PageRepository pageRepository;

    @GetMapping("/storage")
    public String list() {
        return "storage";
    }

//    @GetMapping("/checkList/실신")
//    public String listSwoon(Model model) {
//        List<Check> checkImages = checkRepository.findByStatus(CheckStatus.실신);
//        model.addAttribute("checkImages", checkImages);
//        return "checks/swoomTables";
//    }

    @GetMapping("/checkList/실신")
    public String listSwoon(Model model, @PageableDefault(size = 2) Pageable pageable) {
            Page<Check> checkImages = pageRepository.findByCheckStatus(CheckStatus.실신, pageable);
            //        Page<Check> checkImages = pageRepository.findByImageNameContaining(keyword, pageable);
            int startPage = Math.max(1, checkImages.getPageable().getPageNumber() - 4);
            int endPage = Math.min(checkImages.getTotalPages(), checkImages.getPageable().getPageNumber() + 4);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("checkImages", checkImages);
            return "checks/swoomTables";

//            Page<Check> checkImages = pageRepository.findByImageNameContaining(CheckStatus.실신, keyword, pageable);
//            int startPage = Math.max(1, checkImages.getPageable().getPageNumber() - 4);
//            int endPage = Math.min(checkImages.getTotalPages(), checkImages.getPageable().getPageNumber() + 4);
//            model.addAttribute("startPage", startPage);
//            model.addAttribute("endPage", endPage);
//            model.addAttribute("checkImages", checkImages);
//            return "checks/swoomTables";

    }

    @GetMapping("/checks/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<Check> searchList = checkService.search(keyword);
        model.addAttribute("checkImages", searchList);
        return "checks/swoomTables";
    }

    @GetMapping("/checkList/오작동")
    public String listMalfunction(Model model) {
        List<Check> checkImages = checkService.findByStatus(CheckStatus.오작동);
        model.addAttribute("checkImages", checkImages);
        return "checks/malfunctionTables";
    }

    @GetMapping("/checkList/기타")
    public String listEtc(Model model) {
        List<Check> checkImages = checkService.findByStatus(CheckStatus.기타);
        model.addAttribute("checkImages", checkImages);
        return "checks/etcTables";
    }


    @PostMapping("/")
    public String checkImage(@ModelAttribute("status") String status, @ModelAttribute("etc") String etc) {

//        String DATA_DIR = "C:\\resources\\raw\\";
        String DATA_DIR = "\\root\\test\\";
        File dir = new File(DATA_DIR);
        String[] filenames = dir.list();

        if(filenames.length == 0) {
            return "images/noImage";
        }

        Image image = new Image();
        image.setName(filenames[0]);

        String date = filenames[0].split("_")[0];
        String year = "20" + date.substring(0, 2);
        String month = date.substring(2, 4);
        String day = date.substring(4, 6);
        String time = "T" + date.substring(6, 8) + ":" + date.substring(8, 10) + ":" + date.substring(10, 12);

        image.setDateTime(year + "-" + month + "-" + day + " " + time);

        Long imageId = imageService.saveImage(image);

        String checkStatus = "";
        if (etc.isEmpty()) {
            checkStatus = status;
        } else {
            checkStatus = status + "/" + etc;
        }

        checkService.check(1L, imageId, checkStatus);

        File deleteFile = new File(DATA_DIR + filenames[0]);
        if (deleteFile.exists()) {
            deleteFile.delete();
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }

        return "redirect:/";
    }


}
