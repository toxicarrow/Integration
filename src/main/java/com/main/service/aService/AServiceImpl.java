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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            studentAList.add(studentADao.findOne(stClassList.get(i).getSno()));
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
            classAList.add(classADao.findOne(stClassList.get(i).getCno()));
        }
        return classAList;
    }

    @Override
    public AccountA findByAccount(String account) {
        return accountADao.findOne(account);
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
    public StudentA getStudentByAccount(String account) {
        return studentADao.findByAccount(account);
    }
}
