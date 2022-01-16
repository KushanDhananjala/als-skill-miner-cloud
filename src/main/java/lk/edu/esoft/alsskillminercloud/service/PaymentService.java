package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.PaymentDTO;
import lk.edu.esoft.alsskillminercloud.dto.ResourcesPurchaseReportDTO;

import java.util.List;

public interface PaymentService {

    boolean savePayment(String userName, Long resourceId) throws Exception;

    List<PaymentDTO> getPaymentsByUserId(String userName) throws Exception;

    List<ResourcesPurchaseReportDTO> findAllByPayedDateTimeIsBetween(String strFromDate, String strToDate) throws Exception;

}
