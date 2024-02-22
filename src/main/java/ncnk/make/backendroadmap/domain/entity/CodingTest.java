package ncnk.make.backendroadmap.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class CodingTest extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "codingTest_id")
    private Long codingTestId;
    private String problemName;
    private String problemLevel;
    private String problemContext;
    private String problemImage;
    private String problemInput;
    private String problemOutput;
    private Double problemAccuracy;
    private Boolean testOrQuiz;

    @ManyToOne
    @JoinColumn(name = "main_docs_id")
    private MainCategory mainCategory;
}