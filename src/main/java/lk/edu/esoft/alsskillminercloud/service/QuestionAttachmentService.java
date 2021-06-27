package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.QuestionAttachmentDTO;

import java.util.ArrayList;

public interface QuestionAttachmentService {

    boolean deleteQuestionAttchment(long id) throws Exception;

    ArrayList<QuestionAttachmentDTO> getAllQuestionAttchments() throws Exception;

    ArrayList<QuestionAttachmentDTO> getSelectedQuestionAttachments(long id) throws Exception;

}
