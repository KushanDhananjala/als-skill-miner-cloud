package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomQuestionDTO {

    private String userName;
    private String userDisplyName;
    private String userprofileImageUrl;
    private String badgeName;
    private Long questionID;
    private String questionTitle;
    private String questionBody;
    private String questionCreationDate;
    private Long questionAnswersCount;
    private Long questionViewsCount;
    private Long questionVotesCount;
    private int questionActive;
}
