package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private long id;
    private String userName;
    private long questionID;
    private String answer;
    private long score;
    private int status;
    private String date;
}
