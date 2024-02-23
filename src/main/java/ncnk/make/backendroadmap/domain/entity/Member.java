package ncnk.make.backendroadmap.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ncnk.make.backendroadmap.domain.common.BaseTimeEntity;

@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    private String profile;
    private String email;
    private String name;
    private String nickName;
    private String github;
    private int level;
    private int point;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_docs_id")
    private MainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    private Member(String profile, String email, String name, String nickName, String github, int level, int point,
                   Role role) {
        this.profile = profile;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.github = github;
        this.level = level;
        this.point = point;
        this.role = role;
    }

    public static Member createMember(String profile, String email, String name, String nickName, String github,
                                      int level, int point, Role role) {
        return new Member(profile, email, name, nickName, github, level, point, role);
    }

    public Member updateMember(String profile, String name, String github) {
        this.profile = profile;
        this.name = name;
        this.github = github;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}