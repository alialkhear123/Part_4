/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q4;

import part4.*;
import java.io.Serializable;

/**
 *
 * @author Ali Abo Alkhear
 */
public class Account implements Serializable{
    private int AccNo;
    private String AccName;
    private float AccBalance;

    public Account(int AccNo, String AccName, float AccBalance) {
        this.AccName = AccName;
        this.AccNo = AccNo;
        this.AccBalance = AccBalance;
    }
    

    public int getAccNo() {
        return AccNo;
    }
    
    public void setAccNo(int AccNo) {
        this.AccNo = AccNo;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String AccName) {
        this.AccName = AccName;
    }

    public float getAccBalance() {
        return AccBalance;
    }

    public void setAccBalance(float AccBalance) {
        this.AccBalance = AccBalance;
    }

    @Override
    public String toString() {
        return "Account => " + " AccNo = " + AccNo + "\t AccName = " + AccName + "\t AccBalance = " + AccBalance + '.';
    }
    
    
    
}
