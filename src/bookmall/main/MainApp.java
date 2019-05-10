package bookmall.main;

import java.util.List;

import bookmall.dao.BookDAO;
import bookmall.dao.CartDAO;
import bookmall.dao.CategoryDAO;
import bookmall.dao.MemberDAO;
import bookmall.dao.OrderDAO;
import bookmall.vo.BookVO;
import bookmall.vo.CartVO;
import bookmall.vo.CategoryVO;
import bookmall.vo.MemberVO;
import bookmall.vo.OrderBookVO;
import bookmall.vo.OrderVO;

public class MainApp {

	public static void main(String[] args) {
		doGetMemberList(); //회원리스트
		doGetCategoryList();//카테고리리스트
		doGetBookList();//도서리스트
		doGetCartList(1L);//카트리스트
		doGetOrderList(1L);//주문리스트
		doGetOrderBookList(1L);//주문도서리스트
	}
	//주문도서리스트
	private static void doGetOrderBookList(long order_no) {
		System.out.println("=================================================================주문도서리스트=====================================================================");
		List<OrderBookVO> list = new OrderDAO().getOrderBookList(order_no);
		for(OrderBookVO vo : list) {
			//책제목,가격,총수량
			System.out.print("책제목 : "+vo.getTitle()+"\t");
			System.out.print("가격 : "+vo.getPrice()+"\t");
			System.out.print("총 수량 : "+vo.getCnt()+"\n");
		}
	}
	//주문 리스트
	private static void doGetOrderList(long member_no) {
		System.out.println("=================================================================주문리스트=====================================================================");
		List<OrderVO> list = new OrderDAO().getOrderList(member_no);
		for(OrderVO vo : list) {
			//주문번호,주문타이틀,주문일자,주문가격,배송지
			System.out.print("주문번호 : "+vo.getOrder_no()+"\t");
			System.out.print("주문 : "+vo.getOrder_title()+"\t");
			System.out.print("주문일자 : "+vo.getDate()+"\t");
			System.out.print("주문가격 : "+vo.getOrder_price()+"\t");
			System.out.print("배송지 : "+vo.getReceive()+"\n");
		}
	}
	//카트 리스트
	private static void doGetCartList(long member_no) {
		System.out.println("=================================================================장바구니=====================================================================");
		List<CartVO> list = new CartDAO().getList(member_no);
		for(CartVO vo : list) {
			//책제목,수량,총가격
			System.out.printf("책제목 : %-15s",vo.getTitle()+"\t");
			System.out.print("수량 : "+vo.getCnt()+"\t");
			System.out.print("총가격 : "+vo.getPrice()+"\n");
		}
	}
	//도서리스트
	private static void doGetBookList() {
		System.out.println("=================================================================도서리스트=====================================================================");
		List<BookVO> list = new BookDAO().getList();
		for(BookVO vo : list) {
			//카테고리,책제목,가격
			System.out.print("카테고리 : "+vo.getCategory()+"\t");
			System.out.print("제목 : "+vo.getTitle()+"\t");
			System.out.print("가격 : "+vo.getPrice()+"\n");
		}
	}
	//카테고리 리스트
	private static void doGetCategoryList() {
		System.out.println("=================================================================카테고리리스트=====================================================================");
		List<CategoryVO> list = new CategoryDAO().getList();
		for(CategoryVO vo : list) {
			System.out.print("번호 : "+vo.getNo()+"\t");
			System.out.print("카테고리 : "+vo.getName()+"\n");
		}
		
	}
	//회원리스트
	private static void doGetMemberList() {
		System.out.println("=================================================================회원리스트=====================================================================");
		List<MemberVO> list = new MemberDAO().getList();
		for(MemberVO vo : list) {
			System.out.print("회원 이름 : "+vo.getName()+"\t");
			System.out.print("회원 전화번호 : "+vo.getPhone()+"\t");
			System.out.print("회원 이메일 : "+vo.getEmail()+"\n");
		}
		
	}
	

}
