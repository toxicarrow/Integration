package com.main.service.cService;

import com.main.dao.cdept.AccountCDao;
import com.main.dao.cdept.ClassCDao;
import com.main.dao.cdept.StClassCDao;
import com.main.dao.cdept.StudentCDao;
import com.main.entity.adept.ClassA;
import com.main.entity.cdept.AccountC;
import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StClassC;
import com.main.entity.cdept.StudentC;
import com.main.service.dService.DService;
import com.main.util.XMLHelper;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CServiceImpl implements CService{
    @Autowired
    private ClassCDao classCDao;
    @Autowired
    private StudentCDao studentCDao;
    @Autowired
    private StClassCDao stClassCDao;
    @Autowired
    private AccountCDao accountCDao;
    @Autowired
    private DService dService;

    private XMLHelper xmlHelper = new XMLHelper();

    @Override
    public AccountC findByAccount(String account) {
        return accountCDao.findOne(account);
    }
    @Override
    public List<ClassC> getAllClass() {
        return classCDao.findAll();
    }

    @Override
    public List<ClassC> getAllShareClass() {
        return classCDao.findAllByShare("y");
    }

    @Override
    public List<StudentC> getAllChooseStudents(String classNo) {
        List<StClassC> stClassList=stClassCDao.findAllByCno(classNo);
        List<StudentC> studentCList=new ArrayList<>();
        for(int i=0;i<stClassList.size();i++){
            studentCList.add(studentCDao.findOne(stClassList.get(i).getSno()));
        }
        return studentCList;
    }

    @Override
    public boolean hasChoose(String sno, String cno) {
        return stClassCDao.existsByCnoAndSno(cno,sno);
    }



    @Override
    public List<StudentC> getAllStudents() {
        return studentCDao.findAll();
    }

    @Override
    public List<ClassC> getAllChooseClass(String studentNo) {
        List<StClassC> stClassList=stClassCDao.findAllBySno(studentNo);
        List<ClassC> classList=new ArrayList<>();
        for(int i=0;i<stClassList.size();i++){
            classList.add(classCDao.findOne(stClassList.get(i).getCno()));
        }
        return classList;
    }


    @Override
    public boolean login(String account,String pwd) {
        StudentC studentC=studentCDao.findOne(account);
        if(studentC==null){
            return false;
        }
        if(studentC.getPwd().equals(pwd)){
            return true;
        }
        return false;
    }

    @Override
    public void chooseClass(String sno, String cno) {
        StClassC stClassC=new StClassC();
        stClassC.setCno(cno);
        stClassC.setSno(sno);
        stClassCDao.save(stClassC);
    }

    @Override
    @Transactional
    public void cancelClass(String sno, String cno) {
        stClassCDao.deleteByCnoAndSno(cno,sno);
    }

    @Override
    public Document sendShareClass(){
        List<ClassC> classCList = getAllShareClass();
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("classes");
        for(ClassC cc:classCList){
            Element emp = root.addElement("class");
            emp.addElement("Cno").setText(cc.getCno());
            emp.addElement("Cnm").setText(cc.getCnm());
            emp.addElement("Ctm").setText(cc.getCtm());
            emp.addElement("Cpt").setText(cc.getCpt());
            emp.addElement("Tec").setText(cc.getTec());
            emp.addElement("Pla").setText(cc.getPla());
        }
        return doc;
    }

    @Override
    public Boolean chooseShareClass(String sno,String cno){
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("choices");
        Element emp = root.addElement("choice");
        emp.addElement("Cno").setText(cno);
        emp.addElement("Sno").setText(sno);
//        emp.addElement("Grd").setText("0");
        if(cno.startsWith("a")){
            return dService.chooseShareClassFromA(doc);
        }
        else if(cno.startsWith("b")){
            return dService.chooseShareClassFromB(doc);
        }
        return false;
    }

    @Override
    public List<ClassC> get_AB_Class(){
        List<ClassC> shareClass = new ArrayList();
        List<Document> classXmls = dService.getShareClassForB();
        for(Document xml:classXmls){
            if(!xmlHelper.validateXml(xml,"cdept\\Cclass.xsd")){
                return null;
            }
            Element root = xml.getRootElement();
            for(Iterator i = root.elementIterator(); i.hasNext();){
                Element temp = (Element)i.next();
                ClassC classC = new ClassC();
                for(Iterator j = temp.elementIterator();j.hasNext();){
                    Element node = (Element) j.next();
                    String value = node.getName();
                    switch (value){
                        case "Cno":
                            classC.setCno(node.getText());
                            break;
                        case "Cnm":
                            classC.setCnm(node.getText());
                            break;
                        case "Ctm":
                            classC.setCtm(node.getText());
                        case "Cpt":
                            classC.setCpt(node.getText());
                            break;
                        case "Tec":
                            classC.setTec(node.getText());
                            break;
                        case "Pla":
                            classC.setPla(node.getText());
                            break;
                        default:
                            System.out.println("wrong xml "+xml.asXML());
                    }
                }
                shareClass.add(classC);
            }
        }
        return shareClass;
    }

    @Override
    public Boolean solveShareChoose(Document xmlChoice){
        if(!xmlHelper.validateXml(xmlChoice,"cdept\\Cchoice.xsd")){
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
                    case "Cno":
                        cno = node.getText();
                        break;
                    case "Sno":
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
        List<StClassC> stClassAList = stClassCDao.findAllBySno(sno);
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("choices");
        for(StClassC stClassC:stClassAList){
            Element emp = root.addElement("choice");
            emp.addElement("Cno").setText(stClassC.getCno());
            emp.addElement("Sno").setText(stClassC.getSno());
//            emp.addElement("成绩").setText(String.valueOf(stClassC.getGrd()));
        }
        return doc;
    }

    @Override
    public boolean hasChooseShare(String sno,String cno){
        List<StClassC> stClassCList = new ArrayList<>();
        List<Document> shareList = dService.getStnChoiceForA(sno);
        for(Document xml:shareList){
            if(!xmlHelper.validateXml(xml,"cdept\\Cchoice.xsd")){
                return false;
            }
            Element root = xml.getRootElement();
            for(Iterator i=root.elementIterator();i.hasNext();) {
                Element temp = (Element) i.next();
                StClassC stClassA = new StClassC();
                for (Iterator j = temp.elementIterator(); j.hasNext(); ) {
                    Element node = (Element) j.next();
                    String value = node.getName();
                    switch (value) {
                        case "Cno":
                            stClassA.setCno(node.getText());
                            break;
                        case "Sno":
                            stClassA.setSno(node.getText());
                            break;
//                        case "Grd":
//                            stClassA.setGrd(Integer.parseInt(node.getText()));
                        default:
                            System.out.println("wrong xml "+xml.asXML());
                    }
                }
                stClassCList.add(stClassA);
            }
        }

        for(int i =0;i<stClassCList.size();i++){
            if(stClassCList.get(i).getCno().equals(cno) && stClassCList.get(i).getSno().equals(sno)){
                return true;
            }
        }
        return false;
    }
}
