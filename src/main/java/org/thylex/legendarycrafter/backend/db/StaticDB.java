/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;

/**
 *
 * @author Henrik
 */
public class StaticDB {

    private CrafterApp app = null;
    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    
    public StaticDB(CrafterApp app) {
        this.app = app;
        emf = Persistence.createEntityManagerFactory("swgDB");
        em = emf.createEntityManager();
    }

    public List<String> getProfessions() {
        Query q = em.createNamedQuery("Profession.findAll");
        return q.getResultList();
    }
    
    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }


}
