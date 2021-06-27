package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.CustomReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReportedQuestionDTO;

import java.util.ArrayList;

public interface ReportedQuestionService {

    boolean saveReportedQuestion(ReportedQuestionDTO reportedQuestionDTO) throws Exception;

    ReportedQuestionDTO getReportedQuestion(long id) throws Exception;

    boolean deleteReportedQuestion(long id) throws Exception;

    ArrayList<ReportedQuestionDTO> getAllReportedQuestions() throws Exception;

    ArrayList<CustomReportedQuestionDTO> getReportedQuestions() throws Exception;


}
