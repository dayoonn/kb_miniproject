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

public class StockDaoImpl implements StockDao {
	
	@Override
	public void add(StockDto dto) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (findById(dto.getPRODUCT_ID())!= null) {//해당 아이디가 이미 있다면 
        		throw new RecordNotFoundException(dto.getPRODUCT_ID()+"는 이미 사용중");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "INSERT INTO STOCK(PRODUCT_ID,PRODUCT_QUANTITY ";
            sql += "VALUES( ? , ?)";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getPRODUCT_ID());
            pstmt.setInt(2, dto.getPRODUCT_QUANTITY());
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void Stock_update(String id, int quan) throws SQLException, RecordNotFoundException {
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
            String sql = "UPDATE STOCK SET PRODUCT_QUANTITY = PRODUCT_QUANTITY + ? ";
            sql += "WHERE PRODUCT_ID = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1, quan);
            pstmt.setString(2, id);

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
        		throw new RecordNotFoundException(id +"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "DELETE STOCK ";
            sql += "WHERE PRODUCT_ID = ? ";
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
	public List<StockDto> Stock_list() throws SQLException {
		List<StockDto> result = new ArrayList<StockDto>();
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM Buy order by no DESC";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			// 6. SQL 전송, 결과수신
			//   DML전송: executeUpdate() : int 
			//   SELECT전송: executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//조회결과가 있다
				String PRODUCT_ID = rs.getString("PRODUCT_ID");
				int PRODUCT_QUANTITY = rs.getInt("BUY_QUANTITY"); 
				StockDto dto = new StockDto(PRODUCT_ID, PRODUCT_QUANTITY);
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
	public StockDto findById(String id) throws SQLException, ClassNotFoundException {
		StockDto dto = null;
    	Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = JdbcUtil.connect();
        	//3.SQL 작성
    		String sql = "SELECT * FROM BUY where BUY_NUM = ?";//마지막 한칸 띄기
    		//4. Statement 생성
    		pstmt = con.prepareStatement(sql);
    		//5. 데이터 설정
    		pstmt.setString(1, id);
    		//6. SQL 전송, 결과 수신
    		//DML 전송 : executeUpdate() : int
    		//select전송 : executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();
    		if(rs.next()){
                pstmt.setString(3, dto.getPRODUCT_ID());
                pstmt.setInt(6, dto.getPRODUCT_QUANTITY());

    		}
        }catch(SQLException e){
    			System.out.println();
    		}finally{
    			
    		}
        
        return dto;
	}
	@Override
	public int count() throws SQLException {
		int count = 0;
    	//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT count(*) FROM STOCK ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
            // 6. SQL 전송, 결과수신
            count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
        return 0;
	}

}
