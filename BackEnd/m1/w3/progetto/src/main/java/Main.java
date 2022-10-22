
import enums.Periodicita;
import java.time.LocalDate;
import dao.ArticoloDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import enums.Genere;

public class Main {

	public static void main(String[] args) {

		// ES-1 =======================

//		ArticoloDAO.save("Tom Sawyer", 2000, 300, "Qualcuno", Genere.NARRATIVA);
//		ArticoloDAO.save("1984", 1949, 300, "Orwell", Genere.NARRATIVA);
//		ArticoloDAO.save("rivista1", 2020, 30, Periodicita.MENSILE);
//		ArticoloDAO.save("rivista2", 2020, 30, Periodicita.SEMESTRALE);
//		ArticoloDAO.save("Al faro", 1987, 300, "Woolf", Genere.NARRATIVA);
//		ArticoloDAO.save("David Copperfield", 1850, 300, "Dickens", Genere.NARRATIVA);
//
//		UtenteDAO.save("mario", "rossi", 1234, "29/05/98");
//
//		PrestitoDAO.save(ArticoloDAO.getByISBN(1), UtenteDAO.getById(1));

		// ES-2 =======================

//		ArticoloDAO.deleteArticle(5);

		// ES-3 =======================

		System.out.println(ArticoloDAO.getByISBN(1));

		// ES-4 =======================

		System.out.println(ArticoloDAO.getByYear(2000));

		// ES-5 =======================

		System.out.println(ArticoloDAO.getByAuthor("Orwell"));

		// ES-6 =======================

		System.out.println(ArticoloDAO.getByTitle("ppe"));

		// ES-7 =======================
		
		PrestitoDAO.getByCard(1234);
		
		// ES-8 =======================

//		LocalDate l = LocalDate.now();
//
//		PrestitoDAO.metodoPerUtilizzareLaScadenza(ArticoloDAO.getByISBN(1), UtenteDAO.getById(1), l);
//
//		System.out.println(PrestitoDAO.getAllExpiredDeadlines());

	}

}
