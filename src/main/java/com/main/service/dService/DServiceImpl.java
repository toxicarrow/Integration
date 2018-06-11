package com.main.service.dService;

import com.main.service.aService.AService;
//import com.main.service.bService.BLessonService;
import com.main.service.cService.CService;
import com.main.util.XMLHelper;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/6/10.
 */
@Service
public class DServiceImpl implements DService{
    @Autowired
    private AService aService;
//    @Autowired
//    private BLessonService bLessonService;
    @Autowired
    private CService cService;

    private XMLHelper xmlHelper = new XMLHelper();

    @Override
    public List<Document> getShareClassForA() {
        Document xmlC = xmlHelper.transform(cService.sendShareClass(),"dService\\formatClass.xsl");
        List<Document> shareClass = new ArrayList<>();
        xmlC = xmlHelper.transform(xmlC,"dService\\Aclass.xsl");
        //Document xmlB = xmlHelper.transform(bLessonService.sendShareClass(),"dService\\formatClass.xsl");
        //xmlB = xmlHelper.transform(xmlB,"dService\\Aclass.xsl");
        shareClass.add(xmlC);
        //shareClass.add(xmlB);
        return shareClass;
    }

    @Override
    public List<Document> getShareClassForB(){
        Document xmlA = xmlHelper.transform(aService.sendShareClass(),"dService\\formatClass.xsl");
        List<Document> shareClass = new ArrayList<>();
        xmlA = xmlHelper.transform(xmlA,"dService\\Bclass.xsl");
        shareClass.add(xmlA);
        Document xmlC = xmlHelper.transform(cService.sendShareClass(),"dService\\formatClass.xsl");
        xmlC = xmlHelper.transform(xmlC,"dService\\Bclass.xsl");
        shareClass.add(xmlC);
        return shareClass;
    }

    @Override
    public List<Document> getShareClassForC() {
        Document xmlA = xmlHelper.transform(aService.sendShareClass(),"dService\\formatClass.xsl");
        List<Document> shareClass = new ArrayList<>();
        xmlA = xmlHelper.transform(xmlA,"dService\\Cclass.xsl");
        //Document xmlB = xmlHelper.transform(bLessonService.sendShareClass(),"dService\\formatClass.xsl");
        //xmlB = xmlHelper.transform(xmlB,"dService\\Cclass.xsl");
        //System.out.println(xmlA.asXML());
        shareClass.add(xmlA);
        //shareClass.add(xmlB);
        return shareClass;
    }

    @Override
    public boolean chooseShareClassFromA(Document chooseXml) {
        Document docSource = xmlHelper.transform(chooseXml,"dService\\formatChoice.xsl");
        Document docResult = xmlHelper.transform(docSource,"dService\\Achoice.xsl");
        return aService.solveShareChoose(docResult);
    }

    @Override
    public boolean chooseShareClassFromB(Document chooseXml){
        //Document docSource = xmlHelper.transform(chooseXml,"dService\\formatChoice.xsl");
        //Document docResult = xmlHelper.transform(docSource,"dService\\Bchoice.xsl");
        //return bLessonService.solveShareChoose(docResult);
        return false;
    }

    @Override
    public boolean chooseShareClassFromC(Document chooseXml) {
        Document docSource = xmlHelper.transform(chooseXml,"dService\\formatChoice.xsl");
        Document docResult = xmlHelper.transform(docSource,"dService\\Cchoice.xsl");
        return cService.solveShareChoose(docResult);
    }

    @Override
    public List<Document> getStnChoiceForA(String sno){
        List<Document> StnChoice = new ArrayList<>();
        Document xmlc = xmlHelper.transform(cService.sendShareChoice(sno),"dService\\formatChoice.xsl");
        xmlc = xmlHelper.transform(xmlc,"dService\\Achoice.xsl");
        StnChoice.add(xmlc);
        return StnChoice;
    }

    @Override
    public List<Document> getStnChoiceForB(String sno){
        return null;
    }

    @Override
    public List<Document> getStnChoiceForC(String sno){
        List<Document> StnChoice = new ArrayList<>();
        Document xmla = xmlHelper.transform(aService.sendShareChoice(sno),"dService\\formatChoice.xsl");
        xmla = xmlHelper.transform(xmla,"dService\\Cchoice.xsl");
        StnChoice.add(xmla);
        return StnChoice;
    }

    @Override
    public boolean cancelShareClassFromA(Document xmlCancel){
        Document docSource = xmlHelper.transform(xmlCancel,"dService\\formatChoice.xsl");
        Document docResult = xmlHelper.transform(docSource,"dService\\Achoice.xsl");

        return aService.solveCancelShare(docResult);
    }

    @Override
    public boolean cancelShareClassFromB(Document xmlCancel){
        return false;
    }

    @Override
    public boolean cancelShareClassFromC(Document xmlCancel){
        Document docSource = xmlHelper.transform(xmlCancel,"dService\\formatChoice.xsl");
        Document docResult = xmlHelper.transform(docSource,"dService\\Cchoice.xsl");

        return cService.solveCancelShare(docResult);
    }
}
