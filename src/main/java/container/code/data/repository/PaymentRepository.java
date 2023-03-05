package container.code.data.repository;

import container.code.data.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findPaymentByPaymentCode(String paymentCode);
}
