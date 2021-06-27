package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {

    @Query(value = "SELECT COUNT(av.id) \n" +
            "FROM AnswerVote av \n" +
            "WHERE lower(av.user)=lower(:name) AND lower(av.question)=lower(:answerID)")
    long getUserQuestionVotesCount(@Param("name") String name, @Param("answerID") long answerID);

}
