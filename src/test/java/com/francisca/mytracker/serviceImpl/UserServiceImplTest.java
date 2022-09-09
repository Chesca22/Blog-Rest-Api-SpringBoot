package com.francisca.mytracker.serviceImpl;

import com.francisca.mytracker.dto.TaskDTO;
import com.francisca.mytracker.dto.UserDTO;
import com.francisca.mytracker.model.Status;
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
    private UserServiceImpl userServiceImpl;

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
        this.task = new Task(1, "my task", "trying out new task", Status.PENDING, dateTime, dateTime, dateTime, user);
        this.taskDTO = new TaskDTO("to read", "read up for the task", 1);
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
            when(userServiceImpl.registerUser(userDTO)).thenReturn(user);
            var actual = userServiceImpl.registerUser(userDTO);
            var expected = user;
            assertEquals( expected , actual);
        }

        @Test
        void tologinUser_Successfull() {
            String message = "Success";
            assertEquals(message , userServiceImpl.loginUser("enwerevincent@gmail.com" , "12345"));
        }

        @Test
        void loginUser_Unsuccessfull() {
            String message = "incorrect password";
            assertEquals(message , userServiceImpl.loginUser("enwerevincent@gmail.com" , "1234"));
        }


        @Test
        void createTask() {
            when(userServiceImpl.createdTask(taskDTO)).thenReturn(task);
            assertEquals(task , userServiceImpl.createdTask(taskDTO));
        }

        @Test
        void updateTitleAndDescription() {
            assertEquals(task , userServiceImpl.updateTitleAndDescription(taskDTO , 1));
        }

        @Test
        void viewAllTasks() {
            assertEquals(1 , userServiceImpl.viewAllTask().size());
        }

        @Test
        void viewAllTaskByStatus() {

            assertEquals(taskList , userServiceImpl.viewAllTaskByStatus("pending", 1));
        }

//    @Test
//    void deleteById() {
//        when(userServiceImpl.deleteById(1)).thenReturn(true);
//        assertTrue(userServiceImpl.deleteById(1));
//    }

        @Test
        void updateTaskStatus() {
            assertTrue(userServiceImpl.updateTaskStatus("ongoing" , 1));
        }

        @Test
        void getUserByEmail() {
            assertEquals(user , userServiceImpl.getUserByEmail("enwerevincent@gmail.com"));
        }

        @Test
        void getTaskById() {
            assertEquals(task, userServiceImpl.getTaskById(1));
        }
    }
