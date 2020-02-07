package work.lince.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.lince.account.model.Account;
import work.lince.account.model.AccountBalance;
import work.lince.account.model.AccountStatus;
import work.lince.account.repository.AccountBalanceRepository;
import work.lince.account.repository.AccountRepository;
import work.lince.commons.authentication.AuthenticationService;
import work.lince.commons.exception.NotFoundException;

import java.util.List;

@Slf4j
@Service
public class AccountBalanceService {

    @Autowired
    protected AccountBalanceRepository repository;

    @Autowired
    protected AuthenticationService authenticationService;

    public Account create(AccountBalance account) {
        return repository.save(account);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }


    public AccountBalance findByAccountId(Long accountId) {
        return repository.findByAccountId(accountId)
                .orElseThrow(() -> new NotFoundException());
    }

}