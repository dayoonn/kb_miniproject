package day_0803.dao;

import java.util.List;

public class PayDaoImplExam {
	public static void main(String[] args) throws SQLException, java.sql.SQLException {
//		add();
//      update();
//		delete();
//		find();
//		count();
		Pay_list();
	}
	private static void Pay_list() throws java.sql.SQLException {
		PayDao buyDao = new PayDaoImpl();
		try {
			List<PayDto> list = buyDao.Pay_list();
			System.out.println("전체 조회");
			for(PayDto dto : list) {
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("DBMS 오류 발생");
		}
	}
	private static void count() throws java.sql.SQLException {
		PayDao paysDao = new PayDaoImpl();
		try {
			int count = paysDao.count();
			System.out.println();
		} catch (SQLException e) {
			System.out.println("DBMS 오류 발생");
		}
	}
	
	private static void update() throws java.sql.SQLException {
		PayDao paysDao = new PayDaoImpl();
		try {
			paysDao.update(new PayDto());
			System.out.println("수정 성공");
		} catch (SQLException e) {
			System.out.println("DBMS 오류 발생");
		} catch (RecordNotFoundException e) {
			System.out.println();//무결성 제약조건 때문에			
		}
	}
	
	private static void add() throws java.sql.SQLException, LackQuantityException, RecordNotFoundException, DuplicatedIdException {
		PayDao paysDao = new PayDaoImpl();
		try {
			paysDao.add(new PayDto());
			System.out.println("등록 성공");
		} catch (SQLException e) {
			System.out.println("DBMS 오류 발생");
		}
	}
}
