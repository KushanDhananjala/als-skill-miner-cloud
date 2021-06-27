package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerVoteDTO {

    private long id;
    private String userName;
    private long questionID;
    private long answerID;
    private String date;
    private int status;
}
