package com.elms.registrationservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elms.registrationservice.models.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Integer>{
	Instructor findByEmail(String email);
	@Modifying
	@Query(value = "update instructor set instructor_password=:password where instructor_email=:usermail",nativeQuery = true)
	int updateInstructorPassword(@Param("usermail") String useremail,@Param("password") String password);
}
