package day_0803.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import day_0803.util.JdbcUtil;


public class StockOrderDaoImpl implements StockOrderDao {
	
	@Override
	public void StockOrder_add(String id, int quan) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try { 
            // 3. SQL 작성
            String sql = "INSERT INTO STOCK_ORDER(ORDER_SEQ,PRODUCT_ID,ORDER_QUANTITY,ORDER_DATE) ";
            sql += "VALUES(ORDER_SEQ.NEXTVAL,?,?,SYSDATE)";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, id);
            pstmt.setLong(2, quan);
            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void StockOrder_update(StockOrderDto dto) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (StockOrder_findById(dto.getORDER_SEQ()) == null) {//해당 아이디가 없다면
        		throw new RecordNotFoundException(dto.getORDER_SEQ()+"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "UPDATE STOCK_ORDER SET ORDER_SEQ=?,PRODUCT_QUANTITY=?,ORDER_DATE=? ";
            sql += "WHERE PRODUCT_ID = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getPRODUCT_ID());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void StockOrder_delete(int id) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (StockOrder_findById(id)== null) {//해당 아이디가 없다면
        		throw new RecordNotFoundException(id +"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "DELETE STOCK_ORDER ";
            sql += "WHERE PRODUCT_ID = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setLong(1, id);
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
	public List<StockOrderDto> StockOrder_list() throws SQLException {
		List<StockOrderDto> result = new ArrayList<StockOrderDto>();
		//DBMS연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "SELECT * FROM STOCK_ORDER order by no DESC";
			// 4. Statement 생성
			pstmt = con.prepareStatement(sql);
			// 5. 데이터 설정
			// 6. SQL 전송, 결과수신
			//   DML전송: executeUpdate() : int 
			//   SELECT전송: executeQuery() : ResultSet
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//조회결과가 있다
				int ORDER_SEQ = rs.getInt("ORDER_SEQ");
				String PRODUCT_ID = rs.getString("PRODUCT_ID");
				int ORDER_QUANTITY = rs.getInt("ORDER_QUANTITY"); 
				String ORDER_DATE = rs.getString("ORDER_DATE"); 
				StockOrderDto dto = new StockOrderDto(ORDER_SEQ, PRODUCT_ID, ORDER_QUANTITY,ORDER_DATE);
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
	public StockOrderDto StockOrder_findById(int id) throws SQLException, ClassNotFoundException {
		StockOrderDto dto = null;
    	Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = JdbcUtil.connect();
        	//3.SQL 작성
    		String sql = "SELECT * FROM STOCK_ORDER where BUY_NUM = ?";//마지막 한칸 띄기
    		//4. Statement 생성
    		pstmt = con.prepareStatement(sql);
    		//5. 데이터 설정
    		pstmt.setLong(1, id);
    		//6. SQL 전송, 결과 수신
    		//DML 전송 : executeUpdate() : int
    		//select전송 : executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();
    		if(rs.next()){
                pstmt.setInt(1, dto.getORDER_SEQ());
                pstmt.setString(2, dto.getPRODUCT_ID());
                pstmt.setInt(3, dto.getORDER_QUANTITY());
                pstmt.setString(4, dto.getORDER_DATE());
    		}
        }catch(SQLException e){
    			System.out.println();
    		}finally{
    			
    		}
        
        return dto;
	}
	@Override
	public int StockOrder_count() throws SQLException {
		int count = 0;
    	//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "SELECT count(*) FROM STOCK_ORDER ";
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
