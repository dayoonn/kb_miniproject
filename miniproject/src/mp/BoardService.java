package mp;
/** 게시판 기능 제공 */

import java.util.List;

import mp.dao.RecordNotFoundException;
import mp.vo.BoardDto;

public interface BoardService {
	//게시물 등록
	public boolean add(BoardDto dto) throws BoardException;
	//게시물 목록
	public List<BoardDto> list() throws BoardException; //서버 오류
	//게시물 상세보기
	public BoardDto read(int no) throws BoardException,RecordNotFoundException;
	//게시물 수정
	public boolean update(BoardDto dto) throws BoardException,RecordNotFoundException;
	//게시물 삭제
	public boolean delete(int no) throws BoardException,RecordNotFoundException;
	//게시물 갯수
	public int count() throws BoardException;
}
