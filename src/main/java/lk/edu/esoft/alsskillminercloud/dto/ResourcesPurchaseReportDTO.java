package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourcesPurchaseReportDTO {

    private String userName;
    private Long resourceId;
    private String resourceName;
    private String fileType;
    private LocalDateTime payedDateTime;
    private BigDecimal amount;
    private String teacherName;

}
