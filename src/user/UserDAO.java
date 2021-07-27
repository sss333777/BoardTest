package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	private Connection conn; //자바와 데이터베이스를 연결
	private PreparedStatement pstmt; //쿼리문 대기 및 설정
	private ResultSet rs; //결과값 받아오기
	
	
	//기본 생성자
	//UserDAO가 실행되면 자동으로 생성되는 부분
	//메소드마다 반복되는 코드를 이곳에 넣으면 코드가 간소화된다
	public UserDAO(){
		try {
			String dbURL = "jdbc:oracle:thin:@172.31.137.51:1557:DEMINI";
			String dbID = "POSMINI";
			String dbPassword = "demini1";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			System.out.println("Connection Success : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//로그인 영역
	public int login(String userID, String userPassword){
		String sql = "select userPassword from USER_TABLE where userID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals(userPassword))
					return 1;	//로그인성공
				else
					return 0;	//비밀번호 틀림
			}
			return -1; //아이디 없음
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -2; //오류
	}
	
	public int join(User user){
		String sql = "insert into USER_TABLE values (?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
}
