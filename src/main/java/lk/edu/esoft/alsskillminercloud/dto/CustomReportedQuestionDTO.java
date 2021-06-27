package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomReportedQuestionDTO {

    private long id;
    private String userName;
    private String userprofileImageUrl;
    private long questionID;
    private String questionTitle;
    private String questionBody;
    private String date;
    private String reason;
    private int status;
}
