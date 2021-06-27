package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.CustomQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.PostQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.QuestionDTO;

import java.util.ArrayList;

public interface QuestionService {

    boolean saveQuestion(PostQuestionDTO postQuestionDTO) throws Exception;

    QuestionDTO getQuestion(long id) throws Exception;

    boolean deleteQuestion(long id) throws Exception;

    ArrayList<QuestionDTO> getAllQuestions() throws Exception;

    String getLastID() throws Exception;

    ArrayList<CustomQuestionDTO> getLandingPageQuestions() throws Exception;

    ArrayList<CustomQuestionDTO> getRecentQuestions() throws Exception;

    ArrayList<CustomQuestionDTO> getRecentFiveQuestions() throws Exception;

    ArrayList<CustomQuestionDTO> getMostlyViewedQuestions() throws Exception;

    ArrayList<CustomQuestionDTO> getMostlyVotedQuestions() throws Exception;

    long getUserQuestionCount(String name) throws Exception;

    boolean increseCounts(QuestionDTO questionDTO) throws Exception;

    long getTotalQuestions() throws Exception;

    boolean updateQuestionTitleAndBody(String title, String body, long id) throws Exception;

    boolean updateQuestionActiveStatus(int active, long id) throws Exception;

}
