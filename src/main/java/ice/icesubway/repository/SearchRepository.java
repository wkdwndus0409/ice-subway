package ice.icesubway.repository;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SearchRepository extends JpaRepository<Check, Long> {

    @Query(value = "select c from Check c where c.imageName like %?1%")
    List<Check> findByImageNameContaining(String keyword);
}
