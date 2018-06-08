package com.main;


import com.main.dao.cdept.AccountCDao;
import com.main.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	@Autowired
	private AccountMapper accountDao;
	@Autowired
	private AccountCDao accountCDao;
	@Test
	public void testAccountA() {
		System.out.println(accountDao.findByAccount("11").getPassword());
	}
	@Test
	public void testAccountC() {
		System.out.println(accountCDao.findOne("a").getPassword());
	}

}
