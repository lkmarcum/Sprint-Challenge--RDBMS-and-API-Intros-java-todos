package com.lambdaschool.todos.controller;

import com.lambdaschool.todos.model.Todo;
import com.lambdaschool.todos.model.User;
import com.lambdaschool.todos.service.TodoService;
import com.lambdaschool.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    // GET localhost:2019/users/mine
    @GetMapping(value = "/mine", produces = {"application/json"})
    public ResponseEntity<?> getLoggedInUser(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
    }

    // POST localhost:2019/users
    @PostMapping(value = "", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody
                                                User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // POST localhost:2019/users/todo/{userid}
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewTodo(@PathVariable Long userid, @RequestBody
            Todo newTodo)
    {
        Todo tempTodo = new Todo(newTodo.getDescription(), newTodo.getDatestarted(), userService.findUserById(userid));
        todoService.save(tempTodo);
        return new ResponseEntity<>(tempTodo, HttpStatus.OK);
    }

    // DELETE localhost:2019/users/userid/{userid}
    // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable
                                                    long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}