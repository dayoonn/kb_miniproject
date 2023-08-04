package day_0803.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import day_0803.util.JdbcUtil;
//pay_id 입력받기
//해당 pay_id로 조회하여 buy 리스트를 테이블에서 product_id,product_price, subprice 출력
//pay_id로 조회하여 PAY 테이블 전체 출력.

public class PayDaoImpl implements PayDao {
	
	@Override
	public void add(PayDto dto) throws SQLException, DuplicatedIdException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (findById(dto.getPAY_ID())!= null) {//해당 아이디가 이미 있다면 
        		throw new DuplicatedIdException(dto.getPAY_ID()+"는 이미 사용중");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "INSERT INTO PAY(PAY_ID, PAY_DATE, PAY_TIME, PAY_TYPE,FINAL_AMOUNT) ";
            sql += "VALUES( ? ,  SYSDATE , ? , ?, ?)";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getPAY_ID());
            pstmt.setString(2, dto.getPAY_DATE());
            pstmt.setString(3, dto.getPAY_TIME());
            pstmt.setString(5, dto.getPAY_TYPE());
            pstmt.setString(6, dto.getFINAL_AMOUNT());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void update(PayDto dto) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (findById(dto.getPAY_ID())== null) {//해당 아이디가 없다면
        		throw new RecordNotFoundException(dto.getPAY_ID()+"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "UPDATE PAY SET PAY_ID=?, PAY_DATE=?, PAY_TIME=?, PAY_TYPE=?,FINAL_AMOUNT=?  ";
            sql += "WHERE no = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getPAY_ID());
            pstmt.setString(2, dto.getPAY_DATE());
            pstmt.setString(3, dto.getPAY_TIME());
            pstmt.setString(5, dto.getPAY_TYPE());
            pstmt.setString(6, dto.getFINAL_AMOUNT());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void delete(String id) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (findById(id)== null) {//해당 아이디가 없다면
        		throw new RecordNotFoundException(id+"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "DELETE PAY ";
            sql += "WHERE no = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, id);
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	//PAY테이블 전체 출력
	@Override
	public List<PayDto> Pay_list() throws SQLException {
		List<PayDto> result = new ArrayList<PayDto>();
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM Pay order by no DESC";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			// 6. SQL 전송, 결과수신
			//   DML전송: executeUpdate() : int 
			//   SELECT전송: executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//조회결과가 있다
				String PAY_ID = rs.getString("PAY_ID");
				String PAY_DATE = rs.getString("PAY_DATE");
				String PAY_TIME = rs.getString("PAY_TIME");
				String PAY_TYPE = rs.getString("PAY_TYPE"); 
				String FINAL_AMOUNT = rs.getString("FINAL_AMOUNT");
				PayDto dto = new PayDto(PAY_ID, PAY_DATE,PAY_TIME, PAY_TYPE,FINAL_AMOUNT);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;

	}

	@Override
	public PayDto findById(String ID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
