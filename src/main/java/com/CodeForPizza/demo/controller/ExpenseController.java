package com.CodeForPizza.demo.controller;

import com.CodeForPizza.demo.model.Expense;
import com.CodeForPizza.demo.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//denfines that this is a controller for the API and the base path is /api/expense
@RestController
@RequestMapping("api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /*
    POST http://localhost:8080/api/expense
    Content-Type: application/json

    {
     "expenseName": "netflix",
     "expenseCategory": "ENTERTAINMENT",
    "expenseAmount": 10
    }
     */
    @PostMapping
    public ResponseEntity<Void> addExpense(@RequestBody Expense expense) { //RequestBody is the body of the request
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //returns a 201 status code if successful
    }

    /*
    PUT http://localhost:8080/api/expense
    Content-Type: application/json

    {
     "id": "64b857d58c10d04774541db4",
    "expenseName": "netflix",
    "expenseCategory": "ENTERTAINMENT",
    "expenseAmount": 20
    }
     */
    @PutMapping
    public ResponseEntity<Void> updateExpense(@RequestBody Expense expense) {
        expenseService.updateExpense(expense);
        return ResponseEntity.status(HttpStatus.OK).build(); //returns a 200 status code if successful
    }


    /*
    GET http://localhost:8080/api/expense
     */
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    /*
    GET http://localhost:8080/api/expense/<expense-name>
     */
    @GetMapping("/{name}") //defines the path parameter
    public ResponseEntity<Expense> getExpenseByName(@PathVariable String name) { //PathVariable is the path parameter
        Optional<Expense> expense = expenseService.getExpenseByName(name);
        return expense.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); //returns a 200 status code if successful
    }

    /*
    DELETE http://localhost:8080/api/expense/<MongoDBID>
     */
    @DeleteMapping("/{id}")  //defines the path parameter
    public ResponseEntity<Void> deleteExpense(@PathVariable String id) { //PathVariable is the path parameter
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //returns a 204 status code if successful
    }
}
