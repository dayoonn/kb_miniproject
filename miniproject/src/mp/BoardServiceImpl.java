package mp;

import java.sql.SQLException;
import java.util.List;

import mp.dao.BoardDao;
import mp.dao.DuplicatedIDException;
import mp.dao.RecordNotFoundException;
import mp.vo.BoardDto;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao=new BoardDaoImpl();
	@Override
	public List<BoardDto> list() throws BoardException {
		List<BoardDto> list=null;
		try {
			list = boardDao.list();
		}catch(SQLException e) {
			throw new BoardException(e.getMessage());
		}
		return list;
	}

	@Override
	public BoardDto read(int no) throws BoardException, RecordNotFoundException {
		BoardDto dto=null;
		try {
			dto=boardDao.findByNo(no);
	    	if(dto==null)
	    		throw new RecordNotFoundException(no+"번 게시물은 존재하지 않습니다.");
			dto=boardDao.read(no);
		} catch (SQLException e) {
			throw new BoardException(e.getMessage());
		} 
		return dto;
	}

	@Override
	public boolean update(BoardDto dto) throws BoardException, RecordNotFoundException {
		BoardDto boardDto;
		try {
			boardDto = boardDao.findByNo(dto.getNo());
			if(boardDto==null) 
				throw new RecordNotFoundException(dto.getNo()+"번 게시물은 존재하지 않습니다.");
			boardDao.update(dto);
		} catch (SQLException e) {
			throw new BoardException(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean delete(int no) throws BoardException, RecordNotFoundException {
		try {
			BoardDto dto=boardDao.findByNo(no);
			if(dto==null) 
				throw new RecordNotFoundException(no+"번 게시물은 존재하지 않습니다.");
			boardDao.delete(no);
			return true;
		} catch (SQLException e) {
			throw new BoardException(e.getMessage());
		}
	}

	@Override
	public int count() throws BoardException {
		int count;
		try {
			count=boardDao.count();
		} catch (SQLException e) {
			throw new BoardException(e.getMessage());
		}
		return count;
	}

	@Override
	public boolean add(BoardDto dto) throws BoardException {
		try {
			boardDao.add(dto);
		} catch (SQLException e) {
			throw new BoardException(e.getMessage());
		} catch (DuplicatedIDException e) {
		}
		return true;
	}

}
