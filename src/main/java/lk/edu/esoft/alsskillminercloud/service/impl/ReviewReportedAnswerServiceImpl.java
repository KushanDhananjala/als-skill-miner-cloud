package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReviewReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.entity.Admin;
import lk.edu.esoft.alsskillminercloud.entity.Answer;
import lk.edu.esoft.alsskillminercloud.entity.ReportedAnswer;
import lk.edu.esoft.alsskillminercloud.entity.ReviewReportedAnswer;
import lk.edu.esoft.alsskillminercloud.repository.*;
import lk.edu.esoft.alsskillminercloud.service.ReviewReportedAnswerService;
import lk.edu.esoft.alsskillminercloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ReviewReportedAnswerServiceImpl implements ReviewReportedAnswerService {

    @Autowired
    private ReviewReportedAnswerRepository reviewReportedAnswerRepository;
    @Autowired
    private ReportedAnswerRepository reportedAnswerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveReviewAnswer(ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception {

        ReportedAnswer reportedAnswer = reportedAnswerRepository.findById(reviewReportedAnswerDTO.getReportedID()).get();
        Admin admin = adminRepository.findById(reviewReportedAnswerDTO.getAdminName()).get();

        ReviewReportedAnswer reviewReportedAnswer = new ReviewReportedAnswer(reportedAnswer, admin,
                reviewReportedAnswerDTO.getDate(), reviewReportedAnswerDTO.getAction());

        reviewReportedAnswerRepository.save(reviewReportedAnswer);

        return true;
    }

    @Override
    public ReviewReportedAnswerDTO getReviewAnswer(long id) throws Exception {

        ReviewReportedAnswer reviewReportedAnswer = reviewReportedAnswerRepository.findById(id).get();
        ReviewReportedAnswerDTO reviewReportedAnswerDTO = new ReviewReportedAnswerDTO(reviewReportedAnswer.getId(),
                reviewReportedAnswer.getReportedAnswer().getId(), reviewReportedAnswer.getAdmin().getName(),
                reviewReportedAnswer.getDate(), reviewReportedAnswer.getAction());

        return reviewReportedAnswerDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteReviewAnswer(long id) {

        reviewReportedAnswerRepository.deleteById(id);
        return true;
    }

    @Override
    public ArrayList<ReviewReportedAnswerDTO> getAllReviewAnswers() throws Exception {

        List<ReviewReportedAnswer> reviewReportedAnswerList = reviewReportedAnswerRepository.findAll();
        ArrayList<ReviewReportedAnswerDTO> reviewReportedAnswerDTOS = new ArrayList<>();

        for (ReviewReportedAnswer reviewReportedAnswer : reviewReportedAnswerList) {
            ReviewReportedAnswerDTO reviewReportedAnswerDTO = new ReviewReportedAnswerDTO(reviewReportedAnswer.getId(),
                    reviewReportedAnswer.getReportedAnswer().getId(), reviewReportedAnswer.getAdmin().getName(),
                    reviewReportedAnswer.getDate(), reviewReportedAnswer.getAction());

            reviewReportedAnswerDTOS.add(reviewReportedAnswerDTO);
        }

        return reviewReportedAnswerDTOS;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean ignoreReportedAnswer(int status, long answerID, ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception {

        Admin admin = adminRepository.findById(reviewReportedAnswerDTO.getAdminName()).get();

        List<Object> reportedAnswerIDs = reportedAnswerRepository.getReportedAnswerIDs(answerID);
        for (Object id : reportedAnswerIDs) {
            ReportedAnswer reportedAnswer = reportedAnswerRepository.findById(Long.parseLong(id.toString())).get();
            ReviewReportedAnswer reviewReportedAnswer = new ReviewReportedAnswer(reportedAnswer, admin,
                    reviewReportedAnswerDTO.getDate(), reviewReportedAnswerDTO.getAction());

            reviewReportedAnswerRepository.save(reviewReportedAnswer);
        }

        reportedAnswerRepository.updateReportedAnswerStatus(answerID, status);

        List<String> reportedUserNames = reportedAnswerRepository.getReportedAnswerUsers(answerID);
        for (String reportedUserName : reportedUserNames) {
            long currentUserPoints = userRepository.getUserPoints(reportedUserName);
            userService.updateUserPoints(reportedUserName, currentUserPoints - 4);
        }

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean editReportedAnswer(int status, AnswerDTO answerDTO, ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception {

        Admin admin = adminRepository.findById(reviewReportedAnswerDTO.getAdminName()).get();

        List<Object> reportedAnswerIDs = reportedAnswerRepository.getReportedAnswerIDs(answerDTO.getId());
        for (Object id : reportedAnswerIDs) {
            ReportedAnswer reportedAnswer = reportedAnswerRepository.findById(Long.parseLong(id.toString())).get();
            ReviewReportedAnswer reviewReportedAnswer = new ReviewReportedAnswer(reportedAnswer, admin,
                    reviewReportedAnswerDTO.getDate(), reviewReportedAnswerDTO.getAction());

            reviewReportedAnswerRepository.save(reviewReportedAnswer);
        }

        reportedAnswerRepository.updateReportedAnswerStatus(answerDTO.getId(), status);

        List<String> reportedUserNames = reportedAnswerRepository.getReportedAnswerUsers(answerDTO.getId());
        for (String reportedUserName : reportedUserNames) {
            long currentUserPoints = userRepository.getUserPoints(reportedUserName);
            userService.updateUserPoints(reportedUserName, currentUserPoints + 3);
        }

        answerRepository.updateAnswer(answerDTO.getAnswer(), answerDTO.getId());

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeReportedAnswer(int status, long answerID, ReviewReportedAnswerDTO reviewReportedAnswerDTO) throws Exception {

        Admin admin = adminRepository.findById(reviewReportedAnswerDTO.getAdminName()).get();

        List<Object> reportedAnswerIDs = reportedAnswerRepository.getReportedAnswerIDs(answerID);
        for (Object id : reportedAnswerIDs) {
            ReportedAnswer reportedAnswer = reportedAnswerRepository.findById(Long.parseLong(id.toString())).get();
            ReviewReportedAnswer reviewReportedAnswer = new ReviewReportedAnswer(reportedAnswer, admin,
                    reviewReportedAnswerDTO.getDate(), reviewReportedAnswerDTO.getAction());

            reviewReportedAnswerRepository.save(reviewReportedAnswer);
        }

        reportedAnswerRepository.updateReportedAnswerStatus(answerID, status);

        List<String> reportedUserNames = reportedAnswerRepository.getReportedAnswerUsers(answerID);
        for (String reportedUserName : reportedUserNames) {
            long currentUserPoints = userRepository.getUserPoints(reportedUserName);
            userService.updateUserPoints(reportedUserName, currentUserPoints + 3);
        }

        Answer answer = answerRepository.findById(answerID).get();
        long currentUserPoints = userRepository.getUserPoints(answer.getUser().getName());
        userService.updateUserPoints(answer.getUser().getName(), currentUserPoints - 8);

        answerRepository.updateAnswerStatus(0, answerID);

        return true;
    }

}

