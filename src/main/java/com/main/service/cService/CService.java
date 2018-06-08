package com.main.service.cService;

import com.main.entity.cdept.AccountC;
import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StudentC;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CService {
    /**
     * 分页获取所有课程信息
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return
     */
    Page<ClassC> getAllClass(int pageNum, int pageSize);


    /**
     * 获得选某课的所有学生
     * @param classNo
     * @return
     */
    List<StudentC> getAllChooseStudents(String classNo);

    Page<StudentC> getAllStudents(int pageNum, int pageSize);

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
