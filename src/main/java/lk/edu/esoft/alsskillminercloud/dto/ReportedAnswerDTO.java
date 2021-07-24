package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportedAnswerDTO {

    private Long id;
    private String userName;
    private Long answerID;
    private String date;
    private String reason;
    private int status;
}
