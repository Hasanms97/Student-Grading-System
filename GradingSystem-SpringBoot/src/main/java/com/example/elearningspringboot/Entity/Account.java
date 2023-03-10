package com.example.elearningspringboot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    int account_id;
    @Column(name = "student_id")
    int student_id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;

}
