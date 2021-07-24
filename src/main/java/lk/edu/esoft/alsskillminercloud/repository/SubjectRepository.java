package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByStreamId(Long streamId);

}
