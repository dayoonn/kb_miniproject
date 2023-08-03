package day_0803.dao;

import java.util.List;

/** 게시판 기능 제공*/
public interface StoreService {
	//게시물 목록
	public List<PayDto> plist() ;//서버오류
	public List<BuyDto> blist() ;//서버오류
	public List<BuyDto> Buy_list() throws SQLException, java.sql.SQLException;
	public List<PayDto> Pay_list() throws SQLException, java.sql.SQLException;
	public List<StockDto> Stock_list() throws SQLException, java.sql.SQLException;
	//게시물 상세보기
	public PayDto pread(String id) throws RecordNotFoundException;//null이 뜨면 지워진 메세지라고 뜨도록
	public BuyDto bread(String id) throws RecordNotFoundException;//null이 뜨면 지워진 메세지라고 뜨도록
	//게시물 수정
	public boolean update(PayDto dto) throws  RecordNotFoundException;
	public boolean update(BuyDto dto) throws  RecordNotFoundException;
	public boolean Stock_update(String id, int qUAN) throws  RecordNotFoundException;
	public void StockOrder_update(StockOrderDto m) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//게시물 삭제
	public boolean delete(int no) throws RecordNotFoundException;
	public void StockOrder_delete(int id) throws SQLException, RecordNotFoundException, java.sql.SQLException;
	//게시물 갯수
	public int count() ;
	public int StockOrder_count() throws SQLException, java.sql.SQLException;
	//게시물 등록
	public boolean add(PayDto dto) ;
	public boolean StockOrder_add(String id, int quan) ;
}
