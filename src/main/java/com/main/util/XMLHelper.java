package com.main.util;

import org.dom4j.Document;
import org.dom4j.io.*;
import org.dom4j.util.XMLErrorHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileReader;

/**
 * Created by lenovo on 2018/6/10.
 */
public class XMLHelper {
    public Document transform(Document source,String stylesheet){
        try {
            SAXReader saxReader = new SAXReader();
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File("D:\\study\\jicheng\\Integration\\src\\main\\java\\com\\main\\service\\"+stylesheet)));
            DocumentSource Source = new DocumentSource(source);
            DocumentResult result = new DocumentResult();
            transformer.transform(Source, result);
            return result.getDocument();
            //System.out.println(result.getDocument().asXML());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean validateXml(Document source,String xsdFilename){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:" + "D:\\study\\jicheng\\Integration\\src\\main\\java\\com\\main\\entity\\"+xsdFilename);

            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            // 错误处理器，存放验证过程中的错误信息
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            validator.setErrorHandler(errorHandler);

            SAXReader saxReader = new SAXReader();
            Document xmlDocument = source;

            validator.validate(xmlDocument);

            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
            if (errorHandler.getErrors().hasContent()) {
                writer.write(errorHandler.getErrors());
                return false;
                //System.out.println("111"+errorHandler.getErrors()+" "+errorHandler.getErrorQName());
            } else {
                System.out.println("Success");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
