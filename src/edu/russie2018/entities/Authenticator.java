/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.entities;

/**
 *
 * @author Jawhar
 */
   import java.util.HashMap;
import java.util.Map;

/**
 * Simple Authenticator service, that checks user logins
 * @author Tarun Tyagi
 */
public class Authenticator {
    private static final Map<String, String> USERS = new HashMap<>();
    static {
  //USERS.put("");
    }
    public static boolean validate(String user, String password){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
 