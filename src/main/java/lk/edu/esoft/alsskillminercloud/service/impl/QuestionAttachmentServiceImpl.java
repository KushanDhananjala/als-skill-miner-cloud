package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.QuestionAttachmentDTO;
import lk.edu.esoft.alsskillminercloud.dto.QuestionDTO;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.QuestionAttachment;
import lk.edu.esoft.alsskillminercloud.repository.QuestionAttachmentRepository;
import lk.edu.esoft.alsskillminercloud.service.QuestionAttachmentService;
import lk.edu.esoft.alsskillminercloud.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class QuestionAttachmentServiceImpl implements QuestionAttachmentService {

    @Autowired
    private QuestionAttachmentRepository questionAttachmentRepository;
    @Autowired
    private QuestionService questionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteQuestionAttchment(long id) throws Exception {

        questionAttachmentRepository.deleteById(id);

        return true;
    }

    @Override
    public ArrayList<QuestionAttachmentDTO> getAllQuestionAttchments() throws Exception {

        ArrayList<QuestionAttachmentDTO> questionAttachmentDTOS = new ArrayList<>();

        List<QuestionAttachment> questionAttachments = questionAttachmentRepository.findAll();

        for (QuestionAttachment questionAttachment : questionAttachments) {

            QuestionDTO questionDTO = questionService.getQuestion(questionAttachment.getQuestion().getId());

            QuestionAttachmentDTO questionAttachmentDTO = new QuestionAttachmentDTO(questionAttachment.getId(), questionDTO
                    , questionAttachment.getAttachmentUrl());

            questionAttachmentDTOS.add(questionAttachmentDTO);
        }

        return questionAttachmentDTOS;

    }

    @Override
    public ArrayList<QuestionAttachmentDTO> getSelectedQuestionAttachments(long id) throws Exception {
        ArrayList<QuestionAttachmentDTO> questionAttachmentDTOS = new ArrayList<>();

        List<Object[]> questionAttachments = questionAttachmentRepository.getSelectedQuestionAttachments(id);

        for (Object[] o : questionAttachments) {

            Question question = (Question) o[1];

            QuestionDTO questionDTO = questionService.getQuestion(question.getId());

            QuestionAttachmentDTO questionAttachmentDTO = new QuestionAttachmentDTO((Long) o[0], questionDTO
                    , o[2].toString());

            questionAttachmentDTOS.add(questionAttachmentDTO);
        }

        return questionAttachmentDTOS;
    }
}
