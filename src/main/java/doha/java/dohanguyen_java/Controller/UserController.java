package doha.java.dohanguyen_java.Controller;

import doha.java.dohanguyen_java.Model.UserModel;
import doha.java.dohanguyen_java.Service.IdNotFound;
import doha.java.dohanguyen_java.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String User(Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admincheck") == null) {
            redirectAttributes.addFlashAttribute("canhbao","Vui Lòng Đăng nhập");
            return "redirect:/login";
        } else {
            List<UserModel> userModels = userService.listall();
            model.addAttribute("users", userModels);
            return "User";
        }
    }

    @GetMapping("/adduser")
    public String adduser(Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admincheck") == null) {
            redirectAttributes.addFlashAttribute("canhbao","Vui Lòng Đăng nhập");
            return "redirect:/login";
        } else {
            model.addAttribute("user", new UserModel());
            return "AddUser";
        }
    }


    @PostMapping("/user/save")
    public String saveuser(UserModel userModel, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("admincheck") == null) {
            redirectAttributes.addFlashAttribute("canhbao","Vui Lòng Đăng nhập");
            return "redirect:/login";
        } else {
            userService.saveUser(userModel);
            redirectAttributes.addFlashAttribute("tinnhan", "Đã Lưu thông tin nhân viên thành công");
            return "redirect:/user";
        }
    }

    @GetMapping("/user/edit/{id}")
    public String edituser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("admincheck") == null) {
            redirectAttributes.addFlashAttribute("canhbao","Vui Lòng Đăng nhập");
            return "redirect:/login";
        } else {
            try {
                UserModel userModel = userService.Getid(id);
                model.addAttribute("user1", userModel);
                return "EditUser";
            } catch (IdNotFound e) {
                redirectAttributes.addFlashAttribute("tinnhan1", e.getMessage());
                return "redirect:/user";
            }
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteuser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("admincheck") == null) {
            redirectAttributes.addFlashAttribute("canhbao","Vui Lòng Đăng nhập");
            return "redirect:/login";
        } else {
            try {
                userService.DeleteuserId(id);
                redirectAttributes.addFlashAttribute("tinnhan3", "Đã xóa User Thành Công");
            } catch (IdNotFound e) {
                redirectAttributes.addFlashAttribute("tinnhan2", e.getMessage());
            }
            return "redirect:/user";
        }
    }
}
