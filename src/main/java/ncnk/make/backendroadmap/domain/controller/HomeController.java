package ncnk.make.backendroadmap.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ncnk.make.backendroadmap.domain.entity.Member;
import ncnk.make.backendroadmap.domain.exception.SessionNullPointException;
import ncnk.make.backendroadmap.domain.security.auth.LoginUser;
import ncnk.make.backendroadmap.domain.security.auth.dto.SessionUser;
import ncnk.make.backendroadmap.domain.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 홈 Controller
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String mainhomepage(@LoginUser SessionUser user, Model model) {
        //로그인한 사용자만 home.html에 접속가능
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        return "mainHome";
    }

    @GetMapping("/home")
    public String homeLoginV3ArgumentResolver(@LoginUser SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        //TODO: dev/server 머지하고 mainPage.html로 return 값 변경하기!
        return "Login/login";
    }

    private final MemberService memberService;
    @GetMapping("/myPage")
    public String myPage(@LoginUser SessionUser user, Model model,@SessionAttribute(name = "member", required = false) SessionUser sessionUser
                         ) {
        if (sessionUser == null) {
            throw new SessionNullPointException("[ERROR] SessionUser is null");
        }
        Member member = memberService.findMemberByEmail(sessionUser.getEmail()); // 회원 검색
        model.addAttribute("memberID", member.getMemberId());
        model.addAttribute("Profile", member.getProfile());
        model.addAttribute("Email", member.getEmail());
        model.addAttribute("Name", member.getName());
        model.addAttribute("NickName",member.getNickName());
        model.addAttribute("Github", member.getGithub());
        model.addAttribute("Level", member.getLevel());
        model.addAttribute("Point",member.getPoint());
        model.addAttribute("hard",member.getHard());
        model.addAttribute("normal",member.getNormal());
        model.addAttribute("easy",member.getEasy());


        return "myPage/myPage";
    }
}
