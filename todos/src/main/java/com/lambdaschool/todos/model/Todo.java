package com.lambdaschool.todos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties({"todos", "hibernateLazyInitializer"})
    private User user;

    private boolean completed;

    private Date datestarted;

    public Todo()
    {
        this.completed = false;
    }

    public Todo(String todo, Date datestarted, User user)
    {
        this.todo = todo;
        this.user = user;
        this.completed = false;
        this.datestarted = datestarted;
    }

    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getTodo()
    {
        return todo;
    }

    public void setTodo(String todo)
    {
        this.todo = todo;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}