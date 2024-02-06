package com.make.backendroadmap.domain.controller;

import com.make.backendroadmap.domain.controller.dto.Member.MemberUpdateRequestDto;
import com.make.backendroadmap.domain.entity.Member;
import com.make.backendroadmap.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberUpdateController {
    private final MemberService memberService;

    @GetMapping("/edit/{memberId}")
    public String updateProfile(@PathVariable Long memberId, Model model) {
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "form/updateForm";
    }

    @PostMapping("/edit/{memberId}")
    public String update(@PathVariable Long memberId,
                         @ModelAttribute MemberUpdateRequestDto updateRequestDto) {
        Member member = memberService.findMemberById(memberId);
        memberService.updateProfile(member, updateRequestDto);

        return "redirect:/form/myPage/{memberId}";
    }
}