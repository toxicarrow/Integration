package com.main.controller.cController;

import com.main.entity.adept.ClassA;
import com.main.entity.cdept.ClassC;
import com.main.service.aService.AService;
import com.main.service.cService.CService;
import com.main.vo.adept.AClassVo;
import com.main.vo.cdept.CClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cdept")
public class cController {
    @Autowired
    private CService cService;

    @RequestMapping("/login")
    public String login(Model model){
        return "/loginCdept";
    }

    @ResponseBody
    @RequestMapping("/loginPost")
    public String loginPost(@RequestParam String account, @RequestParam String password, HttpServletRequest request){
        if(cService.login(account,password)){
            request.getSession().setAttribute("adept",account);
            return "true";
        }
        return "false";
    }
    @ResponseBody
    @RequestMapping("/choose")
    public String chooseClass(@RequestParam String cno,HttpServletRequest request){
        String sno=(String) request.getSession().getAttribute("adept");
        cService.chooseClass(sno,cno);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/cancel")
    public String cancelClass(@RequestParam String cno,HttpServletRequest request){
        String sno=(String) request.getSession().getAttribute("adept");
        cService.cancelClass(sno,cno);
        return "success";
    }
    @RequestMapping("/home")
    public String loginPost(HttpServletRequest request,Model model){
        String sno=(String) request.getSession().getAttribute("adept");
        List<ClassC> classList=cService.getAllClass();
        List<CClassVo> classVoList=new ArrayList<>();
        for(int i=0;i<classList.size();i++){
            ClassC tclass=classList.get(i);
            CClassVo classVo=new CClassVo();
            classVo.setCnm(tclass.getCnm());
            classVo.setCno(tclass.getCno());
            classVo.setCpt(tclass.getCpt());
            classVo.setPla(tclass.getPla());
            classVo.setTec(tclass.getTec());
            classVo.setCtm(tclass.getCtm());
            if(tclass.getShare().equals("y")){
                classVo.setShare("是");
            }
            else {
                classVo.setShare("否");
            }
            classVo.setHasChoose(cService.hasChoose(sno,tclass.getCno()));
            classVoList.add(classVo);
        }
        model.addAttribute("classes",classVoList);
        return "/homeCdept";
    }
}
