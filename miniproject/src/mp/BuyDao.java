package day_0803.dao;

import java.util.List;


public interface BuyDao {
	//등록
	public void add(BuyDto m) throws SQLException, LackQuantityException, java.sql.SQLException, RecordNotFoundException;
	//수정
	public void update(BuyDto m) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//삭제
	public void delete(int no) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//갯수
	public int count() throws SQLException, java.sql.SQLException;
	//목록
	public List<BuyDto> Buy_list() throws SQLException, java.sql.SQLException;
	//검색
	public BuyDto findById(int no) throws SQLException, java.sql.SQLException, ClassNotFoundException;

}
