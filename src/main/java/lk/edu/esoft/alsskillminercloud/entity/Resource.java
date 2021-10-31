package lk.edu.esoft.alsskillminercloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "teacher_name")
    private Teacher teacher;
    private String title;
    private String description;
    private String fileType;
    private String accessType;
    private String expireDate;
    private String price;
    private Date lastUpdated;
    private String resourceUrl;

}
