package container.code.data.dto;

import com.google.firebase.auth.FirebaseToken;
import container.code.data.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;

    private AccountDTO accountDTO;
}
