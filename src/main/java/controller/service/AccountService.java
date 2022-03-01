package controller.service;

import model.Entity.Account;
import model.repository.AccountRepository;

public class AccountService extends BaseService<Account,AccountRepository> {
    private AccountRepository accountRepository;

    public AccountService() {
        super(new AccountRepository());
        this.accountRepository = new AccountRepository();
    }

    public Account findByName(String name){
        return accountRepository.findByName(name);
    }

    public Account login(String userName , String password){
        return accountRepository.login(userName,password);
    }

}
