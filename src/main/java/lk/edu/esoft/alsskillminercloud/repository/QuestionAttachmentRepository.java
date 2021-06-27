package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.QuestionAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface QuestionAttachmentRepository extends JpaRepository<QuestionAttachment, Long> {

    @Query(value = "SELECT qa.id,qa.question,qa.attachmentUrl FROM QuestionAttachment qa\n " +
            "WHERE lower(qa.question)=lower(:id) ")
    ArrayList<Object[]> getSelectedQuestionAttachments(@Param("id") long id);

}
