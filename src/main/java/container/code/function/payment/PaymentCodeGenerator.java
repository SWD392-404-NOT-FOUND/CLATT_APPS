package container.code.function.payment;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentCodeGenerator {
    public String generatePaymentCode(){
        Random random = new Random();
        StringBuffer res = new StringBuffer("");
        for (int i = 1; i<=13; i++){
            int j = random.nextInt(3);
            int k;
            switch (j){
                case 0: k = 48 + random.nextInt(9); break;
                case 1: k = 65 + random.nextInt(26);break;
                default: k = 97 + random.nextInt(26);
            }

            res.append((char) k);
        }
        return res.toString();
    }
}
