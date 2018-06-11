package com.main.service.aService;

import com.main.entity.adept.AccountA;
import com.main.entity.adept.ClassA;
import com.main.entity.adept.StudentA;
import com.main.entity.cdept.AccountC;
import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StudentC;
import org.dom4j.Document;

import java.util.List;

public interface AService {
    /**
     * 获取所有课程信息
     * @return
     */
    List<ClassA> getAllClass();

    List<ClassA> getAllShareClass();

    boolean login(String account,String pwd);
    /**
     * 获得选某课的所有学生
     * @param classNo
     * @return
     */
    List<StudentA> getAllChooseStudents(String classNo);

    List<StudentA> getAllStudents();

    /**
     * 获得某学生所有课
     * @param studentNo
     * @return
     */
    List<ClassA> getAllChooseClass(String studentNo);
    AccountA findByAccount(String account);

    void chooseClass(String sno,String cno);
    void cancelClass(String sno,String cno);

    StudentA getStudentByAccunt(String account);

    String getSnoByAccount(String acc);
    boolean hasChoose(String sno,String cno);

    Document sendShareClass();
    Boolean chooseShareClass(String sno,String cno);
    List<ClassA> get_BC_Class();
    Boolean solveShareChoose(Document xmlChoice);
    Document sendShareChoice(String sno);
    boolean hasChooseShare(String sno,String cno);
    boolean cancelShareClass(String sno,String cno);
    boolean solveCancelShare(Document xmlCancel);

}
