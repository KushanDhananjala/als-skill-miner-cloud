package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.PaymentDTO;
import lk.edu.esoft.alsskillminercloud.entity.Payment;
import lk.edu.esoft.alsskillminercloud.entity.Resource;
import lk.edu.esoft.alsskillminercloud.entity.User;
import lk.edu.esoft.alsskillminercloud.repository.PaymentRepository;
import lk.edu.esoft.alsskillminercloud.repository.ResourceRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public boolean savePayment(String userName, Long resourceId) throws Exception {

        List<Payment> allByUserNameAndResourceId = paymentRepository.findAllByUserNameAndResourceId(userName, resourceId);

        if (allByUserNameAndResourceId == null || allByUserNameAndResourceId.isEmpty()) {

            User user = userRepository.findById(userName).get();
            Resource resource = resourceRepository.findById(resourceId).get();

            Payment payment = new Payment(0L, user, resource, LocalDateTime.now(), new BigDecimal(resource.getPrice()));

            paymentRepository.save(payment);
        }

        return true;
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(String userName) throws Exception {

        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        List<Payment> allByUserName = paymentRepository.findAllByUserName(userName);

        if (allByUserName != null) {
            for (Payment payment : allByUserName) {
                PaymentDTO paymentDTO = new PaymentDTO(
                        payment.getId(),
                        payment.getUser().getName(),
                        payment.getResource().getId(),
                        payment.getPayedDateTime(),
                        payment.getAmount()
                );

                paymentDTOS.add(paymentDTO);
            }
        }
        return paymentDTOS;
    }
}
