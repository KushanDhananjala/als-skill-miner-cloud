package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.AnswerAttachmentDTO;
import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.CustomAnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.PostAnswerDTO;
import lk.edu.esoft.alsskillminercloud.entity.Answer;
import lk.edu.esoft.alsskillminercloud.entity.AnswerAttachment;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.User;
import lk.edu.esoft.alsskillminercloud.repository.AnswerAttachmentRepository;
import lk.edu.esoft.alsskillminercloud.repository.AnswerRepository;
import lk.edu.esoft.alsskillminercloud.repository.QuestionRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.AnswerService;
import lk.edu.esoft.alsskillminercloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerAttachmentRepository answerAttachmentRepository;
    private final UserService userService;
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveAnswer(PostAnswerDTO postAnswerDTO) throws Exception {

        User user = userRepository.findById(postAnswerDTO.getAnswerDTO().getUserName()).get();

        Question question = questionRepository.findById(postAnswerDTO.getAnswerDTO().getQuestionID()).get();

        Answer answer = new Answer(user, question, postAnswerDTO.getAnswerDTO().getAnswer(),
                postAnswerDTO.getAnswerDTO().getScore(), postAnswerDTO.getAnswerDTO().getStatus(),
                postAnswerDTO.getAnswerDTO().getDate());

        answerRepository.save(answer);

        List<AnswerAttachmentDTO> answerAttachmentDTOS = postAnswerDTO.getAnswerAttachmentDTOList();

        if (answerAttachmentDTOS != null) {
            for (AnswerAttachmentDTO answerAttachmentDTO : answerAttachmentDTOS) {
                AnswerAttachment answerAttachment = new AnswerAttachment(answer, answerAttachmentDTO.getAttachmentUrl());
                answerAttachmentRepository.save(answerAttachment);
            }
        }

        long currentUserPoints = userRepository.getUserPoints(user.getName());
        userService.updateUserPoints(user.getName(), currentUserPoints + 5);

        sendEmailAnswerProvided(question, user, postAnswerDTO.getAnswerDTO().getAnswer());

        return true;

    }

    @Override
    public AnswerDTO getAnswer(long id) throws Exception {
        Answer answer = answerRepository.findById(id).get();
        AnswerDTO answerDTO = new AnswerDTO(answer.getId(), answer.getUser().getName(), answer.getQuestion().getId(),
                answer.getAnswer(), answer.getScore(), answer.getStatus(), answer.getDate());

        return answerDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteAnswer(long id) throws Exception {

        answerRepository.deleteById(id);

        return true;
    }

    @Override
    public ArrayList<CustomAnswerDTO> getQuestionAnswers(long questionID) throws Exception {

        ArrayList<Object[]> objectArrayList = answerRepository.getQuestionAnswers(questionID);
        ArrayList<CustomAnswerDTO> customAnswerDTOList = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            CustomAnswerDTO customAnswerDTO = new CustomAnswerDTO();
            customAnswerDTO.setAnswerID(((BigInteger) o[0]).longValue());
            customAnswerDTO.setQuestionID(((BigInteger) o[1]).longValue());
            customAnswerDTO.setAnswer(o[2].toString());
            customAnswerDTO.setScore(((BigInteger) o[3]).longValue());
            customAnswerDTO.setStatus((Integer) o[4]);
            customAnswerDTO.setDate(o[5].toString());
            customAnswerDTO.setUserName(o[6].toString());
            customAnswerDTO.setUserDisplayName(o[7].toString());
            customAnswerDTO.setUserProfileImageUrl(o[8].toString());
            customAnswerDTO.setUserBadgeName(o[9].toString());

            customAnswerDTOList.add(customAnswerDTO);
        }

        return customAnswerDTOList;
    }

    @Override
    public String getLastID() throws Exception {
        return answerRepository.getLastID();
    }

    @Override
    public long getTotalAnswers() throws Exception {
        return answerRepository.count();
    }

    @Override
    public long getUserAnswerCount(String name) throws Exception {
        return answerRepository.getUserAnswersCount(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean increseScore(AnswerDTO answerDTO) throws Exception {
        answerRepository.increseScore(answerDTO.getId(), answerDTO.getScore());

        long currentUserPoints = userRepository.getUserPoints(answerDTO.getUserName());
        userService.updateUserPoints(answerDTO.getUserName(), currentUserPoints + 1);

        return true;
    }

    public void sendEmailAnswerProvided(Question question, User user, String answer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(question.getUser().getEmail());
        message.setSubject("Answer submitted : " + question.getTitle());

        String msgBody = user.getName() + " has provided an answer for, \n\n" +
                question.getTitle() + "\n" +
                question.getBody() + "\n\n" +
                "The answer is : " + answer + "\n" +
                "Please visit http://localhost:4200/main/question?questionID=" + question.getId() + " for more details.";

        message.setText(msgBody);
        emailSender.send(message);
    }
}
