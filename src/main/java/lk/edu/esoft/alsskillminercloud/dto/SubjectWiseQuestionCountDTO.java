package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SubjectWiseQuestionCountDTO {

    private Long subjectId;
    private String subjectName;
    private Long questionCount;

}
