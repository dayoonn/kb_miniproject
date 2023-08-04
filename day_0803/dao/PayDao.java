package day_0803.dao;

import java.util.List;


public interface PayDao {
	//등록
	public void add(PayDto m) throws SQLException, LackQuantityException, java.sql.SQLException, RecordNotFoundException, DuplicatedIdException;
	//수정
	public void update(PayDto m) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//삭제
	public void delete(String id) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//갯수
	public int count() throws SQLException, java.sql.SQLException;
	//목록
	public List<PayDto> Pay_list() throws SQLException, java.sql.SQLException;
	//검색
	public PayDto findById(String id) throws SQLException, java.sql.SQLException, ClassNotFoundException;

}
