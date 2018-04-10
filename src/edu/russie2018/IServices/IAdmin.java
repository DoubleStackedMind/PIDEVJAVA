/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Admin;
import java.util.List;

/**
 *
 * @author Jawhar
 */
public interface IAdmin {
 
    public void ajouterAdmin(Admin A);
    public void supprimerAdmin(Admin A);
    public void modifierAdmin(Admin A);
    public List<Admin> consulterAdmin();
    
}
