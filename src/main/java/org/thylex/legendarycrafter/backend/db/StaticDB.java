/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.thylex.legendarycrafter.backend.db.entity.stat.*;
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
        open();

        checkData();
    }

    public List<ResourceGroup> getResourceGroupByCategory(String category) {
        // q1.size = 0 => end-type rg
        // q1.size > o => category rg
        ArrayList<ResourceGroup> rgList = new ArrayList<>();
        Query q1 = em.createNamedQuery("ResourceGroupCategory.findByResourceCategory");
        q1.setParameter("resourceCategory", category);
        List<ResourceGroupCategory> rgcList = q1.getResultList();
//        System.out.println("Listlength for " + category + " is: " + rgcList.size());
        if (rgcList.size() > 0) {
//            System.out.println(category + " is not a end-type, translating by resursive call");
            for (ResourceGroupCategory rgc : rgcList) {
                rgList.addAll(getResourceGroupByCategory(rgc.getResourceGroup()));
            }
        }
        if (rgcList.isEmpty()) {
//            System.out.println(category + " is an end-type, getting object and returning");
            rgList.add(getRescourceGroup(category));
        }
//        System.out.println("RGC translatation return size: " + rgList.size());
        return rgList;
    }

    public ObjectType getObjectType(Integer objecttype) {
        Query q = em.createNamedQuery("ObjectType.findByObjectType");
        q.setParameter("objectType", objecttype);
        return (ObjectType) q.getSingleResult();
    }

    public ResourceGroup getRescourceGroup(String group) {
        Query q = em.createNamedQuery("ResourceGroup.findByResourceGroup");
        q.setParameter("resourceGroup", group);
        return (ResourceGroup) q.getSingleResult();
    }

    public ResourceType getResourceTypeByName(String name) {
        Query q = em.createNamedQuery("ResourceType.findByResourceTypeName");
        q.setParameter("resourceTypeName", name);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (ResourceType) q.getSingleResult();
        }
    }
    
    public List<ResourceType> getResourceTypeByGroup(String group) {
        Query q = em.createNamedQuery("ResourceTypeGroup.findByResourceGroup");
        q.setParameter("resourceGroup", group);
        List<ResourceTypeGroup> rtgList = q.getResultList();
        
        ArrayList<ResourceType> rtList = new ArrayList<>();
        for (ResourceTypeGroup rtg : rtgList) {
            Query q1 = em.createNamedQuery("ResourceType.findByResourceType");
            q1.setParameter("resourceType", rtg.getResourceType());
            rtList.addAll(q1.getResultList());
        }
        return rtList;
    }

    public List<Profession> getAllProfessions() {
        Query q = em.createNamedQuery("Profession.findAll");
        return q.getResultList();
    }

    public Profession getProfessionByName(String name) {
        Profession p;
        Query q = em.createNamedQuery("Profession.findByProfName");
        q.setParameter("profName", name);
        p = (Profession) q.getSingleResult();
        return p;
    }

    public List<Schematic> getSchematicByGroupName(String skillGroup) {
        Query q = em.createNamedQuery("Schematic.findBySkillGroup");
        q.setParameter("skillGroup", skillGroup);
        List<Schematic> retVal = q.getResultList();
        return retVal;
    }

    public Schematic getSchematicByID(String ID) {
        Query q = em.createNamedQuery("Schematic.findBySchematicID");
        q.setParameter("schematicID", ID);
        return (Schematic) q.getSingleResult();
    }

    public List<SchematicIngredients> getIngredientsBySchematicID(String schematicID) {
        Query q = em.createNamedQuery("SchematicIngredients.findBySchematicID");
        q.setParameter("schematicID", schematicID);
        List<SchematicIngredients> retVal = q.getResultList();
        return retVal;
    }
    
    public List<SchematicQualities> getSchematicQualitiesBySchematicID(String schematicID) {
        Query q = em.createNamedQuery("SchematicQualities.findBySchematicID");
        q.setParameter("schematicID", schematicID);
        return q.getResultList();
    }
    
    public List<SchematicResWeights> getSrwByExpQualityID(Integer expQualityID) {
        Query q = em.createNamedQuery("SchematicResWeights.findByExpQualityID");
        q.setParameter("expQualityID", expQualityID);
        return q.getResultList();
    }

    public void bulkMerge(ArrayList list) {
        em.getTransaction().begin();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            em.merge(iter.next());
        }
        em.getTransaction().commit();
    }

    private void open() {
        emf = Persistence.createEntityManagerFactory("swgDB");
        em = emf.createEntityManager();
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    private void checkData() {
        Boolean dataOK = true;

        //Check Professions
        Query profQ = em.createQuery("SELECT count(*) FROM Profession p");
        long profCount = (long) profQ.getSingleResult();
        //System.out.println("Number of professions in DB: " + profCount);
        if (profCount != 18) {
            dataOK = false;
            ArrayList<Profession> profList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("professions.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    Profession p = new Profession(new Short(rec.get("profID")));
                    p.setCraftingQuality(new Short(rec.get("craftingQuality")));
                    p.setProfName(rec.get("profName"));
                    //merge(p);
                    profList.add(p);
                }
                bulkMerge(profList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Check Skillgroup
        Query skillgroupQ = em.createQuery("SELECT count(*) FROM ResourceType rt");
        long skillgroupCount = (long) skillgroupQ.getSingleResult();
        if (skillgroupCount != 264) {
            dataOK = false;
            ArrayList<SkillGroup> skillList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("skillgroup.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    SkillGroup s = new SkillGroup(rec.get("skillGroup"));
                    s.setProfID(new Short(rec.get("profID")));
                    s.setSkillGroupName(rec.get("skillGroupName"));
                    //merge(s);
                    skillList.add(s);
                }
                bulkMerge(skillList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Check ResourceType
        Query resTypeQ = em.createQuery("SELECT count(*) FROM ResourceType rt");
        long resTypeCount = (long) resTypeQ.getSingleResult();
        if (resTypeCount != 1106) {
            dataOK = false;
            ArrayList<ResourceType> rtList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("resourcetype.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    ResourceType rt = new ResourceType(rec.get("resourceType"));
                    //"resourceType","resourceTypeName","resourceCategory","resourceGroup","enterable","maxTypes","CRmin","CRmax",
                    //"CDmin","CDmax","DRmin","DRmax","FLmin","FLmax","HRmin","HRmax","MAmin","MAmax","PEmin","PEmax","OQmin","OQmax",
                    //"SRmin","SRmax","UTmin","UTmax","ERmin","ERmax","containerType","inventoryType","specificPlanet"
                    rt.setResourceTypeName(rec.get("resourceTypeName"));
                    rt.setResourceCategory(rec.get("resourceCategory"));
                    rt.setResourceGroup(rec.get("resourceGroup"));
                    rt.setEnterable(new Short(rec.get("enterable")));
                    rt.setMaxTypes(new Short(rec.get("maxTypes")));
                    rt.setCRmin(new Short(rec.get("CRmin")));
                    rt.setCRmax(new Short(rec.get("CRmax")));
                    rt.setCDmin(new Short(rec.get("CDmin")));
                    rt.setCDmax(new Short(rec.get("CDmax")));
                    rt.setDRmin(new Short(rec.get("DRmin")));
                    rt.setDRmax(new Short(rec.get("DRmax")));
                    rt.setFLmin(new Short(rec.get("FLmin")));
                    rt.setFLmax(new Short(rec.get("FLmax")));
                    rt.setHRmin(new Short(rec.get("HRmin")));
                    rt.setHRmax(new Short(rec.get("HRmax")));
                    rt.setMAmin(new Short(rec.get("MAmin")));
                    rt.setMAmax(new Short(rec.get("MAmax")));
                    rt.setPEmin(new Short(rec.get("PEmin")));
                    rt.setPEmax(new Short(rec.get("PEmax")));
                    rt.setOQmin(new Short(rec.get("OQmin")));
                    rt.setOQmax(new Short(rec.get("OQmax")));
                    rt.setSRmin(new Short(rec.get("SRmin")));
                    rt.setSRmax(new Short(rec.get("SRmax")));
                    rt.setUTmin(new Short(rec.get("UTmin")));
                    rt.setUTmax(new Short(rec.get("UTmax")));
                    rt.setERmin(new Short(rec.get("ERmin")));
                    rt.setERmax(new Short(rec.get("ERmin")));
                    rt.setContainerType(rec.get("containerType"));
                    rt.setInventoryType(rec.get("inventoryType"));
                    rt.setSpecificPlanet(new Short(rec.get("specificPlanet")));
                    rtList.add(rt);
                    //merge(rt);
                }
                bulkMerge(rtList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Check Schematic
        Query schemQ = em.createQuery("SELECT count(*) FROM Schematic s");
        long schemCount = (long) schemQ.getSingleResult();
        if (schemCount != 1689) {
            dataOK = false;
            ArrayList<Schematic> schemList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("schematic.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    Schematic s = new Schematic(rec.get("schematicID"));
                    //"schematicID","schematicName","craftingTab","skillGroup","objectType","complexity","objectSize","xpType","xpAmount","objectPath","objectGroup"
                    s.setSchematicName(rec.get("schematicName"));
                    s.setCraftingTab(new Integer(rec.get("craftingTab")));
                    s.setSkillGroup(rec.get("skillGroup"));
                    s.setObjectType(new Integer(rec.get("objectType")));
                    s.setComplexity(new Short(rec.get("complexity")));
                    s.setObjectSize(new Short(rec.get("objectSize")));
                    s.setXpType(rec.get("xpType"));
                    s.setXpAmount(new Short(rec.get("xpAmount")));
                    s.setObjectPath(rec.get("objectPath"));
                    s.setObjectGroup(rec.get("objectGroup"));
                    schemList.add(s);
                }
                bulkMerge(schemList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Check SchematicIngredients
        Query schemIngredQ = em.createQuery("SELECT count(*) FROM SchematicIngredients si");
        long schemIngredCount = (long) schemIngredQ.getSingleResult();
        if (schemIngredCount != 7447) {
            dataOK = false;
            ArrayList<SchematicIngredients> ingredList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("schematicingredients.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    SchematicIngredients si = new SchematicIngredients();
                    //"schematicID","ingredientName","ingredientType","ingredientObject","ingredientQuantity","ingredientContribution"
                    si.setSchematicID(rec.get("schematicID"));
                    si.setIngredientName(rec.get("ingredientName"));
                    si.setIngredientType(new Short(rec.get("ingredientType")));
                    si.setIngredientObject(rec.get("ingredientObject"));
                    si.setIngredientQuantity(new Short(rec.get("ingredientQuantity")));
                    si.setIngredientContribution(new Short(rec.get("ingredientContribution")));
                    ingredList.add(si);
                }
                bulkMerge(ingredList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Check ResourceGroup
        Query resTypeGrpQ = em.createQuery("SELECT count(*) FROM ResourceGroup rg");
        long resTypeGrpCount = (long) resTypeGrpQ.getSingleResult();
        if (resTypeGrpCount != 99) {
            dataOK = false;
            ArrayList<ResourceGroup> resGrpList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("resourcegroup.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    //"resourceGroup","groupName","groupLevel","groupOrder","containerType"
                    ResourceGroup rg = new ResourceGroup(rec.get("resourceGroup"));
                    rg.setGroupName(rec.get("groupName"));
                    rg.setGroupLevel(new Short(rec.get("groupLevel")));
                    rg.setGroupOrder(new Short(rec.get("groupOrder")));
                    rg.setContainerType(rec.get("containerType"));
                    resGrpList.add(rg);
                }
                bulkMerge(resGrpList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Check ResourceTypeGroup
        Query resGrpTypeQ = em.createQuery("SELECT count(*) FROM ResourceTypeGroup rtg");
        long resGrpTypeCount = (long) resGrpTypeQ.getSingleResult();
        if (resGrpTypeCount != 5430) {
            dataOK = false;
            ArrayList<ResourceTypeGroup> resGrpTypeList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("resourcetypegroup.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    //"typeGroupID","resourceType","resourceGroup"
                    ResourceTypeGroup rtg = new ResourceTypeGroup();
                    rtg.setResourceType(rec.get("resourceType"));
                    rtg.setResourceGroup(rec.get("resourceGroup"));
                    resGrpTypeList.add(rtg);
                }
                bulkMerge(resGrpTypeList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Check ObjectType
        Query objTypeQ = em.createQuery("SELECT count(*) FROM ObjectType o");
        long objTypeCount = (long) objTypeQ.getSingleResult();
        if (objTypeCount != 98) {
            dataOK = false;
            ArrayList<ObjectType> objTypeList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("objecttype.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    //"objectType","typeName","craftingTab"
                    ObjectType o = new ObjectType(new Integer(rec.get("objectType")));
                    o.setTypeName(rec.get("typeName"));
                    o.setCraftingTab(new Integer(rec.get("craftingTab")));
                    objTypeList.add(o);
                }
                bulkMerge(objTypeList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Check ResourceGroupCategory
        Query resGrpCatQ = em.createQuery("SELECT count(*) FROM ResourceGroupCategory rgc");
        long resGrpCatCount = (long) resGrpCatQ.getSingleResult();
        if (resGrpCatCount != 330) {
            dataOK = false;
            ArrayList<ResourceGroupCategory> resGrpCatList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("resourcegroupcategory.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    //"ID","resourceGroup","resourceCategory"
                    ResourceGroupCategory rgc = new ResourceGroupCategory();
                    rgc.setResourceGroup(rec.get("resourceGroup"));
                    rgc.setResourceCategory(rec.get("resourceCategory"));
                    resGrpCatList.add(rgc);
                }
                bulkMerge(resGrpCatList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Check SchematicResWeights
        Query schemResWeightQ = em.createQuery("SELECT count(*) FROM SchematicResWeights srw");
        long schemResWeightCount = (long) schemResWeightQ.getSingleResult();
        if (schemResWeightCount != 8972) {
            dataOK = false;
            ArrayList<SchematicResWeights> schemResWeightList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("schematicresweights.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    //"expQualityID","statName","statWeight"
                    SchematicResWeights srw = new SchematicResWeights();
                    srw.setExpQualityID(new Integer(rec.get("expQualityID")));
                    srw.setStatName(rec.get("statName"));
                    srw.setStatWeight(new Short(rec.get("statWeight")));
                    schemResWeightList.add(srw);
                }
                bulkMerge(schemResWeightList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Check SchematicQualities
        Query schemQualQ = em.createQuery("SELECT count(*) FROM SchematicQualities sq");
        long schemQualCount = (long) schemQualQ.getSingleResult();
        if (schemQualCount != 13348) {
            dataOK = false;
            ArrayList<SchematicQualities> schemQualList = new ArrayList<>();
            try {
                File file = new File(getClass().getClassLoader().getResource("schematicqualities.csv").getFile());
                FileReader reader = new FileReader(file);
                CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180.withFirstRecordAsHeader());
                for (CSVRecord rec : parser) {
                    //"expQualityID","schematicID","expProperty","expGroup","weightTotal"
                    SchematicQualities sq = new SchematicQualities(new Integer(rec.get("expQualityID")));
                    sq.setSchematicID(rec.get("schematicID"));
                    sq.setExpProperty(rec.get("expProperty"));
                    sq.setExpGroup(rec.get("expGroup"));
                    sq.setWeightTotal(new Short(rec.get("weightTotal")));
                    schemQualList.add(sq);
                }
                bulkMerge(schemQualList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StaticDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dataOK == false) {
            close();
            open();
        }
    }
}
