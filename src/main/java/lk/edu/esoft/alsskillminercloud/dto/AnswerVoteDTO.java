package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerVoteDTO {

    private Long id;
    private String userName;
    private Long questionID;
    private Long answerID;
    private String date;
    private int status;
}
