package model.repository;

import model.Entity.Account;
import model.utility.PostgresConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {
    private AccountRepository accountRepository;
    private static Connection connection;

    @BeforeAll
    public static void beforeAll() {
        connection = PostgresConnection.connection;
    }

    @BeforeEach
    public void beforeEach() {
        accountRepository = new AccountRepository();
    }


    @Test
    void save() {
//        arrange
        Account account = new Account(null, "admin", "admin", "a");
//        act
        account.setId(accountRepository.save(account));
        Account load = accountRepository.findById(account.getId());
//        assert
        assertAll(
                () -> assertEquals(account.getId(), load.getId()),
                () -> assertEquals(account.getUserName(), load.getUserName())
        );

    }

    @Test
    void update() {
//        arrange
        Account account = new Account(null, "admin", "admin", "a");
//        act
        account.setId(accountRepository.save(account));
        Account load = account;
        load.setName("omid");
        accountRepository.update(load);
        load = accountRepository.findById(load.getId());
//        assert
        assertEquals("omid",load.getName());
    }

    @Test
    void delete() {
//        arrange
        Account account = new Account(null, "admin", "admin", "a");
//        act
        account.setId(accountRepository.save(account));
        accountRepository.delete(account.getId());
//        assert
        assertNull(accountRepository.findById(account.getId()));
    }

    @Test
    void findById() {
//        arrange
        Account account = new Account(null, "admin", "admin", "a");
//        act
        account.setId(accountRepository.save(account));
//        assert
        assertNotNull(accountRepository.findById(account.getId()));
    }

    @Test
    void findAll() {
//        arrange
        List<Account> accountList = Arrays.asList(
           new Account(null,"a","a","aa"),
           new Account(null,"b","b","dd"),
           new Account(null,"c","c","dd")
        ) ;
//        act
        int size = accountRepository.findAll().size();
        for (Account ac : accountList) {
            accountRepository.save(ac);
        }
        size += accountList.size();
//        assert
        assertEquals(size,accountRepository.findAll().size());
    }

}