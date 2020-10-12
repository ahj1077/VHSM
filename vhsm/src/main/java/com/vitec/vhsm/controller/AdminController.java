package com.vitec.vhsm.controller;

import com.sun.deploy.net.HttpResponse;
import com.vitec.vhsm.domain.Admin;
import com.vitec.vhsm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/newuser")
    public String newuser(Model model){
        model.addAttribute("user_id", getUserId());
        return "newuser";
    }

    @PostMapping("/adduser")
    public String adduser(@RequestParam("adminId") String id,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("name") String name,
                          @RequestParam("department") String department,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("email") String email,
                          @RequestParam("use") Boolean use,
                          @RequestParam("approval") String approval,
                          @RequestParam("registeredDate") String registeredDate) {

        if(password.equals(password2)){
            adminService.join(new Admin(id,password,name,department,mobile,email,use,approval,registeredDate));
            return "index";
        }
        else{
            return "errorpage";
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("user_id") String id,
                         @RequestParam("password") String password,
                         @RequestParam("password_check") String password_check,
                         @RequestParam("name") String name,
                         @RequestParam("department") String department,
                         @RequestParam("mobile") String mobile,
                         @RequestParam("email") String email,
                         @RequestParam("use") Boolean use){

        if(password.equals(password_check)) {
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String str_time = format1.format(time);
            String admin_id = getUserId();

            adminService.join(new Admin(id, password, name, department,mobile,email,use,name,str_time));
        }
        return "redirect:/index";
    }

    public String getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        return userDetails.getUsername();
    }
}
