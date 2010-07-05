package org.palacehotel.wordpress.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.jdbc.JdbcDaoImpl;

public class UserdetailsDao extends JdbcDaoImpl {

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) {
		List<UserDetails> list = getJdbcTemplate().query("select * from sf_guard_user where username=?",
				new Object[] {username},
				new RowMapper() {
			        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			            WordpressUserdetails guest = new WordpressUserdetails(
			            		rs.getString("username"),
			            		rs.getString("password"),
			            		rs.getString("salt"),
			            		new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_USER")});
			            return guest;
			        }
				}
			);
		return list.size()>0? list.get(0) : null;
	}
	
}
