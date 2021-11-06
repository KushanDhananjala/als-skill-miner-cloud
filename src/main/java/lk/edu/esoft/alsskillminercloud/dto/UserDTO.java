package lk.edu.esoft.alsskillminercloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String displayName;
    private String email;
    private String password;
    private int age;
    private String joinDate;
    private String location;
    private String about;
    private String reputation;
    private String facebookUrl;
    private String mobileNo;
    private String profileImageUrl;
    private long points;
    private Long badgeID;
}
