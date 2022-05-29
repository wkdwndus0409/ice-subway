package ice.icesubway.repository;

import ice.icesubway.domain.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    private final EntityManager em;

    public void save(Image image) {
        em.persist(image);
    }

    public Image findOne(Long id) {
        return em.find(Image.class, id);
    }

    public List<Image> findAll() {
        return em.createQuery("select i from Image i", Image.class)
                .getResultList();
    }

    public Image findByName(String name) {
        return em.createQuery("select i from Image i where i.name = :name", Image.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
