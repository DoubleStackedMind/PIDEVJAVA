/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.russie2018.services;

import edu.russie2018.entities.*;
import edu.russie2018.utils.BCrypt;
import edu.russie2018.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Jawhar
 */
public class ServiceUser {
    Connection cnx=DatabaseConnection.getInstance().getConnection();
    Statement ste;
    ResultSet rs;
    public static User currentUser=new User();
    public ServiceUser()
    {   
        try {
            ste=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void addUser(User u) throws SQLException
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String req="INSERT INTO `user`(`username`, `email`, "
                + " `enabled`, `password`, `last_login`, "
                + "`roles`, `prenom`, "
                + "`nom`, `Likes`, `dislikes`, `soldes`,`username_canonical`,`email_canonical`,`confirmation_token`) "
                + "VALUES (?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?)";
                
        PreparedStatement pre=cnx.prepareStatement(req);
        pre.setString(1,u.getUser_name());
        pre.setString(2,u.getEmail());
        pre.setInt(3,1);
        pre.setString(4,u.getPassword());
        pre.setString(5,timeStamp);
        if (u.getRoles().toString()=="Utilisateur")
            u.setRoles("a:0:{}");
        else
            u.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
        pre.setString(6,u.getRoles());
        System.out.println("role :");
        System.out.println(u.getRoles());
        pre.setString(7,u.getFirst_name());
        pre.setString(8,u.getLast_name());
        pre.setInt(9,0);
        pre.setInt(10,0);
        pre.setInt(11,50);
        pre.setString(12,u.getUser_name());
        pre.setString(13,u.getEmail());
        pre.setString(14,null);
        try {
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Boolean Autentifier(String u,String p) throws SQLException
    {
        String req = "SELECT * FROM `user` WHERE username =\'"+u+"\'";
        System.out.println(req);
        
        
        try {
             rs= ste.executeQuery(req);
            System.out.println(rs);
            if (rs!=null)
            {
                        while (rs.next())
                        {
                            System.out.println(rs.getInt(1));
                            System.out.println(rs.getString(2));
                            System.out.println(rs.getString(3));
                            System.out.println(rs.getString(4));
                            System.out.println(rs.getString(5));
                            System.out.println(rs.getString(6));
                            System.out.println(rs.getString(7));
                            System.out.println(rs.getString(8));
                            System.out.println(rs.getString(9));
                            System.out.println(rs.getString(10));
                            System.out.println(rs.getString(11));
                            System.out.println(rs.getString(12));
                            System.out.println(rs.getString(13));
                            System.out.println(rs.getString(14));
                            System.out.println(rs.getString(15));
                            System.out.println(rs.getString(16));
                            System.out.println(rs.getString(17));
                            
                            currentUser.setId(rs.getInt("id"));
                            currentUser.setUser_name(rs.getString("username"));
                            currentUser.setEmail(rs.getString("email"));
                            currentUser.setEnabled(rs.getInt("enabled"));
                            currentUser.setPassword(rs.getString("password"));
                            currentUser.setLast_login(rs.getString("last_login"));
                            currentUser.setConfirmation_token(rs.getString("confirmation_token"));
                            currentUser.setPassword_request_at(rs.getString("password_requested_at"));
                            currentUser.setRoles(rs.getString("roles"));
                            currentUser.setFirst_name(rs.getString("nom"));
                            currentUser.setLast_name(rs.getString("prenom"));
                           
                            currentUser.setLikes(rs.getInt("Likes"));
                            currentUser.setdislikes(rs.getInt("dislikes"));
                            currentUser.setSolde(rs.getInt("soldes"));
                          
                            System.out.println(currentUser.toString());
                            return true;
                            }
                          
                        }
                
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean isLogedIn(User user)
    {
        if ((user.getEmail()=="" && user.getFirst_name()=="" && user.getLast_name()=="")||(user.getEmail()==null && user.getFirst_name()==null && user.getLast_name()==null))
             return false;
        else 
            return true;
    }
    public boolean isValidUser(String Email)
    {
        String req = "SELECT * FROM `user` WHERE email =\'"+Email+"\'";
        try {
             rs= ste.executeQuery(req);
            System.out.println(rs);
            if (rs!=null)
            {
                        while (rs.next())
                        {
                            System.out.println(rs.toString());
                            return true;
                        }
            }
            
                
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void addToken(int token,String email) throws SQLException
    {
            //
        String req = "UPDATE `user` SET `confirmation_token`="+token+",`password_requested_at`=SYSDATE() WHERE email=\""+email+"\"";
        
        try {
             int i= ste.executeUpdate(req);
      
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public boolean isValidToken(String get, String text) {
        String token = null;
        String date = null;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String req = "SELECT * FROM `user` WHERE email =\'"+text+"\'";
                try {
                         rs= ste.executeQuery(req);
                        System.out.println(rs);
                        if (rs!=null)
                        {
                                    while (rs.next())
                                    {
                                        token=rs.getString(10);
                                        date=rs.getString(11);
                                        System.out.println(rs.toString());
                                        
                                    }
                        }


                    } catch (SQLException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if (date==null || token == null)
                {System.out.println("null vale"+date+" token"+token);
                    return false;
                }
                else
                {
                    String jj,mm,yy,dd,hh,m;
                    //vale2018-03-21 19:32:38.05877
                    yy=date.substring(2, 4);
                    mm=date.substring(5, 7);
                    dd=date.substring(8, 10);
                    hh=date.substring(11, 13);
                    m=date.substring(14, 16);
                    System.out.println("year"+yy+"month"+mm+"day"+dd+"hour"+hh+"minut"+m);
                    
                    String jj1,mm1,yy1,dd1,hh1,m1,hh2;
                    //vale2018-03-21 19:32:38.05877
                    yy1=timeStamp.substring(2, 4);
                    mm1=timeStamp.substring(5, 7);
                    dd1=timeStamp.substring(8, 10);
                    hh1=timeStamp.substring(11, 13);
                    m1=timeStamp.substring(14, 16);
                    hh2=hh+1;
                    System.out.println("year"+yy1+"month"+mm1+"day"+dd1+"hour"+hh1+"minut"+m1);
                    
                    System.out.println("vale "+date+token);
                    if (get.equals(token) && yy.equals(yy1) && mm.equals(mm1)&& dd.equals(dd1) && (hh.equals(hh1)||hh2.equals(hh1)))
                    return true;
                }
    return false;
    }



    
            
            
}
