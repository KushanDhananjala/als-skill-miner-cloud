package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.QuestionVotesDTO;

import java.util.ArrayList;

public interface QuestionVoteService {

    boolean saveQuestionVote(QuestionVotesDTO questionVotesDTO) throws Exception;

    QuestionVotesDTO getQuestionVote(long id) throws Exception;

    boolean deleteQuestionVote(long id) throws Exception;

    ArrayList<QuestionVotesDTO> getAllQuestionVotes() throws Exception;

    long getUserQuestionVotesCount(String name, long questionID) throws Exception;

}
