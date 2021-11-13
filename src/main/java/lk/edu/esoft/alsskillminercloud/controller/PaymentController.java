package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.PaymentDTO;
import lk.edu.esoft.alsskillminercloud.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(value = "/save/{userName}/{resourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean savePayment(@PathVariable("userName") String userName,
                               @PathVariable("resourceId") Long resourceId) {
        try {
            return paymentService.savePayment(userName, resourceId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/by-user-name/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentDTO> getPaymentsByUserId(@PathVariable("userName") String userName) {
        try {
            return paymentService.getPaymentsByUserId(userName);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
