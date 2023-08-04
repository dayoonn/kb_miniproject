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

public class BuyDaoImpl implements BuyDao {
	
	@Override
	public void add(BuyDto dto) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (findById_buynum(dto.getBUY_NUM())!= null) {//해당 아이디가 이미 있다면 
        		throw new RecordNotFoundException(dto.getBUY_NUM()+"는 이미 사용중");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "INSERT INTO BUY(BUY_NUM, PAY_ID,PRODUCT_ID, PRODUCT_PRICE,BUY_QUANTITY,BUY_PRICE) ";
            sql += "VALUES( ? , ?, ?, ? , ?, ?)";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setInt(1, dto.getBUY_NUM());
            pstmt.setString(2, dto.getPAY_ID());
            pstmt.setString(3, dto.getPRODUCT_ID());
            pstmt.setInt(4, dto.getPRODUCT_PRICE());
            pstmt.setInt(5, dto.getBUY_QUANTITY());
            pstmt.setInt(6, dto.getBUY_PRICE());
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public void update(BuyDto dto) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	//등록 여부 검사
        	if (findById_buynum(dto.getBUY_NUM())== null) {//해당 아이디가 없다면
        		throw new RecordNotFoundException(dto.getBUY_NUM()+"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "UPDATE BUY SET PAY_ID=?,PRODUCT_ID=?,PRODUCT_PRICE=?,BUY_QUANTITY=?,BUY_PRICE=? ";
            sql += "WHERE BUY_NUM=? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, dto.getPAY_ID());
            pstmt.setString(2, dto.getPRODUCT_ID());
            pstmt.setInt(3, dto.getPRODUCT_PRICE());
            pstmt.setInt(4, dto.getBUY_QUANTITY());
            pstmt.setInt(5, dto.getBUY_PRICE());
            pstmt.setInt(6, dto.getBUY_NUM());
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
        	//등록 여부 검사
        	if (findById_buynum(no)== null) {//해당 아이디가 없다면
        		throw new RecordNotFoundException(no +"는 없음");//throw니까 메서드가 멈추고 메세지 출력 
        	}
            con = JdbcUtil.connect();//연결 전 등록 여부 검사 
            // 3. SQL 작성
            String sql = "DELETE BUY ";
            sql += "WHERE BUY_NUM = ? ";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setLong(1, no);
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
	public List<BuyDto> Buy_list() throws SQLException {
		List<BuyDto> result = new ArrayList<BuyDto>();
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
				int BUY_NUM = rs.getInt("BUY_NUM");
				String PAY_ID = rs.getString("PAY_ID");
				String PRODUCT_ID = rs.getString("PRODUCT_ID");
				int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				int BUY_QUANTITY = rs.getInt("BUY_QUANTITY"); 
				int BUY_PRICE = rs.getInt("BUY_PRICE");
				BuyDto dto = new BuyDto(BUY_NUM, PAY_ID,PRODUCT_ID, PRODUCT_PRICE,BUY_QUANTITY,BUY_PRICE);
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
	public BuyDto findById_buynum(int no) throws SQLException, ClassNotFoundException {
		BuyDto dto = null;
    	Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = JdbcUtil.connect();
        	//3.SQL 작성
    		String sql = "SELECT * FROM BUY where BUY_NUM = ?";//마지막 한칸 띄기
    		//4. Statement 생성
    		pstmt = con.prepareStatement(sql);
    		//5. 데이터 설정
    		pstmt.setLong(1, no);
    		//6. SQL 전송, 결과 수신
    		//DML 전송 : executeUpdate() : int
    		//select전송 : executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();
    		if(rs.next()){
                pstmt.setInt(1, dto.getBUY_NUM());
                pstmt.setString(2, dto.getPAY_ID());
                pstmt.setString(3, dto.getPRODUCT_ID());
                pstmt.setInt(5, dto.getPRODUCT_PRICE());
                pstmt.setInt(6, dto.getBUY_QUANTITY());
                pstmt.setInt(6, dto.getBUY_PRICE());
    		}
        }catch(SQLException e){
    			System.out.println();
    		}finally{
    			
    		}
        
        return dto;
	}
	public BuyDto findById_payid(String no) throws SQLException, ClassNotFoundException {
		BuyDto dto = null;
    	Connection con = null;
        PreparedStatement pstmt = null;
        try {
        	con = JdbcUtil.connect();
        	//3.SQL 작성
    		String sql = "SELECT * FROM BUY where PAY_ID = ?";//마지막 한칸 띄기
    		//4. Statement 생성
    		pstmt = con.prepareStatement(sql);
    		//5. 데이터 설정
    		pstmt.setString(1, no);
    		//6. SQL 전송, 결과 수신
    		//DML 전송 : executeUpdate() : int
    		//select전송 : executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();
    		if(rs.next()){
    			int BUY_NUM = rs.getInt("BUY_NUM ");
				String PAY_ID = rs.getString("PAY_ID");
				String PRODUCT_ID = rs.getString("PRODUCT_ID"); 
				int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				int BUY_QUANTITY  = rs.getInt("BUY_QUANTITY");
				int BUY_PRICE  = rs.getInt("BUY_PRICE");
				dto = new BuyDto(BUY_NUM, PAY_ID,PRODUCT_ID,PRODUCT_PRICE,BUY_QUANTITY,BUY_PRICE);
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
            String sql = "SELECT count(*) FROM BUY ";
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
