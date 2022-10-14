package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import catalogue.Articolo;
import managment.Utente;

public class UtenteDAO {

	public static void save(String nome, String cognome, int tessera, String data_di_nascita) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		Utente utente = new Utente(nome, cognome, tessera, data_di_nascita);

		t.begin();
		em.persist(utente);
		t.commit();

		em.close();
		emf.close();
	}
	
	public static Utente getById(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();

		Utente p = em.find(Utente.class, id);

		em.close();
		emf.close();

		return p;
	}
}
