package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {

	private Connection conn;
	private ResultSet rs;
	
	
	
	//기본 생성자
		public BbsDAO() {
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
		
		//작성일자 메소드
		public String getDate() {
			String sql = "select sysdate from dual;";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return ""; //데이터베이스 오류
		}

		//게시글 번호 부여 메소드
		public int getNext() {
			//현재 게시글을 내림차순으로 조회하여 가장 마지막 글의 번호를 구한다
			String sql = "select bbsID from BBS_TEST order by bbsID desc";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1) + 1;
				}
				return 1; //첫 번째 게시물인 경우
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1; //데이터베이스 오류
		}
		
		//글쓰기 메소드
		public int write(String bbsTitle, String userID, String bbsContent) {
			String sql = "insert into BBS_TEST values(?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, bbsTitle);
				pstmt.setString(3, userID);
				pstmt.setString(4, getDate());
				pstmt.setString(5, bbsContent);
				pstmt.setInt(6, 1); //글의 유효번호
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1; //데이터베이스 오류
		}
		
}
