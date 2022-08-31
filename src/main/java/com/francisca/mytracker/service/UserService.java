package com.francisca.mytracker.service;

import com.francisca.mytracker.dto.TaskDTO;
import com.francisca.mytracker.dto.UserDTO;
import com.francisca.mytracker.model.Task;
import com.francisca.mytracker.model.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService {
    User registerUser(UserDTO userdto);
    String loginUser(String email, String password);
    Task createdTask(TaskDTO taskDto);
    Task updateTitleAndDescription(TaskDTO taskDTO, int id);
    Task getTaskById(int id);
    List<Task> viewAllTask();
    boolean updateTaskStatus(String status, int id);
    List<Task> viewAllTaskByStatus(String status);
    boolean deletedById(int id);
    User getUserByEmail(String email);




}
