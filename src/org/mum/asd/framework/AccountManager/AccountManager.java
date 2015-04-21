/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.asd.framework.AccountManager;

import java.util.ArrayList;
import java.util.List;
import org.mum.asd.framework.enums.Types;
import org.mum.asd.framework.mediator.IColleague;
import org.mum.asd.framework.mediator.ISenderColleague;
import org.mum.asd.framework.mediator.Mediator;
import org.mum.asd.framework.mediator.Message;
import org.mum.asd.framework.transaction.ITransaction;
import org.mum.asd.framework.partyPattern.AParty;
import org.mum.asd.framework.partyPattern.IParty;
import org.mum.asd.framework.transaction.Deposite;

public class AccountManager implements ISenderColleague {

    private static final String NAME = "ACCOUNT_MANAGER";
    private Mediator mediator;

    public List<IAccount> listOfAccount;

    public AccountManager(Mediator md) {
        listOfAccount = new ArrayList<IAccount>();
        mediator = md;
    }

    public List<IAccount> getAccountList() {
        return listOfAccount;
    }

    public void addAccountToList(IAccount account) {
        System.out.println(account.getParty());
        AParty a = (AParty) account.getParty();
        System.out.println("a ++" + a);
        IParty c = account.getParty();
        System.out.println("c+++" + c);
        // Company c=account.getParty();
        this.listOfAccount.add(account);
        this.send(new Message(Message.UPDATE_ACCOUNT_TABLE, true));
//        updateAccountTable();
    }

    public AAccount getAccountById(String id) {
        for (IAccount a : listOfAccount) {
            if (a.getAcctNumber().equalsIgnoreCase(id)) {
                return (AAccount) a;
            }
        }
        return null;
    }

    public void withDraw(IAccount account, ITransaction transaction) {
        double balance = account.getBalance() - transaction.getAmount();
        account.setBalance(balance);
        this.send(new Message(Message.UPDATE_ACCOUNT_TABLE, true));
    }

    public void deposite(IAccount account, ITransaction transaction) {
        double balance = account.getBalance() + transaction.getAmount();
        account.setBalance(balance);
        this.send(new Message(Message.UPDATE_ACCOUNT_TABLE, true));
    }

    public void addInterest() {
        for (IAccount account : listOfAccount) {
            double interestRate = account.getInterest();
            ITransaction deposit = new Deposite();//FactoryProducer.getFactory(Types.TRANSACTION).getTransaction(TransactionType.DEPOSIT);
            //deposit.setupTransaction(this, account);
            //deposit.setName(Deposite.DEPOSIT_INTEREST);
            //deposit.setName("Deposit_Interest");
            // deposit.setAmount(interestAmount);
            // transactionManager.execute(deposit);
            double newBalance = account.getBalance() + account.getBalance() * interestRate * 0.01;
            account.setBalance(newBalance);
            this.send(new Message(Message.UPDATE_ACCOUNT_TABLE, true));
        }
        //  this.updateAccountTable();
    }

    @Override
    public void send(Message message) {
        mediator.send(message);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public void updateAccountTable() {
        this.send(new Message(Message.UPDATE_ACCOUNT_TABLE, true));
    }

}
