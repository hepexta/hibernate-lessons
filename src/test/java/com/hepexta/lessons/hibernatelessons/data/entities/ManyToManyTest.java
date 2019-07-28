package com.hepexta.lessons.hibernatelessons.data.entities;

import com.hepexta.lessons.hibernatelessons.EntityChecker;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class ManyToManyTest extends EntityChecker {

    @Test
    public void test_saveAccount() {
        org.hibernate.Transaction transaction = session.beginTransaction();

        Account account = createNewAccount();
        Account account2 = createNewAccount();
        User user = createUser();
        User user2 = createUser();

        account.getUsers().add(user);
        account.getUsers().add(user2);
        user.getAccounts().add(account);
        user2.getAccounts().add(account);

        /** Establish relationship from Account to User */
        account2.getUsers().add(user);
        account2.getUsers().add(user2);
        /** Establish relationship from UserHibernateAPI to Account. If not done there will be errors! */
        user.getAccounts().add(account2);
        user2.getAccounts().add(account2);

        session.save(user);
        session.save(user2);

        transaction.commit();

        User dbUser = (User) session.
                get(User.class, user.getId());
        System.out.println(dbUser.getAccounts().iterator().next().getName());

    }

    private static Account createNewAccount() {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Kevin Bowersox");
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName("Savings AccountHibernateAPI");
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Kevin Bowersox");
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }

    private static User createUser() {
        User user = new User();
        user.setAddress(Collections.singletonList(createAddress()));
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin Bowersox");
        user.setCreatedDate(new Date());
        user.setCredential(createCredential(user));
        user.setEmailAddress("test@test.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setLastUpdatedBy("system");
        user.setLastUpdateDate(new Date());
        return user;
    }

    private static Credential createCredential(User user) {
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setUsername("test_username");
        credential.setPassword("test_password");
        return credential;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressLine1("101 Address Line");
        address.setAddressLine2("102 Addreess Line");
        address.setCity("New York");
        address.setState("PA");
        address.setZipCode("10000");
        return address;
    }

}
