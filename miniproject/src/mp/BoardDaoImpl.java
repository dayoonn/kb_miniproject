package mp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mp.dao.BoardDao;
import mp.dao.DuplicatedIDException;
import mp.dao.RecordNotFoundException;
import mp.util.JdbcUtil;
import mp.vo.BoardDto;

public class BoardDaoImpl implements BoardDao {

	@Override
	public void add(BoardDto dto) throws SQLException, DuplicatedIDException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "INSERT INTO BOARD(no,writer,title,content,regdate) ";
            sql += "VALUES( BOARD_SEQ.NEXTVAL , ? , ? , ? ,SYSDATE)";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getWriter());
            pstmt.setString(2, dto.getTitle());
            pstmt.setString(3,dto.getContent());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
	}

	@Override
	public void update(BoardDto dto) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "UPDATE BOARD SET content=? ";
            sql += "WHERE no = ?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getContent());
            pstmt.setInt(2, dto.getNo());
           
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void delete(int no) throws SQLException, RecordNotFoundException {
		
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "DELETE FROM BOARD ";
            sql += "WHERE no=?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1,no);
           
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
	}

	@Override
	public int count() throws SQLException {
		int count=0; //반환 타입
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT COUNT(*) FROM BOARD ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정

			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			rs.next();  // 조회결과가 있다
			count=rs.getInt(1); //레코드 갯수 저장
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

		return count;
	}

	@Override
	public BoardDto findByNo(int no) throws SQLException {
		BoardDto dto = null;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM BOARD WHERE no = ? ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setInt(1, no);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회결과가 있다
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Date regdate = rs.getDate("regdate");
				String content = rs.getString("content");
				dto = new BoardDto(no, title, writer, regdate, content); // while문이 돌때마다 dto 생성
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

	@Override
	public List<BoardDto> list() throws SQLException {
		List<BoardDto> result = new ArrayList<BoardDto>();
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM BOARD ORDER BY no DESC "; //최신글 우선

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
		
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // 조회결과가 있다
				int no=rs.getInt("no");
				String title=rs.getString("title");
				String writer=rs.getString("writer");
				Date regdate=rs.getDate("regdate");
				String content=rs.getString("content");
				BoardDto dto = new BoardDto(no,title,writer,regdate,content); //while문이 돌때마다 dto 생성
				result.add(dto); //List에 추가
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

	@Override
	public BoardDto read(int no) throws SQLException {
		
    	
		BoardDto dto = null;
		// DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM BOARD WHERE no = ? ";

			// 4. Statement 생성
			pstmt = con.prepareStatement(sql); // 메서드명은 prepare. 타입은 Prepared

			// 5. 데이터 설정
			pstmt.setInt(1, no);
			// 6. SQL 전송, 결과 수신
			// DML 전송 : executeUpdate() -> int 반환
			// SELECT 전송 : executeQuery() -> ResultSet 반환
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회결과가 있다
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Date regdate = rs.getDate("regdate");
				String content = rs.getString("content");
				dto = new BoardDto(no, title, writer, regdate, content); 
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

}
