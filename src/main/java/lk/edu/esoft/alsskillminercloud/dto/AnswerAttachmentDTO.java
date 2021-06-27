package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAttachmentDTO {

    private long id;
    private AnswerDTO answerDTO;
    private String attachmentUrl;
}
