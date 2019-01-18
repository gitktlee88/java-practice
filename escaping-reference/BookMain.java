
public class BookMain {

	public static void main(String[] args) {
		BookCollection bc = new BookCollection();
		
		bc.printAllBooks();
		
		//get price of book called Tom Jones in EUR
		System.out.println("Tom Jones(EUR) : " + bc.findBookByName("Tom Jones").getPrice().convert("EUR"));
		
		bc.printAllBooks();
	}
}
