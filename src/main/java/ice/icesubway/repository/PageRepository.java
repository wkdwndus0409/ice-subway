package ice.icesubway.repository;

import ice.icesubway.domain.Check;
import ice.icesubway.domain.CheckStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Check, Long> {

    Page<Check> findByCheckStatus(CheckStatus checkStatus, Pageable pageable);
}
