package bookmall.vo;

public class OrderVO {
	private Long no;
	private Long member_no;
	private String order_no;
	private String order_title;
	private Long order_price;
	private String receive;
	private String date;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public String getOrder_title() {
		return order_title;
	}
	public void setOrder_title(String order_title) {
		this.order_title = order_title;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Long getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Long order_price) {
		this.order_price = order_price;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getDate() {
		return date;
	}
	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", member_no=" + member_no + ", order_no=" + order_no + ", order_title="
				+ order_title + ", order_price=" + order_price + ", receive=" + receive + ", date=" + date + "]";
	}
	
}
