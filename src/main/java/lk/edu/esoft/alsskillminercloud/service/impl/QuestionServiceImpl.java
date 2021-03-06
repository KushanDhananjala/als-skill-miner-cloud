package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.*;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.QuestionAttachment;
import lk.edu.esoft.alsskillminercloud.entity.TagDetail;
import lk.edu.esoft.alsskillminercloud.repository.QuestionAttachmentRepository;
import lk.edu.esoft.alsskillminercloud.repository.QuestionRepository;
import lk.edu.esoft.alsskillminercloud.repository.SubjectRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.QuestionService;
import lk.edu.esoft.alsskillminercloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionAttachmentRepository questionAttachmentRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SubjectRepository subjectRepository;

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
        question.setCreatedDateTime(LocalDateTime.now());
        question.setSubject(subjectRepository.findById(postQuestionDTO.getQuestionDTO().getSubjectId()).get());
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

        Question savedQuestion = questionRepository.save(question);

        List<QuestionAttachmentDTO> questionAttachmentDTOS = postQuestionDTO.getQuestionAttachmentDTOList();

        if (questionAttachmentDTOS != null) {
            for (QuestionAttachmentDTO questionAttachmentDTO : questionAttachmentDTOS) {
                QuestionAttachment questionAttachment = new QuestionAttachment(question, questionAttachmentDTO.getAttachmentUrl());
                questionAttachment.setQuestion(savedQuestion);
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
    public ArrayList<CustomQuestionDTO> getLandingPageQuestions(Long streamId, Long subjectId) throws Exception {

        ArrayList<Object[]> objectArrayList = questionRepository.getLandingPageQuestions(streamId, subjectId);
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
    public ArrayList<CustomQuestionDTO> getRecentQuestions(Long streamId, Long subjectId) throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getRecentQuestions(streamId, subjectId);
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
    public ArrayList<CustomQuestionDTO> getMostlyViewedQuestions(Long streamId, Long subjectId) throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getMostlyViewedQuestions(streamId, subjectId);
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
    public ArrayList<CustomQuestionDTO> getMostlyVotedQuestions(Long streamId, Long subjectId) throws Exception {
        ArrayList<Object[]> objectArrayList = questionRepository.getMostlyVotedQuestions(streamId, subjectId);
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

    @Override
    public ArrayList<SubjectWiseQuestionCountDTO> getSubjectWiseQuestionCount(String strFromDate, String strToDate) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDate = LocalDateTime.parse(strFromDate, formatter);
        LocalDateTime toDate = LocalDateTime.parse(strToDate, formatter);

        ArrayList<Object[]> objectArrayList = questionRepository.getSubjectWiseQuestionCount(fromDate, toDate);
        ArrayList<SubjectWiseQuestionCountDTO> questionCountDTOS = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            SubjectWiseQuestionCountDTO subjectWiseQuestionCountDTO = new SubjectWiseQuestionCountDTO();
            subjectWiseQuestionCountDTO.setSubjectId(Long.parseLong(o[0].toString()));
            subjectWiseQuestionCountDTO.setSubjectName(o[1].toString());
            subjectWiseQuestionCountDTO.setQuestionCount(Long.parseLong(o[2].toString()));

            questionCountDTOS.add(subjectWiseQuestionCountDTO);
        }
        return questionCountDTOS;
    }
}
