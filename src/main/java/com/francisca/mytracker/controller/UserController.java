package com.francisca.mytracker.controller;

import com.francisca.mytracker.dto.TaskDTO;
import com.francisca.mytracker.dto.UserDTO;
import com.francisca.mytracker.model.Task;
import com.francisca.mytracker.model.User;
import com.francisca.mytracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public String index(Model model){
        List<Task> allTasks = userService.viewAllTask();
        model.addAttribute("tasks", allTasks);
        return "dashboard";
    }


    @GetMapping(value = "/login")
    public String displayLoginPage(Model model){
        model.addAttribute("userDetails", new UserDTO());
        return "login";
    }

    @PostMapping(value = "loginUser")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        String message = userService.loginUser(email, password);
        if(message.equals("success")){
            User user = userService.getUserByEmail(email);
            session.setAttribute("email,", user.getEmail());
            session.setAttribute("id", user.getId());
            session.setAttribute("fullname", user.getFullName());
            return "redirect:/dashboard";
        }
        else{
            model.addAttribute("errorMessage", message);
            return "redirect:/login";
        }

    }
    @GetMapping(value = "/register")

    public String showRegistrationForm(Model model){
        model.addAttribute("userRegistrationDetails", new UserDTO());
        return "register";
    }

    @PostMapping(value = "/userRegistration")
    public String registration(@ModelAttribute UserDTO userDTO){
        User registeredUser = userService.registerUser(userDTO);
        if(registeredUser != null){
            return "redirect:/login";
        }
        else{
            return "redirect:/register";
        }
    }

    @GetMapping(value = "/task/{status}")
    public String taskByStatus(@PathVariable(name = "status") String status, Model model){
        List<Task> listOfTaskByStatus = userService.viewAllTaskByStatus(status);
        model.addAttribute("tasksByStatus", listOfTaskByStatus);
        return "task_by_status";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") Integer id){
        userService.deletedById(id);
        return "redirect:/dashboard";
    }

    @GetMapping(value="/editPage/{id}")
    public String showEditPage(@PathVariable(name ="id") Integer id, Model model){
    Task task = userService.getTaskById(id);
    model.addAttribute("singleTask", task);
    model.addAttribute("taskBody", new TaskDTO());
    return "editTask";
    }

    @PostMapping(value="/edit/{id}")
    public String editTask(@PathVariable(name = "id") Integer id, @ModelAttribute TaskDTO dto){
        userService.updateTitleAndDescription(dto, id);
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/addNewTask")
    public String addTask(Model model){
        model.addAttribute("ewTask", new TaskDTO());
        return "addTask";
    }

        @PostMapping(value="/addTask")
                public String createTask(@ModelAttribute TaskDTO dto){
            userService.createdTask(dto);
            return "redirect:/dashboard";
        }




}
