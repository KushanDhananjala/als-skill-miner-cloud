package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReviewReportedAnswerDTO;

import java.util.ArrayList;

public interface ReviewReportedAnswerService {

    boolean saveReviewAnswer(ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception;

    ReviewReportedAnswerDTO getReviewAnswer(long id) throws Exception;

    boolean deleteReviewAnswer(long id) throws Exception;

    ArrayList<ReviewReportedAnswerDTO> getAllReviewAnswers() throws Exception;

    boolean ignoreReportedAnswer(int status, long answerID,
                                 ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception;

    boolean editReportedAnswer(int status, AnswerDTO answerDTO,
                               ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception;

    boolean removeReportedAnswer(int status, long answerID,
                                 ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception;

}
