package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.AnswerAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AnswerAttachmentRepository extends JpaRepository<AnswerAttachment, Long> {

    @Query(value = "SELECT at.id,at.answer,at.attachmentUrl FROM AnswerAttachment at\n " +
            "WHERE lower(at.answer)=lower(:id) ")
    ArrayList<Object[]> getSelectedAnswerAttachments(@Param("id") long id);

}
