/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q5;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ali Abo Alkhear
 */
public class Nn {

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        FileOutputStream fos = new FileOutputStream("./src/Q5/login.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        PrintWriter pw = new PrintWriter(fos);
        String name = "ali";
        String Password = "ali";

//        String [][] loginInfo = {
//            {"Ali" , getMd5("Ali")},
//            {"Ahmed", getMd5("Ahmed")},
//            {"Soso", getMd5("Soso")}  
//        };
        pw.write(name + " ");
        pw.write(getMd5(Password) + "\n");
        pw.write("soso" + " ");
        pw.write(getMd5("soso")+"\n");
        pw.write("abd" + " ");
        pw.write(getMd5("abd"));
        pw.flush();
        pw.close();
    }
}
