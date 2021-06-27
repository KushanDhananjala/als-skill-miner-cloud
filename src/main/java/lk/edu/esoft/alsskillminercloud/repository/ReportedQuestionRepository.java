package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.ReportedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ReportedQuestionRepository extends JpaRepository<ReportedQuestion, Long> {

    @Query(value = "SELECT rq.id, rq.user_name, u.profile_image_url, rq.question_id, q.title, q.body, rq.date, rq.reason, rq.status \n" +
            "FROM reported_question rq, User u, Question q \n" +
            "WHERE rq.status=0 AND rq.user_name=u.name AND rq.question_id=q.id \n" +
            "ORDER BY (rq.date) ASC", nativeQuery = true)
    ArrayList<Object[]> getReportedQuestions();

    @Modifying
    @Query(value = "UPDATE ReportedQuestion rq SET rq.status=:status \n" +
            "WHERE lower(rq.question)=lower(:questionID)")
    void updateReportedQuestionStatus(@Param("questionID") long questionID, @Param("status") int status);

    @Query(value = "SELECT rq.user_name \n" +
            "FROM reported_question rq \n" +
            "WHERE lower(rq.question_id)=lower(:questionID)", nativeQuery = true)
    ArrayList<String> getReportedQuestionUsers(@Param("questionID") long questionID);

    @Query(value = "SELECT rq.id \n" +
            "FROM reported_question rq \n" +
            "WHERE lower(rq.question_id)=lower(:questionID)", nativeQuery = true)
    ArrayList<Object> getReportedQuestionIDs(@Param("questionID") long questionID);

}
