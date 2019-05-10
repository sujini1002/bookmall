package bookmall.test;

import java.util.List;

import bookmall.dao.MemberDAO;
import bookmall.vo.MemberVO;

public class MemberDAOTest {

	public static void main(String[] args) {
		getList();
	}
	public static void insert(String name,String phone,String email,String password) {
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		
		new MemberDAO().insert(vo);
	}
	public static void getList() {
		List<MemberVO> list = new MemberDAO().getList();
		for(MemberVO vo : list) {
			System.out.println(vo);
		}
	}
	public static void update(long no,String name,String phone,String email,String password) {
		MemberVO vo = new MemberVO();
		vo.setNo(no);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		
		new MemberDAO().update(vo);
	}
	public static void delete(long no) {
		new MemberDAO().delete(no);
	}

}
