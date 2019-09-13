package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.model.Todo;
// import com.lambdaschool.oauth2.views.CountQuotes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
   //  @Query(value = "SELECT u.username as username, COUNT(*) as countquotes FROM quotes q join users u ON q.userid = u.userid GROUP BY u.username", nativeQuery = true)
    // ArrayList<CountQuotes> getCountQuotes();
}