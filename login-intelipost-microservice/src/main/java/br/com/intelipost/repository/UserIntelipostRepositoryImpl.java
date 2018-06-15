package br.com.intelipost.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.intelipost.domain.UserIntelipost;

public class UserIntelipostRepositoryImpl implements UserIntelipostRepositoryCustom {

	private static final String QUERY_CONSULTA_USER = "select id,password,name,email from tb_user_intelipost  where email= ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserIntelipost getUserByEmail(String email) {
		return this.jdbcTemplate.query(QUERY_CONSULTA_USER, new Object[] { email },
				rs -> (rs.next()) ? new UserIntelipost(rs.getLong("id"), rs.getString("password"), rs.getString("name"),
						rs.getString("email")) : null);

	}

}
