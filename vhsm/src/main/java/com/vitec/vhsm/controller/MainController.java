package com.vitec.vhsm.controller;

import com.vitec.vhsm.domain.CmdProcessingRateItem;
import com.vitec.vhsm.domain.Hsmlog;
import com.vitec.vhsm.service.AdminService;
import com.vitec.vhsm.service.HsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final HsmService hsmService;

    @Autowired
    public MainController(HsmService hsmService) {
        this.hsmService = hsmService;
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Hsmlog> list = hsmService.findCmdProcessingRate();
        model.addAttribute("user_id", getUserId());
        model.addAttribute("data", list);
        return "index";
    }


    @GetMapping("/systemlog")
    public String log(Model model){
        model.addAttribute("user_id", getUserId());
        return "systemlog";
    }

    @GetMapping("/infouser")
    public String infouser(Model model){
        model.addAttribute("user_id", getUserId());
        return "infouser";
    }

    @GetMapping("/infohsm")
    public String infohsm(Model model){
        model.addAttribute("user_id", getUserId());
        return "infohsm";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/newequip")
    public String newequip(){
        return "newequip";
    }


    public String getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        return userDetails.getUsername();
    }

    /*

    // Don't erase
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    */

}
