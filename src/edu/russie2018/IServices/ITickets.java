/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.russie2018.IServices;

import edu.russie2018.entities.Tickets;
import java.util.List;

/**
 *
 * @author Sami
 */
public interface ITickets {
    
    public void ajouterTicket(Tickets t);
    public void supprimerTicket(Tickets t);
    public void modifierTicket(Tickets t);
    public List<Tickets> consulterTickets();
    
}
