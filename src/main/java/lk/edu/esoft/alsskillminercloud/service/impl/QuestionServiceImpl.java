package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.*;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.QuestionAttachment;
import lk.edu.esoft.alsskillminercloud.entity.TagDetail;
import lk.edu.esoft.alsskillminercloud.repository.QuestionAttachmentRepository;
import lk.edu.esoft.alsskillminercloud.repository.QuestionRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.QuestionService;
import lk.edu.esoft.alsskillminercloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionAttachmentRepository questionAttachmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveQuestion(PostQuestionDTO postQuestionDTO) throws Exception {

        Question question = new Question();
        question.setId(postQuestionDTO.getQuestionDTO().getId());
        question.setTitle(postQuestionDTO.getQuestionDTO().getTitle());
        question.setBody(postQuestionDTO.getQuestionDTO().getBody());
        question.setCreationDate(postQuestionDTO.getQuestionDTO().getCreationDate());
        question.setVotesCount(postQuestionDTO.getQuestionDTO().getVotesCount());
        question.setAnswersCount(postQuestionDTO.getQuestionDTO().getAnswersCount());
        question.setViewsCount(postQuestionDTO.getQuestionDTO().getViewsCount());
        question.setActive(postQuestionDTO.getQuestionDTO().getActive());
        question.setUser(userRepository.findById(postQuestionDTO.getQuestionDTO().getUserName()).get());

        List<TagDetailDTO> tagDTOList = postQuestionDTO.getQuestionDTO().getTagDetailDTOList();
        ArrayList<TagDetail> tagDetails = new ArrayList<>();

        long questionID = 0;

        if (questionRepository.getLastID() != null) {
            questionID = Long.parseLong(questionRepository.getLastID()) + 1;
        }

        for (TagDetailDTO tagDetailDTO : tagDTOList) {
            TagDetail tagDetail = new TagDetail(questionID, tagDetailDTO.getTagID());
            tagDetails.add(tagDetail);
        }

        question.setTagDetailList(tagDetails);

        questionRepository.save(question);

        List<QuestionAttachmentDTO> questionAttachmentDTOS = postQuestionDTO.getQuestionAttachmentDTOList();

        boolean addAttchment = false;

        if (questionAttachmentDTOS != null) {
            for (QuestionAttachmentDTO questionAttachmentDTO : questionAttachmentDTOS) {
                QuestionAttachment questionAttachment = new QuestionAttachment(question, questionAttachmentDTO.getAttachmentUrl());
                questionAttachmentRepository.save(questionAttachment);
            }
        }

        long currentUserPoints = userRepository.getUserPoints(postQuestionDTO.getQuestionDTO().getUserName());

        userService.updateUserPoints(postQuestionDTO.getQuestionDTO().getUserName(), currentUserPoints + 3);

        return true;

    }

    @Override
    public QuestionDTO getQuestion(long id) throws Exception {

        Question question = questionRepository.findById(id).get();

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setBody(question.getBody());
        questionDTO.setCreationDate(question.getCreationDate());
        questionDTO.setVotesCount(question.getVotesCount());
        questionDTO.setAnswersCount(question.getAnswersCount());
        questionDTO.setViewsCount(question.getViewsCount());
        questionDTO.setActive(question.getActive());
        questionDTO.setUserName(question.getUser().getName());

//        List<Tag> tags = question.getTagList();
//        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
//
//        for (Tag tag : tags) {
//            TagDTO tagDTO = tagRepository.findById(tag.getId()).get();
//            tagDTOS.add(tagDTO);
//        }

        questionDTO.setTagDetailDTOList(null);

        return questionDTO;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteQuestion(long id) throws Exception {

        questionRepository.deleteById(id);

        return true;

    }

    @Override
    public ArrayList<QuestionDTO> getAllQuestions() throws Exception {

        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();

        List<Question> questions = questionRepository.findAll();

        for (Question question : questions) {

            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setTitle(question.getTitle());
            questionDTO.setBody(question.getBody());
            questionDTO.setCreationDate(question.getCreationDate());
            questionDTO.setVotesCount(question.getVotesCount());
            questionDTO.setAnswersCount(question.getAnswersCount());
            questionDTO.setViewsCount(question.getViewsCount());
            questionDTO.setActive(question.getActive());
            questionDTO.setUserName(question.getUser().getName());

            questionDTO.setTagDetailDTOList(null);

            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }

    @Override
    public String getLastID() throws Exception {
        return questionRepository.getLastID();
    }

    @Override
    public ArrayList<CustomQuestionDTO> getLandingPageQuestions() throws Exception {

        ArrayList<Object[]> objectArrayList = questionRepository.getLandingPageQuestions();
        ArrayList<CustomQuestionDTO> customQuestionDTOList = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            CustomQuestionDTO customQuestionDTO = new CustomQuestionDTO();
            customQuestionDTO.setUserName(o[0].toString());
            customQuestionDTO.setUserDisplyName(o[1].toString());
            customQuestionDTO.setUserprofileImageUrl(o[2].toString());
            customQuestionDTO.setBadgeName(o[3].toString());
            customQuestionDTO.setQuestionID((Long) o[4]);
            customQuestionDTO.setQuestionTitle(o[5].toString());
            customQuestionDTO.setQuestionBody(o[6].toString());
            customQuestionDTO.setQuestionCreationDate(o[7].toString());
            customQuestionDTO.setQuestionAnswersCount((Long) o[8]);
            customQuestionDTO.setQuestionViewsCount((Long) o[9]);
            customQuestionDTO.setQuestionVotesCount((Long) o[10]);
            customQuestionDTO.setQuestionActive((Integer) o[11]);

            customQuestionDTOList.add(customQuestionDTO);
        }

        return customQuestionDTOList;
    }

    @Override
    public ArrayList<CustomQuestionDTO> getRecentQuestions() throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getRecentQuestions();
        ArrayList<CustomQuestionDTO> customQuestionDTOList = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            CustomQuestionDTO customQuestionDTO = new CustomQuestionDTO();
            customQuestionDTO.setUserName(o[0].toString());
            customQuestionDTO.setUserDisplyName(o[1].toString());
            customQuestionDTO.setUserprofileImageUrl(o[2].toString());
            customQuestionDTO.setBadgeName(o[3].toString());
            customQuestionDTO.setQuestionID((Long) o[4]);
            customQuestionDTO.setQuestionTitle(o[5].toString());
            customQuestionDTO.setQuestionBody(o[6].toString());
            customQuestionDTO.setQuestionCreationDate(o[7].toString());
            customQuestionDTO.setQuestionAnswersCount((Long) o[8]);
            customQuestionDTO.setQuestionViewsCount((Long) o[9]);
            customQuestionDTO.setQuestionVotesCount((Long) o[10]);
            customQuestionDTO.setQuestionActive((Integer) o[11]);

            customQuestionDTOList.add(customQuestionDTO);
        }

        return customQuestionDTOList;
    }

    @Override
    public ArrayList<CustomQuestionDTO> getRecentFiveQuestions() throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getRecentFiveQuestions(PageRequest.of(0, 5));
        ArrayList<CustomQuestionDTO> customQuestionDTOList = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            CustomQuestionDTO customQuestionDTO = new CustomQuestionDTO();
            customQuestionDTO.setUserName(o[0].toString());
            customQuestionDTO.setUserDisplyName(o[1].toString());
            customQuestionDTO.setUserprofileImageUrl(o[2].toString());
            customQuestionDTO.setBadgeName(o[3].toString());
            customQuestionDTO.setQuestionID((Long) o[4]);
            customQuestionDTO.setQuestionTitle(o[5].toString());
            customQuestionDTO.setQuestionBody(o[6].toString());
            customQuestionDTO.setQuestionCreationDate(o[7].toString());
            customQuestionDTO.setQuestionAnswersCount((Long) o[8]);
            customQuestionDTO.setQuestionViewsCount((Long) o[9]);
            customQuestionDTO.setQuestionVotesCount((Long) o[10]);
            customQuestionDTO.setQuestionActive((Integer) o[11]);

            customQuestionDTOList.add(customQuestionDTO);
        }

        return customQuestionDTOList;
    }

    @Override
    public ArrayList<CustomQuestionDTO> getMostlyViewedQuestions() throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getMostlyViewedQuestions();
        ArrayList<CustomQuestionDTO> customQuestionDTOList = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            CustomQuestionDTO customQuestionDTO = new CustomQuestionDTO();
            customQuestionDTO.setUserName(o[0].toString());
            customQuestionDTO.setUserDisplyName(o[1].toString());
            customQuestionDTO.setUserprofileImageUrl(o[2].toString());
            customQuestionDTO.setBadgeName(o[3].toString());
            customQuestionDTO.setQuestionID((Long) o[4]);
            customQuestionDTO.setQuestionTitle(o[5].toString());
            customQuestionDTO.setQuestionBody(o[6].toString());
            customQuestionDTO.setQuestionCreationDate(o[7].toString());
            customQuestionDTO.setQuestionAnswersCount((Long) o[8]);
            customQuestionDTO.setQuestionViewsCount((Long) o[9]);
            customQuestionDTO.setQuestionVotesCount((Long) o[10]);
            customQuestionDTO.setQuestionActive((Integer) o[11]);

            customQuestionDTOList.add(customQuestionDTO);
        }

        return customQuestionDTOList;
    }

    @Override
    public ArrayList<CustomQuestionDTO> getMostlyVotedQuestions() throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getMostlyVotedQuestions();
        ArrayList<CustomQuestionDTO> customQuestionDTOList = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            CustomQuestionDTO customQuestionDTO = new CustomQuestionDTO();
            customQuestionDTO.setUserName(o[0].toString());
            customQuestionDTO.setUserDisplyName(o[1].toString());
            customQuestionDTO.setUserprofileImageUrl(o[2].toString());
            customQuestionDTO.setBadgeName(o[3].toString());
            customQuestionDTO.setQuestionID((Long) o[4]);
            customQuestionDTO.setQuestionTitle(o[5].toString());
            customQuestionDTO.setQuestionBody(o[6].toString());
            customQuestionDTO.setQuestionCreationDate(o[7].toString());
            customQuestionDTO.setQuestionAnswersCount((Long) o[8]);
            customQuestionDTO.setQuestionViewsCount((Long) o[9]);
            customQuestionDTO.setQuestionVotesCount((Long) o[10]);
            customQuestionDTO.setQuestionActive((Integer) o[11]);

            customQuestionDTOList.add(customQuestionDTO);
        }

        return customQuestionDTOList;
    }

    @Override
    public long getUserQuestionCount(String name) throws Exception {
        return questionRepository.getUserQuestionCount(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean increseCounts(QuestionDTO questionDTO) throws Exception {
        questionRepository.increseCounts(questionDTO.getAnswersCount(), questionDTO.getViewsCount(), questionDTO.getVotesCount(), questionDTO.getId());

        long currentUserPoints = userRepository.getUserPoints(questionDTO.getUserName());
        userService.updateUserPoints(questionDTO.getUserName(), currentUserPoints + 1);

        return true;
    }

    @Override
    public long getTotalQuestions() {
        return questionRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateQuestionTitleAndBody(String title, String body, long id) throws Exception {

        questionRepository.updateQuestionTitleAndBody(title, body, id);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateQuestionActiveStatus(int active, long id) throws Exception {

        questionRepository.updateQuestionActiveStatus(active, id);
        return true;
    }
}
