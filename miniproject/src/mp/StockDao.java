package day_0803.dao;

import java.util.List;


public interface StockDao {
	//등록
	public void add(StockDto m) throws SQLException, LackQuantityException, java.sql.SQLException, RecordNotFoundException;
	//수정
	public void Stock_update(String id, int quan) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//삭제
	public void delete(String id) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//갯수
	public int count() throws SQLException, java.sql.SQLException;
	//목록
	public List<StockDto> Stock_list() throws SQLException, java.sql.SQLException;
	//검색
	public StockDto findById(String id) throws SQLException, java.sql.SQLException, ClassNotFoundException;

}
