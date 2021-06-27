package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.TagDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface TagDetailRepository extends JpaRepository<TagDetail, Long> {

    @Query(value = "SELECT td.questionid, td.tagid, t.name\n" +
            "FROM question q, tag_detail td, Tag t\n" +
            "WHERE q.id = td.questionid AND td.tagid=t.id", nativeQuery = true)
    ArrayList<Object[]> getAllQuestionsTags();

}
