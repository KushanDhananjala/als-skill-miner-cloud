package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.CustomAnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.PostAnswerDTO;

import java.util.ArrayList;

public interface AnswerService {

    boolean saveAnswer(PostAnswerDTO postAnswerDTO) throws Exception;

    AnswerDTO getAnswer(long id) throws Exception;

    boolean deleteAnswer(long id) throws Exception;

    ArrayList<CustomAnswerDTO> getQuestionAnswers(long questionID) throws Exception;

    String getLastID() throws Exception;

    long getTotalAnswers() throws Exception;

    long getUserAnswerCount(String name) throws Exception;

    boolean increseScore(AnswerDTO answerDTO) throws Exception;

}
