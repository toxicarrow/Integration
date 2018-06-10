package com.main;


import com.main.dao.adept.AccountADao;
import com.main.dao.adept.ClassADao;
import com.main.dao.adept.StudentADao;
import com.main.dao.cdept.AccountCDao;
import com.main.dao.cdept.StudentCDao;
import com.main.entity.adept.AccountA;
import com.main.entity.adept.StClassA;
import com.main.entity.adept.StudentA;
import com.main.entity.cdept.StudentC;
import com.main.service.aService.AService;
import com.main.service.cService.CService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	@Autowired
	private AccountADao accountADao;
	@Autowired
	private StudentADao studentADao;
	@Autowired
	private StudentCDao studentCDao;
	@Autowired
	private AccountCDao accountCDao;
	@Autowired
	private ClassADao classADao;
	@Autowired
	private CService cService;
	@Autowired
	private AService aService;
	@Test
	public void testAccountA() {
		System.out.println(accountADao.findOne("alan").getPassword());
					String name=getRandomString(4);
//			AccountA accountA=new AccountA();
//			accountA.setAcc(name);
//			accountA.setPassword("123456");
//			accountADao.save(accountA);
	}
	@Test
	public void testClassA() {
		System.out.println(classADao.findAll().size());
//		String name=getRandomString(4);
//			AccountA accountA=new AccountA();
//			accountA.setAcc(name);
//			accountA.setPassword("123456");
//			accountADao.save(accountA);
	}
//	@Test
//	public void addAccountA() {
////		System.out.println(accountADao.findOne("alan").getPassword());
//		for(int i=10;i<=50;i++){
//			String name=getRandomString(4);
//			AccountA accountA=new AccountA();
//			accountA.setAcc(name);
//			accountA.setPassword("123456");
//			accountADao.save(accountA);
//
//			StudentA studentA=new StudentA();
//			studentA.setSno("a15"+i);
//			studentA.setSnm(name);
//			if(i%2==0){
//				studentA.setSex("m");
//			}
//			else{
//				studentA.setSex("w");
//			}
//			studentA.setAccount(name);
//			studentA.setSde("a院");
//			studentADao.save(studentA);
//		}
////	}
//
//	@Test
//	public void addStudentC(){
//		for(int i=11;i<=50;i++){
//			String sno="150"+i;
//			String snm=getRandomString(4);
//			StudentC studentC=new StudentC();
//			studentC.setSno(sno);
//			studentC.setSde("c院系");
//			studentC.setSnm(snm);
//			studentC.setPwd("123456");
//			if(i%2==0){
//				studentC.setSex("m");
//			}
//			else{
//				studentC.setSex("w");
//			}
//			studentCDao.save(studentC);
//		}
//	}

//	@Test
//	public void CChooseClass(){
//				for(int i=10;i<=50;i++){
//			String sno="150"+i;
//			if(i%2==0){
//				for(int j=2;j<=8;j=j+2){
//					String cno="c00"+j;
//					cService.chooseClass(sno,cno);
//				}
//				cService.chooseClass(sno,"c010");
//			}
//			else {
//				for (int j = 1; j <= 9; j = j + 2) {
//					String cno = "c00" + j;
//					cService.chooseClass(sno, cno);
//				}
//			}
//		}
//	}

//	@Test
//	public void AChooseClass(){
//		for(int i=10;i<=50;i++){
//			String sno="a15"+i;
//			if(i%2==0){
//				for(int j=2;j<=8;j=j+2){
//					String cno="a00"+j;
////					System.out.println(sno);
////					System.out.println(cno);
//					aService.chooseClass(sno,cno);
//				}
//				aService.chooseClass(sno,"a010");
//			}
//			else {
//				for (int j = 1; j <= 9; j = j + 2) {
//					String cno = "a00" + j;
//					aService.chooseClass(sno, cno);
//				}
//			}
//		}
//	}

	private String getRandomString(int length){
		String str="zxcvbnmlkjhgfdsaqwertyuiop";
		//由Random生成随机数
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		//长度为几就循环几次
		for(int i=0; i<length; ++i){
			//产生0-61的数字
			int number=random.nextInt(26);
			//将产生的数字通过length次承载到sb中
			sb.append(str.charAt(number));
		}
		//将承载的字符转换成字符串
		return sb.toString();
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
