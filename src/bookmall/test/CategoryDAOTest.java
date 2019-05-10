package bookmall.test;

import java.util.List;

import bookmall.dao.CategoryDAO;
import bookmall.vo.CategoryVO;


public class CategoryDAOTest {

	public static void main(String[] args) {
		getListTest();
	}
	public static void insert(String name) {
		
		new CategoryDAO().insert(name);
	}
	public static void getListTest() {
		List<CategoryVO> list = new CategoryDAO().getList();
		for(CategoryVO vo : list) {
			System.out.println(vo);
		}
	}
}
