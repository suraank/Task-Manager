package com.example.todo.entity;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
public class TaskEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String priority;
}
