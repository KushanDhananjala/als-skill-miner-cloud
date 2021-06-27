package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.QuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReviewReportedQuestionDTO;

import java.util.ArrayList;

public interface ReviewReportedQuestionService {

    boolean saveReviewQuestion(ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception;

    ReviewReportedQuestionDTO getReviewQuestion(long id) throws Exception;

    boolean deleteReviewQuestion(long id) throws Exception;

    ArrayList<ReviewReportedQuestionDTO> getAllReviewQuestions() throws Exception;

    boolean ignoreReportedQuestion(int status, long questionID,
                                   ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception;

    boolean editReportedQuestion(int status, QuestionDTO questionDTO,
                                 ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception;

    boolean removeReportedQuestion(int status, long questionID,
                                   ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception;
}
