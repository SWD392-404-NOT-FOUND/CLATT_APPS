package container.code.function.account.controller;

import container.code.data.entity.Account;
import container.code.data.repository.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
