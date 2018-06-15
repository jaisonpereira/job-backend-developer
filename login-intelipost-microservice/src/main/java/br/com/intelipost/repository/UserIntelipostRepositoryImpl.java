package br.com.intelipost.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.intelipost.domain.UserIntelipost;

@CacheConfig(cacheNames = "users_cache")
public class UserIntelipostRepositoryImpl implements UserIntelipostRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(UserIntelipostRepositoryImpl.class);
	private static final String QUERY_CONSULTA_USER = "select id,password,name,email from tb_user_intelipost  where email= ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Cacheable
	public UserIntelipost getUserByEmail(String email) {
		logger.trace("Cache enabled , Exibição de trace unico");
		return this.jdbcTemplate.query(QUERY_CONSULTA_USER, new Object[] { email },
				rs -> (rs.next()) ? new UserIntelipost(rs.getLong("id"), rs.getString("password"), rs.getString("name"),
						rs.getString("email")) : null);
	}

}
