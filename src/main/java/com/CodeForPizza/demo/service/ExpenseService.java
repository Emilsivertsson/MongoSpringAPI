package com.CodeForPizza.demo.service;

import com.CodeForPizza.demo.model.Expense;
import com.CodeForPizza.demo.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    // This is the service for the Expense model and it uses the ExpenseRepository for CRUD operations
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    public void addExpense(Expense expense) {
        expenseRepository.insert(expense);
    }

    //takes a Expense object, gets it by id, and updates it, then saves it back to the database
    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", expense.getId())));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(savedExpense);
    }

    public List<Expense> getAllExpenses() {
       return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseByName(String name) {
       return expenseRepository.findByName(name);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);

    }
}
