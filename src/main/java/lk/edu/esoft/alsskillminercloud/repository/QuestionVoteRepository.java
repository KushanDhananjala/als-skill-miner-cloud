package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {

    @Query(value = "SELECT COUNT(qv.id) \n" +
            "FROM QuestionVote qv \n" +
            "WHERE lower(qv.user)=lower(:name) AND lower(qv.question)=lower(:questionID)")
    long getUserQuestionVotesCount(@Param("name") String name, @Param("questionID") long questionID);

}
