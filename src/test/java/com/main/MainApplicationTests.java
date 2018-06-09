package com.main;


import com.main.dao.adept.AccountADao;
import com.main.dao.cdept.AccountCDao;
import com.main.service.aService.AService;
import com.main.service.cService.CService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	@Autowired
	private AccountADao accountADao;
	@Autowired
	private AccountCDao accountCDao;
	@Autowired
	private CService cService;
	@Autowired
	private AService aService;
	@Test
	public void testAccountA() {
		System.out.println(accountADao.findOne("alan").getPassword());
	}
//	@Test
//	public void testAccountC() {
//		System.out.println(accountCDao.findOne("a").getPassword());
//	}
	@Test
	public void testAService() {
		System.out.println(aService.getAllChooseClass("a1502").size());
		System.out.println(aService.getAllChooseStudents("a006").size());
//		cService.cancelClass("15010","c008");
	}
	@Test
	public void testAService2() {
//		System.out.println(aService.getAllChooseClass("a1509").size());
//		aService.chooseClass("a1509","a002");
//		System.out.println(aService.getAllChooseClass("a1509").size());
		aService.cancelClass("a1509","a002");
		System.out.println(aService.getAllChooseClass("a1509").size());
	}
	@Test
	public void testService() {
		System.out.println(cService.getAllChooseClass("15002").size());
		cService.chooseClass("15002","c001");
		System.out.println(cService.getAllChooseClass("15002").size());
		cService.cancelClass("15002","c001");
		System.out.println(cService.getAllChooseClass("15002").size());
//		System.out.println(cService.getAllChooseStudents("c006").size());
//		cService.cancelClass("15010","c008");
	}
}
