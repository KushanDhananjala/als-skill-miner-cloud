package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.CustomReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.entity.Answer;
import lk.edu.esoft.alsskillminercloud.entity.ReportedAnswer;
import lk.edu.esoft.alsskillminercloud.entity.User;
import lk.edu.esoft.alsskillminercloud.repository.AnswerRepository;
import lk.edu.esoft.alsskillminercloud.repository.ReportedAnswerRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.ReportedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportedAnswerServiceImpl implements ReportedAnswerService {

    @Autowired
    private ReportedAnswerRepository reportedAnswerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveReportedAnswer(ReportedAnswerDTO reportedAnswerDTO) throws Exception {

        User user = userRepository.findById(reportedAnswerDTO.getUserName()).get();
        Answer answer = answerRepository.findById(reportedAnswerDTO.getAnswerID()).get();

        ReportedAnswer reportedAnswer = new ReportedAnswer(user, answer, reportedAnswerDTO.getDate(),
                reportedAnswerDTO.getReason(), reportedAnswerDTO.getStatus());

        reportedAnswerRepository.save(reportedAnswer);

        return true;
    }

    @Override
    public ReportedAnswerDTO getReportedAnswer(long id) throws Exception {
        ReportedAnswer reportedAnswer = reportedAnswerRepository.findById(id).get();

        ReportedAnswerDTO reportedAnswerDTO = new ReportedAnswerDTO(reportedAnswer.getId(),
                reportedAnswer.getUser().getName(), reportedAnswer.getAnswer().getId(), reportedAnswer.getDate(),
                reportedAnswer.getReason(), reportedAnswer.getStatus());

        return reportedAnswerDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteReportedAnswer(long id) throws Exception {

        reportedAnswerRepository.deleteById(id);

        return true;
    }

    @Override
    public ArrayList<ReportedAnswerDTO> getAllReportedAnswers() throws Exception {

        ArrayList<ReportedAnswerDTO> reportedAnswerDTOS = new ArrayList<>();

        List<ReportedAnswer> reportedAnswers = reportedAnswerRepository.findAll();

        for (ReportedAnswer reportedAnswer : reportedAnswers) {

            ReportedAnswerDTO reportedAnswerDTO = new ReportedAnswerDTO(reportedAnswer.getId(),
                    reportedAnswer.getUser().getName(), reportedAnswer.getAnswer().getId(), reportedAnswer.getDate(),
                    reportedAnswer.getReason(), reportedAnswer.getStatus());

            reportedAnswerDTOS.add(reportedAnswerDTO);
        }

        return reportedAnswerDTOS;
    }

    @Override
    public ArrayList<CustomReportedAnswerDTO> getReportedAnswers() throws Exception {

        ArrayList<Object[]> reportedAnswersList = reportedAnswerRepository.getReportedAnswers();
        ArrayList<CustomReportedAnswerDTO> customReportedAnswerDTOList = new ArrayList<>();

        for (Object[] o : reportedAnswersList) {
            CustomReportedAnswerDTO customReportedAnswerDTO = new CustomReportedAnswerDTO();
            customReportedAnswerDTO.setId(Long.parseLong(o[0].toString()));
            customReportedAnswerDTO.setUserName(o[1].toString());
            customReportedAnswerDTO.setUserProfileImageUrl(o[2].toString());
            customReportedAnswerDTO.setAnswerID(Long.parseLong(o[3].toString()));
            customReportedAnswerDTO.setAnswer(o[4].toString());
            customReportedAnswerDTO.setDate(o[5].toString());
            customReportedAnswerDTO.setReason(o[6].toString());
            customReportedAnswerDTO.setStatus((Integer) o[7]);
            customReportedAnswerDTO.setQuestionID(Long.parseLong(o[8].toString()));
            customReportedAnswerDTO.setQuestion(o[9].toString());

            customReportedAnswerDTOList.add(customReportedAnswerDTO);
        }

        return customReportedAnswerDTOList;

    }

}
