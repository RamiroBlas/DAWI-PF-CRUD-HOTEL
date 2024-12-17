package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDetailDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.service.ManageService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    ManageService manageService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/restricted")
    public String restricted(Model model) {
        return "restricted";
    }

    @GetMapping("/start")
    public String start(Model model) {

        try {
            List<UserDto> users = manageService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("error", null);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos");

        }
        return "manage";
    }

    @GetMapping("/userDetail/{id}")
    public String userDetail(@PathVariable Integer id, Model model) {

        try {
            UserDetailDto userDetail = manageService.getUserById(id);
            model.addAttribute("user", userDetail);
            model.addAttribute("error", null);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos");

        }
        return "manage-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {

        try {
            UserDto userDto = manageService.getAllUsersById(id);
            model.addAttribute("user", userDto);
            model.addAttribute("error", null);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos");
        }

        return "manage-update";
    }

    @PostMapping("/update-confirm")
    public String updateConfirm(@ModelAttribute UserDto user, Model model) {
        try {
            boolean success = manageService.updateUser(user);
            if (success) {
                return "redirect:/manage/start";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al actualizar los datos");
        }
        return "redirect:/manage/start";
    }

    @GetMapping("/add")
    public String add(Model model) {
        UserDetailDto userDetailDto = new UserDetailDto(0, "", "", "", "", "", new Date(), new Date());
        model.addAttribute("userDetail", userDetailDto);
        return "manage-add";
    }

    @PostMapping("/add-confirm")
    public String addConfirm(@ModelAttribute UserDetailDto userDetail, @RequestParam("password") String password,
                             Model model) {
        try {
            boolean success = manageService.addUser(userDetail, password);
            if (success) {
                return "redirect:/manage/start";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al agregar los usuario");
        }
        return "manage-add";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        try {
            boolean success = manageService.deleteUserById(id);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "Usuario eliminado con éxito.");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar el usuario.");
        }
        return "redirect:/manage/start";
    }

}
