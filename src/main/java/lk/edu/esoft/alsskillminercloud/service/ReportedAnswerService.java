package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.CustomReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReportedAnswerDTO;

import java.util.ArrayList;

public interface ReportedAnswerService {

    boolean saveReportedAnswer(ReportedAnswerDTO reportedAnswerDTO) throws Exception;

    ReportedAnswerDTO getReportedAnswer(long id) throws Exception;

    boolean deleteReportedAnswer(long id) throws Exception;

    ArrayList<ReportedAnswerDTO> getAllReportedAnswers() throws Exception;

    ArrayList<CustomReportedAnswerDTO> getReportedAnswers() throws Exception;

}
