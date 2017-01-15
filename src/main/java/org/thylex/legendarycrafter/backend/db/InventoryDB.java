/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.thylex.legendarycrafter.backend.db.entity.inv.Resource;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;

/**
 *
 * @author Henrik
 */
public class InventoryDB {

    private CrafterApp app = null;
    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public InventoryDB(CrafterApp app) {
        this.app = app;
        emf = Persistence.createEntityManagerFactory("invDB");
        em = emf.createEntityManager();
    }
    
    public List<Resource> getAllResources() {
        Query q = em.createQuery("select i from Resource i");
        List<Resource> retVal = q.getResultList();
        return retVal;
    }
    
    public Resource getResourceByName(String resName) {
        Query q = em.createQuery("select i from Resource i where i.name = :res");
        q.setParameter("res", resName);
        if (q.getResultList().size() == 1) {
            return (Resource)q.getSingleResult();
        } else {
            System.out.println("ERROR: Didn't find just 1 entry, maxResults is: " + q.getMaxResults());
            return null;
        }
    }
    
    public void importFromGH() {
        try {
            Reader in = new FileReader("inventory.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                if (record.size() > 1) {
                    System.out.println(record.size());
                    Resource res = new Resource();
                    res.setName(record.get("name"));
                    res.setResourceType(record.get("resource type"));
                    res.setResourceTypeName(record.get("type name"));
                    res.setER(parse2int(record.get("ER")));
                    res.setCR(parse2int(record.get("CR")));
                    res.setCD(parse2int(record.get("CD")));
                    res.setDR(parse2int(record.get("DR")));
                    res.setFL(parse2int(record.get("FL")));
                    res.setHR(parse2int(record.get("HR")));
                    res.setMA(parse2int(record.get("MA")));
                    res.setPE(parse2int(record.get("PE")));
                    res.setOQ(parse2int(record.get("OQ")));
                    res.setSR(parse2int(record.get("SR")));
                    res.setUT(parse2int(record.get("UT")));
                    res.setAmount(parse2int(record.get("units")) + 0);
                    this.merge(res);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventoryDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InventoryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void merge(Resource res) {
        em.getTransaction().begin();
        em.merge(res);
        em.getTransaction().commit();
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    private Integer parse2int(String txt) {
        String tmp = null;
        if (txt.isEmpty()) {
            tmp = new String("0");
        } else {
            tmp = txt;
        }
        return Integer.decode(tmp);
    }
}
