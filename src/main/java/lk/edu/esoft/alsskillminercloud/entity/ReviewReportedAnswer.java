package lk.edu.esoft.alsskillminercloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewReportedAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reported_id")
    private ReportedAnswer reportedAnswer;
    @ManyToOne
    @JoinColumn(name = "admin_name")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Admin admin;
    private String date;
    private String action;

    public ReviewReportedAnswer(ReportedAnswer reportedAnswer, Admin admin, String date, String action) {
        this.reportedAnswer = reportedAnswer;
        this.admin = admin;
        this.date = date;
        this.action = action;
    }
}
