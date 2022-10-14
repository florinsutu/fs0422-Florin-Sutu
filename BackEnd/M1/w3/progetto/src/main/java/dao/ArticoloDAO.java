package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import catalogue.Articolo;
import catalogue.Libro;
import catalogue.Rivista;
import enums.Genere;
import enums.Periodicita;


public class ArticoloDAO {
	
	//overload di save per libro e rivista

	public static void save(String titolo, int anno, int pagine, String autore, Genere genere) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
		
		Libro libro = new Libro(titolo, anno, pagine, autore, genere);
		
		t.begin();
		em.persist(libro);
		t.commit();
		
		em.close();
		emf.close();
	}
	
	public static void save(String titolo, int anno, int pagine, Periodicita periodicita) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
		
		Rivista rivista = new Rivista(titolo, anno, pagine, periodicita);
		
		t.begin();
		em.persist(rivista);
		t.commit();
		
		em.close();
		emf.close();
	}

	public static Articolo getByISBN(int ISBN) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();

		Articolo p = em.find(Articolo.class, ISBN);

		em.close();
		emf.close();

		return p;
	}

	public static void deleteArticle(int ISBN) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		Query q = em.createQuery("DELETE Articolo p WHERE p.ISBN = :ISBN");
		
		t.begin();	
		
		q.setParameter("ISBN", ISBN);
		
		q.executeUpdate();
		t.commit();

		em.close();
		emf.close();

	}
	
	public static List<Articolo> getByYear(int anno) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery(
				"SELECT a FROM Articolo a WHERE a.anno = :anno",Articolo.class
				);
		
		q.setParameter("anno", anno);
		
		return q.getResultList();

	}
	
	public static List<Articolo> getByAuthor(String autore) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createQuery(
        		"SELECT a FROM Articolo a WHERE a.autore = :autore",Articolo.class
        		);
        q.setParameter("autore", autore);
        
		return q.getResultList();
		
	}
	
	public static List<Articolo> getByTitle(String titolo) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createQuery(
        		"SELECT a FROM Articolo a WHERE a.titolo LIKE '%"+titolo+"%'",Articolo.class
        		);
       
		return q.getResultList();
		
	}

	public static void refresh(Articolo p) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Homework_W3D3_");
		EntityManager em = emf.createEntityManager();

		em.refresh(p);

		em.close();
		emf.close();

	}
}
