package container.code.function.payment.api;

import lombok.Data;

import java.util.List;

@Data
public class MomoMsg {
    private long begin;
    private long end;
    private List<Transaction> tranList;
}
