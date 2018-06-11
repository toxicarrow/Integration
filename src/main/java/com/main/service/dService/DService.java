package com.main.service.dService;

import com.main.entity.adept.ClassA;
import com.main.entity.cdept.ClassC;

import org.dom4j.Document;
import java.util.List;

/**
 * Created by lenovo on 2018/6/10.
 */
public interface DService {

    List<Document> getShareClassForA();

    List<Document> getShareClassForB();

    List<Document> getShareClassForC();

    boolean chooseShareClassFromA(Document chooseXml);

    boolean chooseShareClassFromB(Document chooseXml);

    boolean chooseShareClassFromC(Document chooseXml);

    List<Document> getStnChoiceForA(String sno);

    List<Document> getStnChoiceForB(String sno);

    List<Document> getStnChoiceForC(String sno);
}
