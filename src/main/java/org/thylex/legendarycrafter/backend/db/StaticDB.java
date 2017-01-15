/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.thylex.legendarycrafter.backend.db.entity.inv.Resource;
import org.thylex.legendarycrafter.backend.db.entity.stat.ResourceType;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;

/**
 *
 * @author Henrik
 */
public class StaticDB {

    private Connection conn = null;
    
    private CrafterApp app = null;
    private SessionFactory sf = null;
    private Session db = null;

    public StaticDB(CrafterApp app, String bla) {
        this.app = app;
        Configuration conf = new Configuration().configure("staticdb.cfg.xml");
        conf.addAnnotatedClass(ResourceType.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
        sf = conf.buildSessionFactory(builder.build());
        db = sf.openSession();
    
    }
    
    public StaticDB(CrafterApp app) {
        String dburl = "jdbc:mysql://192.168.0.170/swgresource";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(dburl, "swgcraft", "devops");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //app.getSettings().setProp("StaticURL", dburl);
    }

    public ArrayList<String> getProfessions() {
        ArrayList<String> retVal = null;
        String query = "SELECT profName FROM tProfession";
        try {
            ArrayList<String> a = new ArrayList<String>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                a.add(rs.getString("profName"));
            }
            retVal = a;
        } catch (SQLException ex) {
            Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retVal;
    }
    
    public void close() {
        if (db != null) {
            db.close();
        }
        if (sf != null) {
            sf.close();
        }
    }


}
