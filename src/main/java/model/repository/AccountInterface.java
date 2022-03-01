package model.repository;

import model.Entity.Account;

public interface AccountInterface<Account> extends RepositoryInterface<Account>{
    Account findByName(String name);
    Account login(String userName , String password);
}
