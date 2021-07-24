package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVotesDTO {
    private Long id;
    private String userName;
    private Long questionID;
    private String date;
    private int status;
}
