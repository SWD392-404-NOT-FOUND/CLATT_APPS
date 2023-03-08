package container.code.function.payment.service;

import container.code.data.entity.Account;
import container.code.data.entity.Payment;
import container.code.data.repository.AccountRepository;
import container.code.data.repository.PaymentRepository;
import container.code.function.payment.PaymentCodeGenerator;
import container.code.function.payment.PaymentConstants;
import container.code.function.payment.api.ApiResponse;
import container.code.function.payment.api.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {
    @Autowired
    private PaymentCodeGenerator paymentCodeGenerator;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Value("${api.momo.token}")
    private String token;
    @Value("${api.momo.url}")
    private String urlMomoHistory;
    @Autowired
    private RestTemplate restTemplate;


    public String generateCode(int accountId){
        String paymentCode = paymentCodeGenerator.generatePaymentCode();
        Payment payment = new Payment();
        payment.setPaymentCode(paymentCode);
        payment.setBalance(new BigDecimal(0));
        payment.setAccountId(accountId);
        payment.setStatus(false);
        paymentRepository.save(payment);
        return paymentCode;
    }

    public String confirmPayment(String paymentCode){
        Payment payment = paymentRepository.findPaymentByPaymentCode(paymentCode);
        if (payment == null) return PaymentConstants.WRONG_CODE;
        if (payment.isStatus()) return PaymentConstants.USED_CODE;
        ApiResponse apiResponse = restTemplate.getForObject(urlMomoHistory+"/"+token,ApiResponse.class);
        var transactions = apiResponse.getMomoMsg().getTranList();
        var transaction = transactions.stream().filter(x -> x.getComment().equals(paymentCode)).findAny();
        if (!transaction.isPresent()) return PaymentConstants.PENDING;
        //Adding balance into account
        int newAmount = addBalanceToAccount(payment,transaction.get());
        //Change payment status
        changePaymentStatus(payment , transaction.get());
        return PaymentConstants.SUCCESS + ", your new Balance is: " + newAmount;
    }
    private void changePaymentStatus(Payment payment,Transaction transaction){
        BigDecimal balance = new BigDecimal(transaction.getAmount());
        payment.setBalance(balance);
        payment.setStatus(true);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }
    private int addBalanceToAccount(Payment payment, Transaction transaction){
        Account account = accountRepository.findAccountById(payment.getAccountId());
        int amount = (int) transaction.getAmount();
        account.setAmount(account.getAmount() + amount);
        accountRepository.save(account);
        return account.getAmount();
    }
}
