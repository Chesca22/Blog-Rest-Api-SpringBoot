package com.francisca.mytracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

   @OneToMany(mappedBy = "user")
   private List<Task> tasks;


}
