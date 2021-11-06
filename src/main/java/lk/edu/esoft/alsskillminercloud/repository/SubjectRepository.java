package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT \n" +
            "   s.id, \n" +
            "   s.subject \n" +
            "FROM \n" +
            "   Subject s,StreamSubjectDetail ssd \n" +
            "WHERE \n" +
            "   s.id = ssd.subject.id AND ssd.stream = lower(:streamId) \n" +
            "GROUP BY s.id")
    ArrayList<Object[]> getAllByStreamId(@Param("streamId") Long streamId);

}
