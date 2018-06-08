package com.main.service.cService;

import com.main.entity.cdept.ClassC;

import java.util.List;

public interface CdeptClassService {
    /**
     * 分页获取所有课程信息
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return
     */
    List<ClassC> getAllClass(int pageNum,int pageSize);
}
