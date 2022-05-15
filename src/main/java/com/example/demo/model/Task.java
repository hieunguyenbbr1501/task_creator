package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tasks")
@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="task_sq")
    @SequenceGenerator(name = "task_sq", sequenceName = "task_sq", initialValue = 1, allocationSize=1)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "user_id")
    private int userId;
}
