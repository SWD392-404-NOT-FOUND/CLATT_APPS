package container.code.function.account.service.impl;

import container.code.data.entity.Account;
import container.code.data.repository.AccountRepository;
import container.code.function.account.service.AccountService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    @Cacheable(cacheNames = "cache1", key = "'#key'")
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

}
