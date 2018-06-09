package com.main.service.cService;

import com.main.entity.cdept.AccountC;
import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StudentC;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CService {
    /**
     * 获取所有课程信息
     * @return
     */
    List<ClassC> getAllClass();

    List<ClassC> getAllShareClass();

    /**
     * 获得选某课的所有学生
     * @param classNo
     * @return
     */
    List<StudentC> getAllChooseStudents(String classNo);

    List<StudentC> getAllStudents();

    /**
     * 获得某学生所有课
     * @param studentNo
     * @return
     */
    List<ClassC> getAllChooseClass(String studentNo);
    AccountC findByAccount(String account);

    void chooseClass(String sno,String cno);
    void cancelClass(String sno,String cno);
}
