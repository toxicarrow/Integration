package com.main.service.cService;

import com.main.dao.cdept.AccountCDao;
import com.main.dao.cdept.ClassCDao;
import com.main.dao.cdept.StClassCDao;
import com.main.dao.cdept.StudentCDao;
import com.main.entity.cdept.AccountC;
import com.main.entity.cdept.ClassC;
import com.main.entity.cdept.StClassC;
import com.main.entity.cdept.StudentC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Override
    public AccountC findByAccount(String account) {
        return accountCDao.findOne(account);
    }
    @Override
    public List<ClassC> getAllClass() {
        return classCDao.findAll();
    }

    @Override
    public List<StudentC> getAllChooseStudents(String classNo) {
        List<StClassC> stClassList=stClassCDao.findAllByCno(classNo);
        List<StudentC> studentCList=new ArrayList<>();
        for(int i=0;i<stClassList.size();i++){
            studentCList.add(studentCDao.getOne(stClassList.get(i).getSno()));
        }
        return studentCList;
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
            classList.add(classCDao.getOne(stClassList.get(i).getCno()));
        }
        return classList;
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
}
