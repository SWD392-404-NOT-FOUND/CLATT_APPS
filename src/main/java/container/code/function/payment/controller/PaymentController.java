package container.code.function.payment.controller;

import container.code.function.payment.api.PaymentCodeResponse;
import container.code.function.payment.api.PaymentConfirmResponse;
import container.code.function.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/account/{accountId}")
    public ResponseEntity<PaymentCodeResponse> requestNewPaymentCode(@PathVariable("accountId") int accountId) {
        String paymentCode = paymentService.generateCode(accountId);
        PaymentCodeResponse response = PaymentCodeResponse.builder()
                .paymentCode(paymentCode)
                .build();
        return new ResponseEntity<PaymentCodeResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("/account/confirm-payment/{paymentCode}")
    public ResponseEntity<PaymentConfirmResponse> confirmPayment(@PathVariable("paymentCode") String paymentCode){
        String msg = paymentService.confirmPayment(paymentCode);
        PaymentConfirmResponse response = PaymentConfirmResponse.builder()
                .msg(msg)
                .build();
        return new ResponseEntity<PaymentConfirmResponse>(response,HttpStatus.OK);
    }
}
