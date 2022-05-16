package doha.java.dohanguyen_java.Controller;

import doha.java.dohanguyen_java.Model.AdminModel;
import doha.java.dohanguyen_java.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        session.removeAttribute("admincheck");
        AdminModel adminModel = new AdminModel();
        model.addAttribute("admin", adminModel);
        return "login";
    }

    @PostMapping("/checklogin")
    public String checklogin(@ModelAttribute("admin") AdminModel adminModel, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            String AdminEmail = adminModel.getEmail();
            AdminModel adminData = adminRepository.findByEmail(AdminEmail);
            if (adminModel.getPassword().equals(adminData.getPassword())) {
                session.setAttribute("admincheck", adminData.getName());
                return "redirect:/dashboard";
            } else {
                redirectAttributes.addFlashAttribute("canhbao", "tài khoản hoặc mật khẩu không chính xác");
                return "redirect:/login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("canhbao", "tài khoản hoặc mật khẩu không chính xác");
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admincheck") == null) {
            redirectAttributes.addFlashAttribute("canhbao", "Vui Lòng Đăng nhập");
            return "redirect:/login";
        } else {
            return "dashboard";
        }
    }


    @GetMapping("/Register")
    public String Register(Model model, HttpSession session) {
        session.removeAttribute("admincheck");
        model.addAttribute("admin", new AdminModel());
        return "Register";
    }

    @PostMapping("/admin_register")
    public String add_admin(AdminModel adminModel, RedirectAttributes redirectAttributes) {
        try {
            adminModel.setPassword(adminModel.getPassword());
            adminRepository.save(adminModel);
            redirectAttributes.addFlashAttribute("admintinnhan", "Đã lưu thông tin admin thành công");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("admintinnhan1", "Email bạn đăng kí đã có người sử dụng");
            return "redirect:/Register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admincheck");
        return "redirect:/login";
    }


}
