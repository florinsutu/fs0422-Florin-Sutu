package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import catalogue.Articolo;

import managment.Prestito;
import managment.Utente;

public class PrestitoDAO {
	
public static void save(Articolo articolo, Utente utente) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        
		Prestito p = new Prestito(articolo,utente);
		
		t.begin();
		em.persist(p);
		t.commit();
		
		em.close();
		emf.close();
	}
public static void metodoPerUtilizzareLaScadenza(Articolo articolo, Utente utente, LocalDate scadenza) {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
    EntityManager em = emf.createEntityManager();
    EntityTransaction t = em.getTransaction();
    
	Prestito p = new Prestito(articolo,utente);
	p.setScadenza(scadenza);
	
	t.begin();
	em.persist(p);
	t.commit();
	
	em.close();
	emf.close();
}

public static Prestito getById(int id) {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
    EntityManager em = emf.createEntityManager();

	Prestito p = em.find(Prestito.class, id);

	em.close();
	emf.close();

	return p;
}

//public static void getByCard(Utente id) {
//	
//	// SELECT p.name, a.city FROM Person p JOIN p.address a WHERE name = :n
//	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
//    EntityManager em = emf.createEntityManager();
//    
//	CriteriaBuilder cb = em.getCriteriaBuilder();
//
//	CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
//
//	Root<Prestito> p = cq.from(Prestito.class);
//
//	ParameterExpression<String> param1 = cb.parameter(String.class);
//
//
//	Expression<String> personName = p.get("name");
//	
//	Predicate pd1 = cb.equal(personName, param1);
//
//	Join<Person, Address> a = p.join("address", JoinType.LEFT);
//	
//    
//    Query q = em.createQuery(
//    		"SELECT p FROM Prestito p JOIN p.articolo WHERE utente = :id ",Articolo.class
//    		);
//    q.setParameter("id", id);
//    
//    System.out.println(q.getResultList());
//    
//
//	
//}

public static List<Prestito> getAllExpiredDeadlines() {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectM1W3");
    EntityManager em = emf.createEntityManager();
    
    Query q = em.createQuery(
    		"SELECT p FROM Prestito p WHERE p.restituito IS NULL AND p.scadenza <= CURRENT_DATE ",Prestito.class
    		);
    
	return q.getResultList();
	
}

}
