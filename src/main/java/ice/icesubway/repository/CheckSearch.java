package ice.icesubway.repository;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter @Setter
public class CheckSearch {

    private String imageName; // 이미지 이름
    private CheckStatus checkStatus; // 실신, 오작동, 기타

}
