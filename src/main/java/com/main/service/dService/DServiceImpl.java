package com.main.service.dService;

import com.main.service.aService.AService;
import com.main.service.bService.BLessonService;
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
    @Autowired
    private BLessonService bLessonService;
    @Autowired
    private CService cService;

    private XMLHelper xmlHelper = new XMLHelper();

    @Override
    public List<Document> getShareClassForA() {
        Document xmlC = xmlHelper.transform(cService.sendShareClass(),"dService\\formatClass.xsl");
        List<Document> shareClass = new ArrayList<>();
        xmlC = xmlHelper.transform(xmlC,"dService\\Aclass.xsl");
        Document xmlB = xmlHelper.transform(bLessonService.sendShareClass(),"dService\\formatClass.xsl");
        xmlB = xmlHelper.transform(xmlB,"dService\\Aclass.xsl");
        shareClass.add(xmlC);
        shareClass.add(xmlB);
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
        Document xmlB = xmlHelper.transform(bLessonService.sendShareClass(),"dService\\formatClass.xsl");
        xmlB = xmlHelper.transform(xmlB,"dService\\Cclass.xsl");
        shareClass.add(xmlA);
        shareClass.add(xmlB);
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
        Document docSource = xmlHelper.transform(chooseXml,"dService\\formatChoice.xsl");
        Document docResult = xmlHelper.transform(docSource,"dService\\Bchoice.xsl");
        return bLessonService.solveShareChoose(docResult);
    }

    @Override
    public boolean chooseShareClassFromC(Document chooseXml) {
        Document docSource = xmlHelper.transform(chooseXml,"dService\\formatChoice.xsl");
        Document docResult = xmlHelper.transform(docSource,"dService\\Cchoice.xsl");
        return cService.solveShareChoose(docResult);
    }
}
