//package com.main.service.bService;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import com.main.service.dService.DService;
//import com.main.util.XMLHelper;
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.main.dao.bdept.LessonBDAO;
//import com.main.dao.bdept.SLessonBDAO;
//import com.main.entity.bdept.LessonB;
//import com.main.entity.bdept.SLessonB;
//
//@Service
//public class BLessonServiceImpl implements BLessonService{
//
//	@Autowired
//	private LessonBDAO lesson;
//	@Autowired
//	private SLessonBDAO slesson;
//	@Autowired
//	private DService dService;
//
//	private XMLHelper xmlHelper = new XMLHelper();
//
//	@Override
//	public List<LessonB> findByShare(char share) {
//		// TODO Auto-generated method stub
//		List<LessonB> list = lesson.findByShare(share);
//		return list;
//	}
//
//	@Override
//	public List<LessonB> findById(String id) {
//		// TODO Auto-generated method stub
//		List<LessonB> list = lesson.findById(id);
//		return list;
//	}
//
//	@Override
//	public List<LessonB> findByName(String name) {
//		// TODO Auto-generated method stub
//		List<LessonB> list = lesson.findByName(name);
//		return list;
//	}
//
//	@Override
//	public List<SLessonB> findByLessonId(String lid) {
//		// TODO Auto-generated method stub
//		List<SLessonB> list = slesson.findByLessonId(lid);
//		return list;
//	}
//
//	@Override
//	public List<SLessonB> findByStudentId(String sid) {
//		// TODO Auto-generated method stub
//		List<SLessonB> list = slesson.findByStudentId(sid);
//		return list;
//	}
//
//	@Override
//	public boolean addStudentToLesson(String sid, String lid) {
//		// TODO Auto-generated method stub
//		SLessonB slb = new SLessonB(lid,sid,"0");
//		slesson.saveAndFlush(slb);
//		return true;
//	}
//
//	@Override
//	public boolean deleteStudentFromLesson(String sid, String lid) {
//		// TODO Auto-generated method stub
//		slesson.deleteByLessonIdAndStudentId(lid, sid);
//		return true;
//	}
//
//	@Override
//	public boolean addLesson(LessonB lesson) {
//		// TODO Auto-generated method stub
//		this.lesson.saveAndFlush(lesson);
//		return true;
//	}
//
//	@Override
//	public boolean deleteLessonById(String lid) {
//		// TODO Auto-generated method stub
//		lesson.deleteById(lid);
//		return true;
//	}
//
//	@Override
//	public Document sendShareClass(){
//		List<LessonB> classAList = findByShare('a');
//		Document doc = DocumentHelper.createDocument();
//		Element root = doc.addElement("classes");
//		for(LessonB cb:classAList){
//			Element emp = root.addElement("class");
//			emp.addElement("编号").setText(cb.getId());
//			emp.addElement("名称").setText(cb.getName());
//			emp.addElement("课时").setText(cb.getTime());
//			emp.addElement("学分").setText(cb.getPoint());
//			emp.addElement("老师").setText(cb.getTeacher());
//			emp.addElement("地点").setText(cb.getPlace());
//		}
//		return doc;
//	}
//
//	@Override
//	public Boolean chooseShareClass(String sno,String cno){
//		Document doc = DocumentHelper.createDocument();
//		Element root = doc.addElement("choices");
//		Element emp = root.addElement("choice");
//		emp.addElement("课程编号").setText(cno);
//		emp.addElement("学号").setText(sno);
//		emp.addElement("得分").setText("0");
//		if(cno.startsWith("a")){
//			return dService.chooseShareClassFromA(doc);
//		}
//		else if(cno.startsWith("c")){
//			return dService.chooseShareClassFromC(doc);
//		}
//		return false;
//	}
//
//	@Override
//	public List<LessonB> get_AC_Class(){
//		List<LessonB> shareClass = new ArrayList();
//		List<Document> classXmls = dService.getShareClassForB();
//		for(Document xml:classXmls){
//			if(!xmlHelper.validateXml(xml,"adept\\Bclass.xsd")){
//				return null;
//			}
//			Element root = xml.getRootElement();
//			for(Iterator i = root.elementIterator(); i.hasNext();){
//				Element temp = (Element)i.next();
//				LessonB classB = new LessonB();
//				for(Iterator j = temp.elementIterator();j.hasNext();){
//					Element node = (Element) j.next();
//					String value = node.getName();
//					switch (value){
//						case "编号":
//							classB.setId(node.getText());
//							break;
//						case "名称":
//							classB.setName(node.getText());
//							break;
//						case "课时":
//							classB.setTime(node.getText());
//						case "学分":
//							classB.setPoint(node.getText());
//							break;
//						case "老师":
//							classB.setTeacher(node.getText());
//							break;
//						case "地点":
//							classB.setPlace(node.getText());
//							break;
//						default:
//							System.out.println("wrong xml "+xml.asXML());
//					}
//				}
//				shareClass.add(classB);
//			}
//		}
//		return shareClass;
//	}
//
//	@Override
//	public Boolean solveShareChoose(Document xmlChoice){
//		if(!xmlHelper.validateXml(xmlChoice,"adept\\Bchoice.xsd")){
//			return false;
//		}
//		Element root = xmlChoice.getRootElement();
//		String sno = null;
//		String cno = null;
//		for(Iterator i=root.elementIterator();i.hasNext();) {
//			Element temp = (Element) i.next();
//			for (Iterator j = temp.elementIterator(); j.hasNext(); ) {
//				Element node = (Element) j.next();
//				String value = node.getName();
//				switch (value) {
//					case "课程编号":
//						cno = node.getText();
//						break;
//					case "学号":
//						sno = node.getText();
//						break;
//				}
//			}
//		}
//		if ((sno != null) && (cno != null)) {
//			addStudentToLesson(sno,cno);
//			return true;
//		}else{
//			return false;
//		}
//
//
//	}
//
//}
