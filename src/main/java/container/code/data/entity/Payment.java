package container.code.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id")
    private int paymentId;
    @Column(name = "payment_code")
    private String paymentCode;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "status")
    private boolean status;
    @Column(name = "account_id")
    private int accountId;
    @ManyToOne
    @JoinColumn(name = "account_id", updatable = false, insertable = false)
    private Account account;

}
