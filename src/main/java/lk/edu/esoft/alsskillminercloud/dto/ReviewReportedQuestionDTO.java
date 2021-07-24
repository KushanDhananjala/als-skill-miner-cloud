package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewReportedQuestionDTO {

    private Long id;
    private Long reportedID;
    private String adminName;
    private String date;
    private String action;
}
