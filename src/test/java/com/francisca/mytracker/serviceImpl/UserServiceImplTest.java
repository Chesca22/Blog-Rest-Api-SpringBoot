package com.francisca.mytracker.serviceImpl;

import com.francisca.mytracker.dto.TaskDTO;
import com.francisca.mytracker.dto.UserDTO;
import com.francisca.mytracker.model.Task;
import com.francisca.mytracker.model.User;
import com.francisca.mytracker.repository.TaskRepository;
import com.francisca.mytracker.repository.UserRepository;
import com.francisca.mytracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Task task;
    private User user;
    private TaskDTO taskDTO;
    private UserDTO userDTO;
    private LocalDateTime dateTime;
    List<Task> taskList;

    @BeforeEach
    void setUp() {
        dateTime = LocalDateTime.of(2022, AUGUST,3,6,30,40,50000);
        taskList = new ArrayList<>();
        taskList.add(task);

        this.user= new User(1, "may june", "june@gmail.com", "1234",taskList);
        this.task = new Task(1, "my task", "trying out new task", "pending", dateTime, dateTime, dateTime, user);
        this.taskDTO = new TaskDTO("my task", "trying out new task");
        this.userDTO = new UserDTO("may june", "june@gmail.com", "1234");
        when(userRepository.save(user)).thenReturn(user);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        when(taskRepository.listOfTasksByStatus("pending")).thenReturn(taskList);
        when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(task));
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(taskRepository.updateTaskByIdAndStatus("in progress", 1)).thenReturn(true);
        when(userRepository.findUserByEmail("june@gmail.com")).thenReturn(Optional.of(user));

    }

    @Test
    void registerUser() {

    }

    @Test
    void loginUser() {
    }

    @Test
    void createdTask() {
    }

    @Test
    void updateTitleAndDescription() {
    }

    @Test
    void getTaskById() {
    }

    @Test
    void viewAllTask() {
    }

    @Test
    void updateTaskStatus() {
    }

    @Test
    void viewAllTaskByStatus() {
    }

    @Test
    void deletedById() {
    }

    @Test
    void getUserByEmail() {
    }
}