package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAttachmentDTO {

    private long id;
    private QuestionDTO questionDTO;
    private String attachmentUrl;
}
