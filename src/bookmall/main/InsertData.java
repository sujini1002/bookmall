package bookmall.main;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.BookDAO;
import bookmall.dao.CartDAO;
import bookmall.dao.CategoryDAO;
import bookmall.dao.MemberDAO;
import bookmall.dao.OrderDAO;
import bookmall.vo.BookVO;
import bookmall.vo.CartVO;
import bookmall.vo.MemberVO;
import bookmall.vo.OrderBookVO;
import bookmall.vo.OrderVO;

public class InsertData {

	public static void main(String[] args) {
		insertMember("강수진", "010-5489-1598", "tgif@naver.com", "1111");
		insertMember("홍길동", "010-4862-9415", "gildong@gmail.com", "gi123");

		insertCategory("소설");
		insertCategory("컴퓨터");
		insertCategory("과학");
		insertCategory("인문");

		insertBook("컴퓨터", "자바의 정석", 32000);
		insertBook("소설", "백야행", 24000);
		insertBook("과학", "물리의 정석", 10000);
		insertBook("인문", "지적 대화를 위한 넓고 얉은 지식", 17000);
		insertBook("컴퓨터", "헤드퍼스트 파이썬", 20000);

		insertCart(1, 1, 1);
		insertCart(3, 1, 2);
		insertCart(4, 1, 1);
		insertCart(3, 2, 2);
		insertCart(5, 2, 3);

		long no =insertOrder(1, 50000, "서울시 종로구 필운동");
		
		List<OrderBookVO> list = new ArrayList<OrderBookVO>();
		list.add(addList(no, 1, 2));
		list.add(addList(no, 3, 1));
		list.add(addList(no, 4, 1));
		
		insertOrderBook(list);
		
		System.out.println("insert 완료!");
	}

	public static void insertMember(String name, String phone, String email, String password) {
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);

		new MemberDAO().insert(vo);
	}

	public static void insertCategory(String name) {

		new CategoryDAO().insert(name);
	}

	public static void insertBook(String category, String title, long price) {
		BookVO vo = new BookVO();
		vo.setCategory(category);
		vo.setTitle(title);
		vo.setPrice(price);

		new BookDAO().insert(vo);
	}

	public static void insertCart(long book_no, long member_no, long cnt) {
		CartVO vo = new CartVO();
		vo.setBook_no(book_no);
		vo.setMember_no(member_no);
		vo.setCnt(cnt);

		new CartDAO().insert(vo);
	}

	public static Long insertOrder(long member_no, long price, String receive) {
		OrderVO vo = new OrderVO();
		vo.setMember_no(member_no);
		vo.setOrder_price(price);
		vo.setReceive(receive);

		return new OrderDAO().insertOrder(vo);
	}

	public static void insertOrderBook(List<OrderBookVO> list) {

		new OrderDAO().insertOrderBook(list);
	}

	public static OrderBookVO addList(long order_no, long book_no, long cnt) {
		OrderBookVO vo = new OrderBookVO();
		vo.setOrder_no(order_no);
		vo.setBook_no(book_no);
		vo.setCnt(cnt);
		return vo;
	}

}
