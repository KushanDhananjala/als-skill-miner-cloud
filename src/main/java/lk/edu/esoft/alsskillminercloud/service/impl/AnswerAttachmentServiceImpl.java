package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.AnswerAttachmentDTO;
import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.entity.Answer;
import lk.edu.esoft.alsskillminercloud.entity.AnswerAttachment;
import lk.edu.esoft.alsskillminercloud.repository.AnswerAttachmentRepository;
import lk.edu.esoft.alsskillminercloud.service.AnswerAttachmentService;
import lk.edu.esoft.alsskillminercloud.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AnswerAttachmentServiceImpl implements AnswerAttachmentService {

    @Autowired
    private AnswerAttachmentRepository answerAttachmentRepository;
    @Autowired
    private AnswerService answerService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteAnswerAttchment(long id) throws Exception {
        answerAttachmentRepository.deleteById(id);

        return true;
    }

    @Override
    public ArrayList<AnswerAttachmentDTO> getAllAnswerAttchments() throws Exception {
        ArrayList<AnswerAttachmentDTO> answerAttachmentDTOS = new ArrayList<>();

        List<AnswerAttachment> answerAttachments = answerAttachmentRepository.findAll();

        for (AnswerAttachment answerAttachment : answerAttachments) {

            AnswerDTO answerDTO = answerService.getAnswer(answerAttachment.getAnswer().getId());

            AnswerAttachmentDTO answerAttachmentDTO = new AnswerAttachmentDTO(answerAttachment.getId(), answerDTO
                    , answerAttachment.getAttachmentUrl());

            answerAttachmentDTOS.add(answerAttachmentDTO);
        }

        return answerAttachmentDTOS;
    }

    @Override
    public ArrayList<AnswerAttachmentDTO> getSelectedAnswerAttachments(long id) throws Exception {
        ArrayList<AnswerAttachmentDTO> answerAttachmentDTOS = new ArrayList<>();

        List<Object[]> answerAttachments = answerAttachmentRepository.getSelectedAnswerAttachments(id);

        for (Object[] o : answerAttachments) {

            Answer answer = (Answer) o[1];

            AnswerDTO answerDTO = answerService.getAnswer(answer.getId());

            AnswerAttachmentDTO answerAttachmentDTO = new AnswerAttachmentDTO((Long) o[0], answerDTO
                    , o[2].toString());

            answerAttachmentDTOS.add(answerAttachmentDTO);
        }

        return answerAttachmentDTOS;
    }
}
