package lk.edu.esoft.alsskillminercloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
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
    private String githubUrl;
    private String profileImageUrl;
    private long points;
    @ManyToOne
    @JoinColumn(name = "badge_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Badge badge;
}

