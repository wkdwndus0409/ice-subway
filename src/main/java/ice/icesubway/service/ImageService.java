package ice.icesubway.service;

import ice.icesubway.domain.Image;
import ice.icesubway.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public Long saveImage(Image image) {
        imageRepository.save(image);
        return image.getId();
    }

    public List<Image> findImages() {
        return imageRepository.findAll();
    }

    public Image findOne(Long imageId) {
        return imageRepository.findOne(imageId);
    }

    public Image findByName(String name) {
        return imageRepository.findByName(name);
    }
}
