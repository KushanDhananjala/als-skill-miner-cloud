package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.AnswerVoteDTO;

import java.util.ArrayList;

public interface AnswerVoteService {

    boolean saveAnswerVote(AnswerVoteDTO answerVoteDTO) throws Exception;

    AnswerVoteDTO getAnswerVote(long id) throws Exception;

    boolean deleteAnswerVote(long id) throws Exception;

    ArrayList<AnswerVoteDTO> getAllAnswerVotes() throws Exception;

    long getUserAnswerVotesCount(String name, long answerID) throws Exception;

}
