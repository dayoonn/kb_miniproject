package day_0803.dao;

import java.util.List;


public interface StockOrderDao {
	//등록
	public void StockOrder_add(String id, int quan) throws SQLException, LackQuantityException, java.sql.SQLException, RecordNotFoundException;
	//수정
	public void StockOrder_update(StockOrderDto m) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//삭제
	public void StockOrder_delete(int id) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//갯수
	public int StockOrder_count() throws SQLException, java.sql.SQLException;
	//목록
	public List<StockOrderDto> StockOrder_list() throws SQLException, java.sql.SQLException;
	//검색
	public StockOrderDto StockOrder_findById(int id) throws SQLException, java.sql.SQLException, ClassNotFoundException;

}
