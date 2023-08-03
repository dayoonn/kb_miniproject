package mp.dao;

import java.sql.SQLException;
import java.util.List;

import mp.vo.BoardDto;

public interface BoardDao {
	//등록
	public void add(BoardDto dto) throws SQLException,DuplicatedIDException;
	//수정
	public void update(BoardDto dto)throws SQLException,RecordNotFoundException;
	//삭제
	public void delete(int no) throws SQLException,RecordNotFoundException;
	//갯수
	public int count() throws SQLException;
	//목록
	public List<BoardDto> list() throws SQLException;
	//no검색
	public BoardDto findByNo(int no) throws SQLException;
	//상세보기
	public BoardDto read(int no) throws SQLException,RecordNotFoundException;
}
