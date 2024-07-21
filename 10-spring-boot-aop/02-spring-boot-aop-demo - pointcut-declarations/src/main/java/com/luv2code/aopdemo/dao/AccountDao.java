package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

import java.util.List;

public interface AccountDao {
    public void addAccount(Account account,boolean redflag);

    public void test1();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

    public List<Account> findAccounts(boolean tripWire);
    public List<Account> findAccounts();


}
