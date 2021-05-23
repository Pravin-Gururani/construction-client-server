package com.construction.repository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.construction.models.User;



@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	User findUserByUsername(String username);
	List<User> findListByUsername(String username);
	
	@Query(value="SELECT name FROM users WHERE id in (select user_id from user_roles where role_id=2)",nativeQuery = true)
	List<String> findAllEmployees();

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Modifying
	@Query(value="update users set is_enabled=:isEnabled where id=:id",nativeQuery = true)
	void userEnableDisable(@Param("id") Long id, @Param("isEnabled") boolean isEnabled);
	
	@Query(value="select * from users where employee_data in (select id from employee_data where occupation=(select occupation_id from occupation where occupation_name=:occupationName))",nativeQuery = true)
	List<User> getByOccupation(@Param("occupationName") String occupationName);
	
	
	
	
	
}