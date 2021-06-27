package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private long id;
    private String title;
    private String body;
    private String creationDate;
    private long votesCount;
    private long answersCount;
    private long viewsCount;
    private int active;
    private String userName;
    private List<TagDetailDTO> tagDetailDTOList;
}
