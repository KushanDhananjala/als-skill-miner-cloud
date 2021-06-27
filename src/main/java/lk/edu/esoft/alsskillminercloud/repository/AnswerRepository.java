package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT MAX(a.id) FROM Answer a")
    String getLastID();

    @Query(value = "SELECT a.id,a.question_id,a.answer,a.score,a.status,a.date,u.name AS userName,u.display_name,u.profile_image_url,b.name " +
            "FROM answer a, user u, badge b " +
            "WHERE a.status = 1 AND a.user_name = u.name AND u.badge_id = b.id AND lower(a.question_id)=lower(:questionID)", nativeQuery = true)
    ArrayList<Object[]> getQuestionAnswers(@Param("questionID") long questionID);

    @Query(value = "SELECT COUNT(a.id) FROM Answer a WHERE lower(a.user)=lower(:name) ")
    long getUserAnswersCount(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE Answer a SET a.score=:score \n" +
            "WHERE lower(a.id)=lower(:id)")
    void increseScore(@Param("id") long id, @Param("score") long score);

    @Modifying
    @Query(value = "UPDATE Answer a SET a.answer=:answer \n" +
            "WHERE lower(a.id)=lower(:id)")
    void updateAnswer(@Param("answer") String answer, @Param("id") long id);

    @Modifying
    @Query(value = "UPDATE Answer a SET a.status=:status \n" +
            "WHERE lower(a.id)=lower(:id)")
    void updateAnswerStatus(@Param("status") int status, @Param("id") long id);


}
