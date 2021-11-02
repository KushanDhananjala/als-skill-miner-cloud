package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {

    private Long id;
    private Long teacherId;
    private String title;
    private String description;
    private String fileType;
    private String accessType;
    private String expireDate;
    private String price;
    private String lastUpdated;
    private String resourceUrl;
    private String fileName;

}
