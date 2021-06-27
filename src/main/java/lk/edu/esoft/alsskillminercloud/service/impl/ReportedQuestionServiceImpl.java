package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.CustomReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.ReportedQuestion;
import lk.edu.esoft.alsskillminercloud.entity.User;
import lk.edu.esoft.alsskillminercloud.repository.QuestionRepository;
import lk.edu.esoft.alsskillminercloud.repository.ReportedQuestionRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.ReportedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportedQuestionServiceImpl implements ReportedQuestionService {

    @Autowired
    private ReportedQuestionRepository reportedQuestionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveReportedQuestion(ReportedQuestionDTO reportedQuestionDTO) throws Exception {

        User user = userRepository.findById(reportedQuestionDTO.getUserName()).get();
        Question question = questionRepository.findById(reportedQuestionDTO.getQuestionID()).get();

        ReportedQuestion reportedQuestion = new ReportedQuestion(user, question, reportedQuestionDTO.getDate(),
                reportedQuestionDTO.getReason(), reportedQuestionDTO.getStatus());

        reportedQuestionRepository.save(reportedQuestion);

        return true;

    }

    @Override
    public ReportedQuestionDTO getReportedQuestion(long id) throws Exception {

        ReportedQuestion reportedQuestion = reportedQuestionRepository.findById(id).get();

        ReportedQuestionDTO reportedQuestionDTO = new ReportedQuestionDTO(reportedQuestion.getId(),
                reportedQuestion.getUser().getName(), reportedQuestion.getQuestion().getId(), reportedQuestion.getDate(),
                reportedQuestion.getReason(), reportedQuestion.getStatus());

        return reportedQuestionDTO;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteReportedQuestion(long id) throws Exception {

        reportedQuestionRepository.deleteById(id);

        return true;

    }

    @Override
    public ArrayList<ReportedQuestionDTO> getAllReportedQuestions() throws Exception {

        ArrayList<ReportedQuestionDTO> reportedQuestionDTOS = new ArrayList<>();

        List<ReportedQuestion> reportedQuestions = reportedQuestionRepository.findAll();

        for (ReportedQuestion reportedQuestion : reportedQuestions) {

            ReportedQuestionDTO reportedQuestionDTO = new ReportedQuestionDTO(reportedQuestion.getId(),
                    reportedQuestion.getUser().getName(), reportedQuestion.getQuestion().getId(), reportedQuestion.getDate(),
                    reportedQuestion.getReason(), reportedQuestion.getStatus());

            reportedQuestionDTOS.add(reportedQuestionDTO);
        }

        return reportedQuestionDTOS;

    }

    @Override
    public ArrayList<CustomReportedQuestionDTO> getReportedQuestions() throws Exception {
        ArrayList<Object[]> reportedQuestionsList = reportedQuestionRepository.getReportedQuestions();
        ArrayList<CustomReportedQuestionDTO> customReportedQuestionDTOList = new ArrayList<>();

        for (Object[] o : reportedQuestionsList) {
            CustomReportedQuestionDTO customReportedQuestionDTO = new CustomReportedQuestionDTO();
            customReportedQuestionDTO.setId(Long.parseLong(o[0].toString()));
            customReportedQuestionDTO.setUserName(o[1].toString());
            customReportedQuestionDTO.setUserprofileImageUrl(o[2].toString());
            customReportedQuestionDTO.setQuestionID(Long.parseLong(o[3].toString()));
            customReportedQuestionDTO.setQuestionTitle(o[4].toString());
            customReportedQuestionDTO.setQuestionBody(o[5].toString());
            customReportedQuestionDTO.setDate(o[6].toString());
            customReportedQuestionDTO.setReason(o[7].toString());
            customReportedQuestionDTO.setStatus((Integer) o[8]);

            customReportedQuestionDTOList.add(customReportedQuestionDTO);
        }

        return customReportedQuestionDTOList;
    }

}
