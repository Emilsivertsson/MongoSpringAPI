package com.CodeForPizza.demo.repository;

import com.CodeForPizza.demo.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

// This is the repository for the Expense model and it extends MongoRepository for CRUD operations
public interface ExpenseRepository extends MongoRepository<Expense, String> {

    // This is a custom query to find by name using a placeholder
    @Query("{'name': ?0}")
    Optional <Expense> findByName(String name);
}
