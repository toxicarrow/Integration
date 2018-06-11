package com.main.controller.aController;

import com.main.entity.adept.ClassA;
import com.main.service.aService.AService;
import com.main.service.dService.DService;
import com.main.vo.adept.AClassVo;
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
@RequestMapping("/adept")
public class aController {
    @Autowired
    private AService aService;

    @RequestMapping("/login")
    public String login(Model model){
        return "/login";
    }

    @ResponseBody
    @RequestMapping("/loginPost")
    public String loginPost(@RequestParam String account,@RequestParam String password, HttpServletRequest request){
        if(aService.login(account,password)){
            request.getSession().setAttribute("adept",aService.getSnoByAccount(account));
            return "true";
        }
        return "false";
    }
    @ResponseBody
    @RequestMapping("/choose")
    public String chooseClass(@RequestParam String cno,HttpServletRequest request){
        String sno=(String) request.getSession().getAttribute("adept");
        aService.chooseClass(sno,cno);
        return "success";
    }
    @ResponseBody
    @RequestMapping("/chooseShare")
    public String chooseShareClass(@RequestParam String cno,HttpServletRequest request){
        String sno=(String) request.getSession().getAttribute("adept");
        aService.chooseShareClass(sno,cno);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/cancel")
    public String cancelClass(@RequestParam String cno,HttpServletRequest request){
        String sno=(String) request.getSession().getAttribute("adept");
        aService.cancelClass(sno,cno);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/cancelShare")
    public String cancelShareClass(@RequestParam String cno,HttpServletRequest request){
        String sno=(String) request.getSession().getAttribute("adept");
        aService.chooseShareClass(sno,cno);
        return "success";
    }
    @RequestMapping("/home")
    public String loginPost(HttpServletRequest request,Model model){
        String sno=(String) request.getSession().getAttribute("adept");
        List<ClassA> classList=aService.getAllClass();
        List<AClassVo> classVoList=new ArrayList<>();
        for(int i=0;i<classList.size();i++){
            ClassA tclass=classList.get(i);
            AClassVo classVo=new AClassVo();
            classVo.setCnm(tclass.getCnm());
            classVo.setCno(tclass.getCno());
            classVo.setCpt(tclass.getCpt());
            classVo.setPla(tclass.getPla());
            classVo.setTec(tclass.getTec());
            if(tclass.getShare().equals("y")){
                classVo.setShare("是");
            }
            else {
                classVo.setShare("否");
            }
            classVo.setHasChoose(aService.hasChoose(sno,tclass.getCno()));
            classVoList.add(classVo);
        }

        List<ClassA> otherShareClasses=aService.get_BC_Class();
        List<AClassVo> classVoList2=new ArrayList<>();
        for(int i=0;i<otherShareClasses.size();i++){
            ClassA tclass=otherShareClasses.get(i);
            AClassVo classVo=new AClassVo();
            classVo.setCnm(tclass.getCnm());
            classVo.setCno(tclass.getCno());
            classVo.setCpt(tclass.getCpt());
            classVo.setPla(tclass.getPla());
            classVo.setTec(tclass.getTec());
            classVo.setHasChoose(aService.hasChooseShare(sno,tclass.getCno()));
            classVoList2.add(classVo);
        }
        model.addAttribute("classes",classVoList);
        model.addAttribute("otherclasses",classVoList2);
        return "/home";
    }
}
