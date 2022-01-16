package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.dto.ResourcesPurchaseReportDTO;
import lk.edu.esoft.alsskillminercloud.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByUserName(String userName);

    List<Payment> findAllByUserNameAndResourceId(String userName, Long resourceId);

    @Query("SELECT new lk.edu.esoft.alsskillminercloud.dto.ResourcesPurchaseReportDTO(" +
            "   p.user.name, " +
            "   p.resource.id, " +
            "   r.fileName, " +
            "   r.fileType, " +
            "   p.payedDateTime, " +
            "   p.amount, " +
            "   t.fullName) " +
            "FROM Payment p " +
            "   LEFT JOIN Resource r ON p.resource.id = r.id\n" +
            "   LEFT JOIN Teacher t ON r.teacher.id = t.id\n" +
            "WHERE p.payedDateTime BETWEEN :fromDate AND :toDate")
    List<ResourcesPurchaseReportDTO> findAllByPayedDateTimeIsBetween(@Param("fromDate") LocalDateTime fromDate,
                                                                     @Param("toDate") LocalDateTime toDate);

}
