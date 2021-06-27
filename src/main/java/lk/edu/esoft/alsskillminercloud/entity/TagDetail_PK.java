package lk.edu.esoft.alsskillminercloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TagDetail_PK implements Serializable {

    private long questionID;
    private long tagID;
}
