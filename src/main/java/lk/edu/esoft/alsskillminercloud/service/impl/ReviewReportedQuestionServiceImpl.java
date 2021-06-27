package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.QuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReviewReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.entity.Admin;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.ReportedQuestion;
import lk.edu.esoft.alsskillminercloud.entity.ReviewReportedQuestion;
import lk.edu.esoft.alsskillminercloud.repository.*;
import lk.edu.esoft.alsskillminercloud.service.ReviewReportedQuestionService;
import lk.edu.esoft.alsskillminercloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ReviewReportedQuestionServiceImpl implements ReviewReportedQuestionService {

    @Autowired
    private ReviewReportedQuestionRepository reviewReportedQuestionRepository;
    @Autowired
    private ReportedQuestionRepository reportedQuestionRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserService userService;

    @Override
    public boolean saveReviewQuestion(ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception {

        ReportedQuestion reportedQuestion = reportedQuestionRepository.findById(reviewReportedQuestionDTO.getReportedID()).get();
        Admin admin = adminRepository.findById(reviewReportedQuestionDTO.getAdminName()).get();

        ReviewReportedQuestion reviewReportedQuestion = new ReviewReportedQuestion(reportedQuestion, admin,
                reviewReportedQuestionDTO.getDate(), reviewReportedQuestionDTO.getAction());

        reviewReportedQuestionRepository.save(reviewReportedQuestion);

        return true;
    }

    @Override
    public ReviewReportedQuestionDTO getReviewQuestion(long id) throws Exception {

        ReviewReportedQuestion reviewReportedQuestion = reviewReportedQuestionRepository.findById(id).get();
        ReviewReportedQuestionDTO reviewReportedQuestionDTO = new ReviewReportedQuestionDTO(reviewReportedQuestion.getId(),
                reviewReportedQuestion.getReportedQuestion().getId(), reviewReportedQuestion.getAdmin().getName(),
                reviewReportedQuestion.getDate(), reviewReportedQuestion.getAction());

        return reviewReportedQuestionDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteReviewQuestion(long id) throws Exception {

        reviewReportedQuestionRepository.deleteById(id);
        return true;
    }

    @Override
    public ArrayList<ReviewReportedQuestionDTO> getAllReviewQuestions() throws Exception {

        List<ReviewReportedQuestion> reviewReportedQuestionList = reviewReportedQuestionRepository.findAll();
        ArrayList<ReviewReportedQuestionDTO> reviewReportedQuestionDTOS = new ArrayList<>();

        for (ReviewReportedQuestion reviewReportedQuestion : reviewReportedQuestionList) {
            ReviewReportedQuestionDTO reviewReportedQuestionDTO = new ReviewReportedQuestionDTO(reviewReportedQuestion.getId(),
                    reviewReportedQuestion.getReportedQuestion().getId(), reviewReportedQuestion.getAdmin().getName(),
                    reviewReportedQuestion.getDate(), reviewReportedQuestion.getAction());

            reviewReportedQuestionDTOS.add(reviewReportedQuestionDTO);
        }

        return reviewReportedQuestionDTOS;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean ignoreReportedQuestion(int status, long questionID,
                                          ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception {

        Admin admin = adminRepository.findById(reviewReportedQuestionDTO.getAdminName()).get();

        List<Object> reportedQuestionIDs = reportedQuestionRepository.getReportedQuestionIDs(questionID);
        for (Object id : reportedQuestionIDs) {
            ReportedQuestion reportedQuestion = reportedQuestionRepository.findById(Long.parseLong(id.toString())).get();
            ReviewReportedQuestion reviewReportedQuestion = new ReviewReportedQuestion(reportedQuestion, admin,
                    reviewReportedQuestionDTO.getDate(), reviewReportedQuestionDTO.getAction());
            reviewReportedQuestionRepository.save(reviewReportedQuestion);
        }

        reportedQuestionRepository.updateReportedQuestionStatus(questionID, status);

        List<String> reportedUserNames = reportedQuestionRepository.getReportedQuestionUsers(questionID);
        for (String reportedUserName : reportedUserNames) {
            long currentUserPoints = userRepository.getUserPoints(reportedUserName);
            userService.updateUserPoints(reportedUserName, currentUserPoints - 4);
        }

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean editReportedQuestion(int status, QuestionDTO questionDTO,
                                        ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception {

        Admin admin = adminRepository.findById(reviewReportedQuestionDTO.getAdminName()).get();

        List<Object> reportedQuestionIDs = reportedQuestionRepository.getReportedQuestionIDs(questionDTO.getId());
        for (Object reprtedQuestionID : reportedQuestionIDs) {
            ReportedQuestion reportedQuestion = reportedQuestionRepository.findById(Long.parseLong(reprtedQuestionID.toString())).get();
            ReviewReportedQuestion reviewReportedQuestion = new ReviewReportedQuestion(reportedQuestion, admin,
                    reviewReportedQuestionDTO.getDate(), reviewReportedQuestionDTO.getAction());
            reviewReportedQuestionRepository.save(reviewReportedQuestion);
        }

        reportedQuestionRepository.updateReportedQuestionStatus(questionDTO.getId(), status);

        List<String> reportedUserNames = reportedQuestionRepository.getReportedQuestionUsers(questionDTO.getId());
        for (String reportedUserName : reportedUserNames) {
            long currentUserPoints = userRepository.getUserPoints(reportedUserName);
            userService.updateUserPoints(reportedUserName, currentUserPoints + 3);
        }

        questionRepository.updateQuestionTitleAndBody(questionDTO.getTitle(), questionDTO.getBody(), questionDTO.getId());

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeReportedQuestion(int status, long questionID,
                                          ReviewReportedQuestionDTO reviewReportedQuestionDTO) throws Exception {

        Admin admin = adminRepository.findById(reviewReportedQuestionDTO.getAdminName()).get();

        List<Object> reportedQuestionIDs = reportedQuestionRepository.getReportedQuestionIDs(questionID);
        for (Object reprtedQuestionID : reportedQuestionIDs) {
            ReportedQuestion reportedQuestion = reportedQuestionRepository.findById(Long.parseLong(reprtedQuestionID.toString())).get();
            ReviewReportedQuestion reviewReportedQuestion = new ReviewReportedQuestion(reportedQuestion, admin,
                    reviewReportedQuestionDTO.getDate(), reviewReportedQuestionDTO.getAction());
            reviewReportedQuestionRepository.save(reviewReportedQuestion);
        }

        reportedQuestionRepository.updateReportedQuestionStatus(questionID, status);

        List<String> reportedUserNames = reportedQuestionRepository.getReportedQuestionUsers(questionID);
        for (String reportedUserName : reportedUserNames) {
            long currentUserPoints = userRepository.getUserPoints(reportedUserName);
            userService.updateUserPoints(reportedUserName, currentUserPoints + 3);
        }

        Question question = questionRepository.findById(questionID).get();
        long currentUserPoints = userRepository.getUserPoints(question.getUser().getName());
        userService.updateUserPoints(question.getUser().getName(), currentUserPoints - 8);

        questionRepository.updateQuestionActiveStatus(0, questionID);

        return true;
    }

}
