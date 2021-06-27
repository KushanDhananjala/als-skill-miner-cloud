package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAnswerDTO {

    private AnswerDTO answerDTO;
    private ArrayList<AnswerAttachmentDTO> answerAttachmentDTOList;
}
