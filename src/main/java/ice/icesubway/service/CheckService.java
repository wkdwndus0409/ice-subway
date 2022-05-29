package ice.icesubway.service;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;
import ice.icesubway.domain.Image;
import ice.icesubway.domain.Member;
import ice.icesubway.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckService {

    private final CheckRepository checkRepository;
    private final SearchRepository searchRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public Long check(Long memberId, Long imageId, String status) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Image image = imageRepository.findOne(imageId);

        //처리
        Check check = Check.createCheck(member, image, status);

        checkRepository.save(check);

        return check.getId();
    }

    public List<Check> findAllChecks() {
        return checkRepository.findAll();
    }

    public List<Check> findByStatus(CheckStatus status) {
        return checkRepository.findByStatus(status);
    }

//    public List<Check> findChecks(CheckSearch checkSearch) {
//        return checkRepository.findAllByString(checkSearch);
//    }

    @Transactional
    public List<Check> search(String keyword) {
        List<Check> checkList = searchRepository.findByImageNameContaining(keyword);
        return checkList;
    }
}
