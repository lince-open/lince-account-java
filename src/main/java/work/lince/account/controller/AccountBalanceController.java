package work.lince.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.lince.account.model.Account;
import work.lince.account.service.AccountService;
import work.lince.commons.log.LogExecutionTime;

import java.util.List;

@LogExecutionTime
@RestController
@RequestMapping(path = "/accounts/{account_id}/balance")
public class AccountBalanceController {

    @Autowired
    protected AccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody @Validated Account body) {
        return service.create(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable("id") final Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("id") final Long id) {
        service.remove(id);
    }

}