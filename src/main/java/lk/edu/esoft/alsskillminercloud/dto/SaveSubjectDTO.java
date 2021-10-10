package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveSubjectDTO {

    private SubjectDTO subjectDTO;
    private List<StreamDTO> streamDTOS;

}
