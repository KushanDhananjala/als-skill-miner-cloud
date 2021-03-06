package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomReportedAnswerDTO {

    private Long id;
    private String userName;
    private String userProfileImageUrl;
    private Long answerID;
    private String answer;
    private String date;
    private String reason;
    private int status;
    private Long questionID;
    private String question;
}
