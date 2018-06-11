package com.main.service.aService;

import com.main.dao.adept.AccountADao;
import com.main.dao.adept.ClassADao;
import com.main.dao.adept.StClassADao;
import com.main.dao.adept.StudentADao;
import com.main.entity.adept.AccountA;
import com.main.entity.adept.ClassA;
import com.main.entity.adept.StClassA;
import com.main.entity.adept.StudentA;
import com.main.entity.cdept.StClassC;
import com.main.entity.cdept.StudentC;
import com.main.service.dService.DService;
import com.main.util.XMLHelper;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AServiceImpl implements AService{
    @Autowired
    private ClassADao classADao;
    @Autowired
    private AccountADao accountADao;
    @Autowired
    private StudentADao studentADao;
    @Autowired
    private StClassADao stClassADao;
    @Autowired
    private DService dService;

    private XMLHelper xmlHelper = new XMLHelper();
    @Override
    public List<ClassA> getAllClass() {
        return classADao.findAll();
    }

    @Override
    public List<ClassA> getAllShareClass() {
        return classADao.findAllByShare("y");
    }

    @Override
    public List<StudentA> getAllChooseStudents(String classNo) {
        List<StClassA> stClassList=stClassADao.findAllByCno(classNo);
        List<StudentA> studentAList=new ArrayList<>();
        for(int i=0;i<stClassList.size();i++){
            studentAList.add(studentADao.findBySno(stClassList.get(i).getSno()));
        }
        return studentAList;
    }

    @Override
    public List<StudentA> getAllStudents() {
        return studentADao.findAll();
    }

    @Override
    public List<ClassA> getAllChooseClass(String studentNo) {
        List<StClassA> stClassList=stClassADao.findAllBySno(studentNo);
        List<ClassA> classAList=new ArrayList<>();
        for(int i=0;i<stClassList.size();i++){
            classAList.add(classADao.findByCno(stClassList.get(i).getCno()));
        }
        return classAList;
    }

    @Override
    public boolean hasChoose(String sno, String cno) {
        StClassA stClassA=stClassADao.findBySnoAndCno(sno,cno);
        if(stClassA==null){
            return false;
        }
        return true;
    }

    @Override
    public String getSnoByAccount(String acc) {
        return studentADao.findBySnm(acc).getSno();
    }

    @Override
    public boolean login(String account, String pwd) {

        AccountA accountA=accountADao.findByAcc(account);
        if(accountA==null){
            return false;
        }
        if(accountA.getPassword().equals(pwd)){

            return true;
        }
        return false;
    }

    @Override
    public AccountA findByAccount(String account) {
        return accountADao.findByAcc(account);
    }

    @Override
    public void chooseClass(String sno, String cno) {
        StClassA stClassA=new StClassA();
        stClassA.setCno(cno);
        stClassA.setSno(sno);
        stClassADao.save(stClassA);
    }

    @Override
    @Transactional
    public void cancelClass(String sno, String cno) {
        stClassADao.deleteBySnoAndCno(sno,cno);
    }

    @Override
    public StudentA getStudentByAccunt(String account) {
        return studentADao.findByAccount(account);
    }

    @Override
    public Document sendShareClass(){
        List<ClassA> classAList = getAllShareClass();
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("classes");
        for(ClassA ca:classAList){
            Element emp = root.addElement("class");
            emp.addElement("课程编号").setText(ca.getCno());
            emp.addElement("课程名称").setText(ca.getCnm());
            emp.addElement("课时").setText("1");
            emp.addElement("学分").setText(ca.getCpt());
            emp.addElement("授课老师").setText(ca.getTec());
            emp.addElement("授课地点").setText(ca.getPla());
        }
        return doc;
    }

    @Override
    public Boolean chooseShareClass(String sno,String cno){
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("choices");
        Element emp = root.addElement("choice");
        emp.addElement("课程编号").setText(cno);
        emp.addElement("学生编号").setText(sno);
        emp.addElement("成绩").setText("0");
        if(cno.startsWith("b")){
           return dService.chooseShareClassFromB(doc);
        }
        else if(cno.startsWith("c")){
            return dService.chooseShareClassFromC(doc);
        }
        return false;
    }

    @Override
    public List<ClassA> get_BC_Class(){
        List<ClassA> shareClass = new ArrayList();
        List<Document> classXmls = dService.getShareClassForA();
        for(Document xml:classXmls){
            if(!xmlHelper.validateXml(xml,"adept\\Aclass.xsd")){
                return null;
            }
            Element root = xml.getRootElement();
            for(Iterator i=root.elementIterator();i.hasNext();){
                Element temp = (Element)i.next();
                ClassA classA = new ClassA();
                for(Iterator j = temp.elementIterator();j.hasNext();){
                    Element node = (Element) j.next();
                    String value = node.getName();
                    switch (value){
                        case "课程编号":
                            classA.setCno(node.getText());
                            break;
                        case "课程名称":
                            classA.setCnm(node.getText());
                            break;
                        case "学分":
                            classA.setCpt(node.getText());
                            break;
                        case "授课老师":
                            classA.setTec(node.getText());
                            break;
                        case "授课地点":
                            classA.setPla(node.getText());
                            break;
                        default:
                            System.out.println("wrong xml "+xml.asXML());
                    }
                }
                shareClass.add(classA);
            }
        }
        return shareClass;
    }

    @Override
    public Boolean solveShareChoose(Document xmlChoice){
        if(!xmlHelper.validateXml(xmlChoice,"adept\\Achoice.xsd")){
            return false;
        }
        Element root = xmlChoice.getRootElement();
        String sno = null;
        String cno = null;
        for(Iterator i=root.elementIterator();i.hasNext();) {
            Element temp = (Element) i.next();
            for (Iterator j = temp.elementIterator(); j.hasNext(); ) {
                Element node = (Element) j.next();
                String value = node.getName();
                switch (value) {
                    case "课程编号":
                        cno = node.getText();
                        break;
                    case "学生编号":
                        sno = node.getText();
                        break;
                }
            }
        }
        if ((sno != null) && (cno != null)) {
            chooseClass(sno,cno);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Document sendShareChoice(String sno){
        List<StClassA> stClassAList = stClassADao.findAllBySno(sno);
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("choices");
        for(StClassA stClassA:stClassAList){
            Element emp = root.addElement("choice");
            emp.addElement("课程编号").setText(stClassA.getCno());
            emp.addElement("学生编号").setText(stClassA.getSno());
            emp.addElement("成绩").setText(String.valueOf(stClassA.getGrd()));
        }
        return doc;
    }

    @Override
    public boolean hasChooseShare(String sno,String cno){
        List<StClassA> stClassAList = new ArrayList<>();
        List<Document> shareList = dService.getStnChoiceForA(sno);
        for(Document xml:shareList){
            if(!xmlHelper.validateXml(xml,"adept\\Achoice.xsd")){
                return false;
            }
            Element root = xml.getRootElement();
            for(Iterator i=root.elementIterator();i.hasNext();) {
                Element temp = (Element) i.next();
                StClassA stClassA = new StClassA();
                for (Iterator j = temp.elementIterator(); j.hasNext(); ) {
                    Element node = (Element) j.next();
                    String value = node.getName();
                    switch (value) {
                        case "课程编号":
                            stClassA.setCno(node.getText());
                            break;
                        case "学生编号":
                            stClassA.setSno(node.getText());
                            break;
                        case "成绩":
                            stClassA.setGrd(Integer.parseInt(node.getText()));
                        default:
                            System.out.println("wrong xml "+xml.asXML());
                    }
                }
                stClassAList.add(stClassA);
            }
        }

        for(int i =0;i<stClassAList.size();i++){
            if(stClassAList.get(i).getCno().equals(cno) && stClassAList.get(i).getSno().equals(sno)){
                return true;
            }
        }
        return false;
    }

}
