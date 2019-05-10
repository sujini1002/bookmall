package bookmall.vo;

public class OrderBookVO {
	private Long order_no;
	private Long book_no;
	private Long cnt;
	private String title;
	private Long price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "OrderBookVO [order_no=" + order_no + ", book_no=" + book_no + ", cnt=" + cnt + ", title=" + title
				+ ", price=" + price + "]";
	}
	
}
