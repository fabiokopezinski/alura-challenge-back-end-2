package br.com.alura.control.financeiro.infrastructure.gateway;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.infrastructure.repository.ExpenseRepository;
import br.com.alura.control.financeiro.validations.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class ExpenseGateway implements IExpense {

    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> findAllExpense(int limit, int offset) {

        log.info("offset={} limit={}", offset, limit);

        return this.expenseRepository.findAll(PageRequest.of(offset, limit));
    }

    @Override
    public Expense findById(Long id) {

        return findByExpense(id);
    }

    @Override
    public Expense saveExpense(Expense expense) {

        log.info("expense={}", expense.toString());

        this.expenseRepository.findByDescriptionAndValue(expense.getDescription(), expense.getValue()).ifPresent(p -> {
            throw Message.IS_PRESENT_EXPENSE.asBusinessException();
        });

        return expenseRepository.save(expense);
    }

    @Override
    public Expense update(Long id, Expense expense) {

        log.info("id={} expense={}", id, expense);

        return expense;
    }

    @Override
    public void delete(Long id) {

        findByExpense(id);

        this.expenseRepository.deleteById(id);

    }

    private Expense findByExpense(Long id) {

        log.info("id={}", id);

        return this.expenseRepository.findById(id).orElseThrow(Message.NOT_FOUND_EXPENSE::asBusinessException);
    }

    @Override
    public List<Expense> findByDescription(String expense) {

        log.info("description={}", expense);

        return this.expenseRepository.findByDescription(expense);
    }

    @Override
    public List<Expense> findByData(String data) {

        log.info("data={}", data);

        return this.expenseRepository.findByData(data);
    }

    @Override
    public List<Expense> findAll() {
       
        return this.expenseRepository.findAll();
    }
}
