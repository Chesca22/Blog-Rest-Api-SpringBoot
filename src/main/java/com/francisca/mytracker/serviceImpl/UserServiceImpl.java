package com.francisca.mytracker.serviceImpl;

import com.francisca.mytracker.dto.TaskDTO;
import com.francisca.mytracker.dto.UserDTO;
import com.francisca.mytracker.exception.TaskNotFoundException;
import com.francisca.mytracker.model.Task;
import com.francisca.mytracker.model.User;
import com.francisca.mytracker.repository.TaskRepository;
import com.francisca.mytracker.repository.UserRepository;
import com.francisca.mytracker.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public User registerUser(UserDTO userdto){
        User user = new User();
        user.setFullName(userdto.getFullName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
     return   userRepository.save(user);
    }

    @Override
   public String loginUser(String email, String password){
      String message = "";
      User user = getUserByEmail(email);
      if(user.getPassword().equals(password)){
          message = "success";
      }
      else{
          message= "incorrect password";
      }
      return message;
    }
    @Override
   public Task createdTask(TaskDTO taskDto){
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
       return taskRepository.save(task);
    }

    @Override
   public Task updateTitleAndDescription(TaskDTO taskDTO, int id){
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }
    @Override
   public Task getTaskById(int id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task not found"));

    }
    @Override
   public List<Task> viewAllTask(){
        return taskRepository.findAll();
    }
    @Override
   public boolean updateTaskStatus(String status, int id){
        return taskRepository.updateTaskByIdAndStatus(status, id);
    }

    @Override
    public List<Task> viewAllTaskByStatus(String status){
        return taskRepository.listOfTasksByStatus(status);
    }
    @Override
   public boolean deletedById(int id){
        taskRepository.deleteById(id);
        return true;
    }
    @Override
    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email).get();
    }


}
