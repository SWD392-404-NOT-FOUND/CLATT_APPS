package container.code.function.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.auth0.jwt.algorithms.Algorithm;
import container.code.data.dto.TokenRequest;
import container.code.data.entity.Account;
import container.code.function.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("getAllAccount")
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PostMapping ("/token/google")
    public ResponseEntity tokenGoogle(@RequestBody String accessToken , HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TokenRequest tokenRequest = mapper.readValue(accessToken, TokenRequest.class);
            String tokenValue = tokenRequest.getAccessToken();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseToken decodedToken = auth.verifyIdToken(tokenValue);
            //email here
            String email = decodedToken.getEmail();
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {

        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean checkIfUserExists(String email) {
        // Implement your logic to check if the user exists in your database
        return true;
    }
}
