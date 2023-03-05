package container.code.function.payment.api;

import lombok.Data;

@Data
public class ApiResponse {
    private MomoMsg momoMsg;
    private long time;
    private String user;
    private String lang;
    private String msgType;
    private boolean result;
    private String appCode;
}
