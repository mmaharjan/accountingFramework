/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.asd.framework.AccountManager;

import org.mum.asd.framework.partyPattern.IParty;
import org.mum.asd.framework.transaction.ITransaction;

/**
 *
 * @author dell
 */
public interface IAccount {
    public void addEntry(ITransaction transaction);

    public double getCurrentBalance();
    
     public String getAcctNumber();
     
     public void setAcctNumber(String accNum);

    public IParty getParty();
    
    public String getType();
}
