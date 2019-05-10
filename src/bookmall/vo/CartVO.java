package bookmall.vo;

public class CartVO {
	private Long book_no;
	private Long member_no;
	private Long cnt;
	private String title;
	private Long price;
	
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public String getTitle() {
		return title;
	}
	public Long getPrice() {
		return price;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	public void setPrice(Long price) {
		this.price = price;
	}public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "CartVO [cnt=" + cnt + ", title=" + title +  ", price=" + price + "]";
	}
	
}
