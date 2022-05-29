package ice.icesubway.controller;

import ice.icesubway.domain.Image;
import ice.icesubway.domain.Member;
import ice.icesubway.repository.MemberRepository;
import ice.icesubway.service.ImageService;
import ice.icesubway.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.awt.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setStation(form.getStation());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String login(@Valid MemberForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "members/loginMemberForm";
        }

        Member findMember = memberService.findByName(form.getName());
        if ((findMember.getStation()).equals(form.getStation())) {
            return "redirect:/";
        }
        else {
            return "members/notAMember";
        }
    }

}
