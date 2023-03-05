package container.code.function.payment.api;

import lombok.Data;

@Data
public class Transaction {
    private String user;
    private long tranId;
    private long clientTime;
    private int tranType;
    private String partnerId;
    private String partnerName;
    private String comment;
    private long amount;
    private int status;
}
