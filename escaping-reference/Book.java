
public class Book implements BookReadOnly {
	private int id;
	private String title;
	private String author;
	private Price price;
	
	/* (non-Javadoc)
	 * @see BookReadOnly#getId()
	 */
	@Override
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see BookReadOnly#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}
	
	/* (non-Javadoc)
	 * @see BookReadOnly#getAuthor()
	 */
	@Override
	public String getAuthor() {
		return author;
	}
	
	public Book(int id, String title, String author, Double price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = new Price(price);
	}
	
	/* (non-Javadoc)
	 * @see BookReadOnly#toString()
	 */
	@Override
	public String toString() {
		return title + " by " + author;
	}
	
	//TODO: check if Price is immutable
	/* (non-Javadoc)
	 * @see BookReadOnly#getPrice()
	 */
	@Override
	public Price getPrice() {
		return this.price;
	}
	
	public void setPrice(Double price) {
		this.price = new Price(price);
	}

}
