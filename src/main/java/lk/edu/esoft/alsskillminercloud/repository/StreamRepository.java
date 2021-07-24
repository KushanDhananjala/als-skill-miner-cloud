package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {
}
