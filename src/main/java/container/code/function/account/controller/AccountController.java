package container.code.function.account.controller;

import container.code.data.entity.Account;
import container.code.data.repository.service.AccountService;
import container.code.function.account.api.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3030", allowCredentials = "true")
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/get-all")
    public List<UserResponse> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PostMapping("/ban/{id}")
    public ResponseEntity banAccount(@PathVariable Integer id) {
        try {
            accountService.banUser(id);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
