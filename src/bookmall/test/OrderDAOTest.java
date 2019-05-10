package bookmall.test;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.CartDAO;
import bookmall.dao.OrderDAO;
import bookmall.vo.CartVO;
import bookmall.vo.OrderBookVO;
import bookmall.vo.OrderVO;

public class OrderDAOTest {

	public static void main(String[] args) {
		long no = insertOrder(5, 40000, "서울시 동대문구 청량리동");
		getOrderList(5);
		getOrderBookList(no);
	}
	public static Long insertOrder(long member_no,long price,String receive) {
		OrderVO vo = new OrderVO();
		vo.setMember_no(member_no);
		vo.setOrder_price(price);
		vo.setReceive(receive);
		
		return new OrderDAO().insertOrder(vo);
	}
	public static OrderBookVO addList(long order_no,long book_no,long cnt) {
		OrderBookVO vo = new OrderBookVO();
		vo.setOrder_no(order_no);
		vo.setBook_no(book_no);
		vo.setCnt(cnt);
		return vo;
	}
	public static void insertOrderBook(List<OrderBookVO> list) {
		
		new OrderDAO().insertOrderBook(list);
	}
	public static void getOrderList(long member_no) {
		List<OrderVO> list = new OrderDAO().getOrderList(member_no);
		for(OrderVO vo : list) {
			System.out.println(vo);
		}
	}
	public static void getOrderBookList(long order_no) {
		List<OrderBookVO> list = new OrderDAO().getOrderBookList(order_no);
		for(OrderBookVO vo : list) {
			System.out.println(vo);
		}
	}

}
