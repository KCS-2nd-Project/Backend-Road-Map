package com.make.backendroadmap.domain.service;

import com.make.backendroadmap.domain.controller.dto.Member.MemberUpdateRequestDto;
import com.make.backendroadmap.domain.entity.Member;
import com.make.backendroadmap.domain.exception.ResourceNotFoundException;
import com.make.backendroadmap.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long updateProfile(Member member, MemberUpdateRequestDto updateRequestDto) {
        Member updateMember = member.updateMember(updateRequestDto.getProfile(),
                updateRequestDto.getName(),
                updateRequestDto.getGithub());

        log.info("Member 프로필 수정 성공");

        return updateMember.getMemberId();
    }

    public Member findMemberById(Long id) {
        Member member = memberRepository.findMemberByMemberId(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return member;
    }
}
