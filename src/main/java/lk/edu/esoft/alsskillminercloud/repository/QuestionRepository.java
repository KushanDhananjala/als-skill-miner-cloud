package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT MAX(q.id) FROM Question q")
    String getLastID();

    @Query(value = "SELECT u.name,u.displayName,u.profileImageUrl,b.name,q.id,q.title,q.body,q.creationDate,q.answersCount,q.viewsCount,q.votesCount,q.active\n" +
            "FROM User u,Badge b,Question q \n" +
            "WHERE u.badge=b.id AND q.user=u.name AND q.active=1 \n" +
            "ORDER BY RAND()")
    ArrayList<Object[]> getLandingPageQuestions();

    @Query(value = "SELECT u.name,u.displayName,u.profileImageUrl,b.name,q.id,q.title,q.body,q.creationDate,q.answersCount,q.viewsCount,q.votesCount,q.active\n" +
            "FROM User u,Badge b,Question q \n" +
            "WHERE u.badge=b.id AND q.user=u.name AND q.active=1 \n" +
            "ORDER BY (q.id) DESC")
    ArrayList<Object[]> getRecentQuestions();

    @Query(value = "SELECT u.name,u.displayName,u.profileImageUrl,b.name,q.id,q.title,q.body,q.creationDate,q.answersCount,q.viewsCount,q.votesCount,q.active\n" +
            "FROM User u,Badge b,Question q \n" +
            "WHERE u.badge=b.id AND q.user=u.name AND q.active=1 \n" +
            "ORDER BY (q.id) DESC")
    ArrayList<Object[]> getRecentFiveQuestions(Pageable pageable);

    @Query(value = "SELECT u.name,u.displayName,u.profileImageUrl,b.name,q.id,q.title,q.body,q.creationDate,q.answersCount,q.viewsCount,q.votesCount,q.active\n" +
            "FROM User u,Badge b,Question q \n" +
            "WHERE u.badge=b.id AND q.user=u.name AND q.active=1 \n" +
            "ORDER BY (q.viewsCount) DESC")
    ArrayList<Object[]> getMostlyViewedQuestions();

    @Query(value = "SELECT u.name,u.displayName,u.profileImageUrl,b.name,q.id,q.title,q.body,q.creationDate,q.answersCount,q.viewsCount,q.votesCount,q.active\n" +
            "FROM User u,Badge b,Question q \n" +
            "WHERE u.badge=b.id AND q.user=u.name AND q.active=1 \n" +
            "ORDER BY (q.votesCount) DESC")
    ArrayList<Object[]> getMostlyVotedQuestions();

    @Query(value = "SELECT COUNT(q.id) FROM Question q WHERE lower(q.user)=lower(:name) ")
    long getUserQuestionCount(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE Question q SET q.answersCount=:answersCount, \n" +
            "q.viewsCount=:viewsCount, q.votesCount=:votesCount\n" +
            "WHERE lower(q.id)=lower(:id)")
    void increseCounts(@Param("answersCount") long answersCount, @Param("viewsCount") long viewsCount, @Param("votesCount") long votesCount, @Param("id") long id);

    @Modifying
    @Query(value = "UPDATE Question q SET q.title=:title, q.body=:body \n" +
            "WHERE lower(q.id)=lower(:id)")
    void updateQuestionTitleAndBody(@Param("title") String title, @Param("body") String body, @Param("id") long id);

    @Modifying
    @Query(value = "UPDATE Question q SET q.active=:active \n" +
            "WHERE lower(q.id)=lower(:id)")
    void updateQuestionActiveStatus(@Param("active") int active, @Param("id") long id);

}
