package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import util.DBUtil;
import model.HdzApplicant;
import model.HdzEducation;
import model.HdzJob;
import model.HdzJobhistory;
import model.HdzReftable;

public class ApplicantDao {
	public static void insert(HdzApplicant applicant) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(applicant);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void insert(HdzEducation edu) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(edu);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void insert(HdzJobhistory job) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(job);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void insert(HdzReftable ref) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(ref);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<HdzJob> getAllJobs (){
        EntityManager em = DBUtil.getEmfFactory().createEntityManager();
        String qString = "select j from HdzJob j";
        
        List<HdzJob> jobs = null;
        try{
            TypedQuery<HdzJob> query = em.createQuery(qString,HdzJob.class);
            jobs = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return jobs;
    }
	
	public static List<HdzJob> searchJobs (String search)
    {
        EntityManager em = DBUtil.getEmfFactory().createEntityManager();
        List<HdzJob> searchposts = null;
        String qString = "select j from HdzJob j "
                + "where j.posttext like :search";
        
        try{
            TypedQuery<HdzJob> query = em.createQuery(qString,HdzJob.class);
            query.setParameter("search", "%" + search + "%");
            searchposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }return searchposts;
    }
}