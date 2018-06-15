package br.com.intelipost.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import br.com.intelipost.domain.UserIntelipost;

public class UserIntelipostRepositoryImpl implements UserIntelipostRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserIntelipost getUserByEmail(String email) {
		try (StatelessSession statelessSession = em.unwrap(Session.class).getSessionFactory().openStatelessSession()) {
			Query query = statelessSession.createQuery("SELECT u FROM UserIntelipost u where u.email=:email")
					.setReadOnly(true).setCacheable(false);
			query.setParameter("email", email);
			query.setMaxResults(1);
			return (UserIntelipost) query.uniqueResult();
		}

	}

}
