package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportedAnswerDTO {

    private long id;
    private String userName;
    private long answerID;
    private String date;
    private String reason;
    private int status;
}
