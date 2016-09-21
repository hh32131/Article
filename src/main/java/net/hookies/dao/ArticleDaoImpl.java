package net.hookies.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.hookies.vo.ArticleVO;

public class ArticleDaoImpl implements ArticleDao {

	@Override
	public List<ArticleVO> getArticles(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ARTICLE", "tjrgns12");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT 	ARTCL_ID");
			query.append("			, SBJ");
			query.append("			, CONT");
			query.append("			, CRT_DT");
			query.append(" FROM		ARTICLE ");
			
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			List<ArticleVO> articles = new ArrayList<ArticleVO>();
			
			ArticleVO articleVO = null;
			
			while(rs.next()) {
				
				articleVO = new ArticleVO();
				articleVO.setArticleId(rs.getInt("ARTCL_ID"));
				articleVO.setSubject(rs.getString("SBJ"));
				articleVO.setContents(rs.getString("CONT"));
				articleVO.setCreateDate(rs.getString("CRT_DT"));
				
				articles.add(articleVO);
			}
			return articles;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		finally{
			
			if ( rs !=null ) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if ( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	
}
