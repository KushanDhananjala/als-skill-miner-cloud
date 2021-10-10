package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private Long id;
    private Long subjectId;
    private String fullName;
    private String userName;
    private String email;
    private int age;
    private String location;
    private LocalDateTime lastUpdated;
    private int isActive;
    private String password;
    private String profileImageUrl;
}
