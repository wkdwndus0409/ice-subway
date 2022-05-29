package ice.icesubway.repository;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CheckRepository {

    private final EntityManager em;

    public void save(Check check) {
        em.persist(check);
    }

    public Check findOne(Long id) {
        return em.find(Check.class, id);
    }

    public List<Check> findAll() {
        return em.createQuery("select c  from Check c", Check.class)
                .getResultList();
    }

//    public List<Check> findByStatus(CheckStatus status) {
//        return em.createQuery("select c from Check c where c.checkStatus = :status", Check.class)
//                .setParameter("status", status)
//                .getResultList();
//    }

    public List<Check> findByStatus(CheckStatus status) {
        return em.createQuery("select c from Check c where c.checkStatus = :status", Check.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Check> findAllByString(CheckSearch checkSearch) {
        String jpql = "select c From Check c join c.member m";
        boolean isFirstCondition = true;
        // 처리 상태 검색
        if (checkSearch.getCheckStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " j.status = :status";
        }

        // 이미지 이름 검색
        if (StringUtils.hasText(checkSearch.getImageName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Check> query = em.createQuery(jpql, Check.class)
                .setMaxResults(1000); //최대 1000건
        if (checkSearch.getCheckStatus() != null) {
            query = query.setParameter("status", checkSearch.getCheckStatus());
        }
        if (StringUtils.hasText(checkSearch.getImageName())) {
            query = query.setParameter("name", checkSearch.getImageName());
        }
        return query.getResultList();
    }
}
