package com.main;


import com.main.dao.cdept.AccountCDao;
import com.main.mapper.AccountMapper;
import com.main.service.cService.CService;
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
	@Autowired
	private CService cService;
//	@Test
//	public void testAccountA() {
//		System.out.println(accountDao.findByAccount("11").getPassword());
//	}
//	@Test
//	public void testAccountC() {
//		System.out.println(accountCDao.findOne("a").getPassword());
//	}
	@Test
	public void testService() {
		System.out.println(cService.getAllChooseClass("15002").size());
		System.out.println(cService.getAllChooseStudents("c006").size());
//		cService.cancelClass("15010","c008");
	}
}
