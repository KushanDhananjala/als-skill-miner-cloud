package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomAnswerDTO {

    private Long answerID;
    private Long questionID;
    private String answer;
    private long score;
    private int status;
    private String date;
    private String userName;
    private String userDisplayName;
    private String userProfileImageUrl;
    private String userBadgeName;
}
