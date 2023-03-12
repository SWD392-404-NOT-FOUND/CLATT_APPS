package container.code.function.authentication.service;

import container.code.data.dto.CreateAccountForm;
import container.code.data.dto.ResponseObject;
import container.code.data.dto.LoginForm;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseObject> login(LoginForm form);

    ResponseEntity<ResponseObject> loginWithIdToken(String token, String role);

    ResponseEntity<ResponseObject> createAccount(CreateAccountForm form);

    ResponseEntity<ResponseObject> sendIdTokenVerify(String token);
}
