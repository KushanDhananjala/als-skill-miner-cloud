package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.ReportedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ReportedAnswerRepository extends JpaRepository<ReportedAnswer, Long> {

    @Query(value = "SELECT ra.id, ra.user.name, u.profileImageUrl, ra.answer.id, a.answer, ra.date, ra.reason, ra.status, a.question.id, q.title \n" +
            "FROM ReportedAnswer ra, Answer a, User u, Question q \n" +
            "WHERE ra.status=0 AND ra.user.name=u.name AND ra.answer.id=a.id AND a.question.id=q.id \n" +
            "ORDER BY (ra.date) ASC")
    ArrayList<Object[]> getReportedAnswers();

    @Modifying
    @Query(value = "UPDATE ReportedAnswer ra SET ra.status=:status \n" +
            "WHERE lower(ra.answer)=lower(:answerID)")
    void updateReportedAnswerStatus(@Param("answerID") long answerID, @Param("status") int status);

    @Query(value = "SELECT ra.id \n" +
            "FROM reported_answer ra \n" +
            "WHERE lower(ra.answer_id)=lower(:answerID)", nativeQuery = true)
    ArrayList<Object> getReportedAnswerIDs(@Param("answerID") long answerID);

    @Query(value = "SELECT ra.user_name \n" +
            "FROM reported_answer ra \n" +
            "WHERE lower(ra.answer_id)=lower(:answerID)", nativeQuery = true)
    ArrayList<String> getReportedAnswerUsers(@Param("answerID") long answerID);

}
