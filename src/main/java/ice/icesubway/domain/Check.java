package ice.icesubway.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "checks")
@Getter @Setter
public class Check {

    @Id @GeneratedValue
    @Column(name = "check_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    // 추가
    private String imageName;

    @Enumerated(EnumType.STRING)
    private CheckStatus checkStatus; //실신, 오작동, 기타

    private String etc; //기타사항

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getCheckList().add(this);
    }

    public void setImage(Image image) {
        this.image = image;
        this.imageName = image.getName();
    }

    //==생성 메서드==//
    public static Check createCheck(Member member, Image image, String status) {
        Check check = new Check();
        check.setMember(member);
        check.setImage(image);

        if (status.equals("실신")) {
            check.setCheckStatus(CheckStatus.실신);
        } else if (status.equals("오작동")) {
            check.setCheckStatus(CheckStatus.오작동);
        } else {
            check.setCheckStatus(CheckStatus.기타);
            String etc = status.split("/")[1];
            check.setEtc(etc);
        }
        return check;
    }
}
