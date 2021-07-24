package lk.edu.esoft.alsskillminercloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDetail {

    @EmbeddedId
    private TagDetail_PK tagDetail_pk;

    public TagDetail(Long questionID, Long tagID) {
        this.tagDetail_pk = new TagDetail_PK(questionID, tagID);
    }

    public TagDetail_PK getTagDetail_pk() {
        return tagDetail_pk;
    }

    public void setTagDetail_pk(TagDetail_PK tagDetail_pk) {
        this.tagDetail_pk = tagDetail_pk;
    }

    @Override
    public String toString() {
        return "TagDetail{" +
                "tagDetail_pk=" + tagDetail_pk +
                '}';
    }
}
