package com.elms.authenticationservice.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.elms.authenticationservice.models.Instructor;

@DataJpaTest
class InstructorRepoTest {

	@Autowired
	private InstructorRepo repo ;
	
	@Test
	void testInstructorRepo() {
		Instructor inst = new Instructor();
		inst.setInstructorId(1);
		inst.setInstructorImage(null);
		inst.setInstructorName("prateek");
		inst.setInstructorPassword("hello");
		inst.setBankIfscCode("abc");
		inst.setAccountNumber(BigInteger.valueOf(100));
		inst.setInstructorEmail("prateek@123");
		repo.save(inst);
		String actual = repo.findByInstructorEmail("prateek@123").getInstructorName();
		String expected = "prateek";
		assertEquals(actual,expected);
	}

}
