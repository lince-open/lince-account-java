package work.lince.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.lince.commons.authentication.AuthenticationService;
import work.lince.commons.exception.NotFoundException;
import work.lince.account.model.Account;
import work.lince.account.model.AccountStatus;
import work.lince.account.repository.AccountRepository;

import java.util.List;

@Slf4j
@Service
public class AccountService {

    @Autowired
    protected AccountRepository repository;

    @Autowired
    protected AuthenticationService authenticationService;

    public Account create(Account account) {
        account.setOwner(authenticationService.getAuthenticatedUser());
        account.setStatus(AccountStatus.CREATED);
        return repository.save(account);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }


    public Account findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public void remove(Long id) {
        Account account  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        repository.delete(account);
    }

}