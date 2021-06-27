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
public class AnswerAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "answer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Answer answer;
    private String attachmentUrl;

    public AnswerAttachment(Answer answer, String attachmentUrl) {
        this.answer = answer;
        this.attachmentUrl = attachmentUrl;
    }
}
