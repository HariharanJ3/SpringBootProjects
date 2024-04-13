/**
 * 
 */
package com.global.translator.ServiceImpl;



import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.global.translator.Service.TranslatorService;
import com.global.translator.model.Test;
import com.global.translator.model.UserMaster;
import com.global.translator.model.Users;
//import com.global.translator.product.repositories.ProductRepository;
import com.global.translator.repositories.UserRepository;
import com.global.translator.repositories.UsersRepository;

/**
 * @author Hariharan J
 *
 * 24-Sep-2022
 */
@Service
@Transactional
public class TranslatorServiceImpl implements TranslatorService {
	
//	@Autowired
//	ProductRepository productRepository;
	
//	@Autowired
//	UserRepository userRepository;
	
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserMaster selectUserMaster(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveTest(Test test) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Test> getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<UserMaster> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Users findByEmailid(String email) 
	{
		List<Users> users = usersRepository.findByEmail(email);
		
		return users != null ? users.get(0) : null;
	}

	@Override
	public List<Users> getUsersByRole(String role) 
	{
		return usersRepository.findByRole(role);
	}

	@Override
	public Users updateUsers(Users users) 
	{
		Users user  = usersRepository.findByUserid(users.getUserid());
		
		user.setEmail(users.getEmail());
		user.setPassword(users.getPassword());
		user.setRole(users.getRole());
		user.setUsername(users.getUsername());
		
		return usersRepository.save(user);
	}

	@Override
	public String DeleteUsersById(Integer id) {
		// TODO Auto-generated method stub
		usersRepository.deleteByUserid(id);
		
		return id+" removed from Users";
	}
	
//	@Autowired
//	@PersistenceContext(unitName = "userentityManagerFactory")
//	@PersistenceContext
//	private EntityManager entityManager;
	
//	@Autowired
//	@PersistenceContext(unitName = "productentityManagerFactory")
//	EntityManager entityManagerTest;
	
//	public UserMaster selectUserMaster(String username) {
		
//		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<UserMaster> query=cb.createQuery(UserMaster.class);
//		
//		Root<UserMaster> root=query.from(UserMaster.class);
//		
//		query.select(root).where(cb.equal(root.get("emailid"), username));
//		
//		TypedQuery<UserMaster> tq=entityManager.createQuery(query);
//		
//		UserMaster userdata=tq.getResultList().stream().findFirst().orElse(null);
		
//		UserMaster userdata=userRepository.findByEmailid(username);
		
//		return null;
//	}
//
//	@Override
//	public void saveTest(Test test) {
//		productRepository.save(test);
//	}


//	@Override
//	public List<Test> getDetails() {
//		
//		CriteriaBuilder builder= entityManagerTest.getCriteriaBuilder();
//		
//		CriteriaQuery<Test> query=builder.createQuery(Test.class);
//		
//		Root<Test> root=query.from(Test.class);
//		
//		query.select(root);
//		
//		TypedQuery<Test> typedQuery=entityManagerTest.createQuery(query);
//		
//		return typedQuery.getResultList();
		
//		List<Test> test=productRepository.findAll();
//		
//		return test;
//	}
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
//	@Override
//	public List<UserMaster> selectAll()
//	{
//		String sqlQuery="select * from usermaster";
//		return jdbcTemplate.query(sqlQuery,new BeanPropertyRowMapper<>(UserMaster.class));
//	}*/
	
//	public Users createUser(Users user) {
//        return usersRepository.save(user);
//    }


}
