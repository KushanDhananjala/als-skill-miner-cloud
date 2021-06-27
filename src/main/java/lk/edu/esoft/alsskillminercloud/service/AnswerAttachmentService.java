package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.AnswerAttachmentDTO;

import java.util.ArrayList;

public interface AnswerAttachmentService {

    boolean deleteAnswerAttchment(long id) throws Exception;

    ArrayList<AnswerAttachmentDTO> getAllAnswerAttchments() throws Exception;

    ArrayList<AnswerAttachmentDTO> getSelectedAnswerAttachments(long id) throws Exception;

}
