package br.com.alura.control.financeiro.entrypoint;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseDeleteUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseFindAllUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseFindByDataUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseFindByDescriptionUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseFindByIdUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseSaveUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseUpdateUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/despesas")
public class ExpenseController {

    private ExpenseFindAllUseCase expenseFindAllUseCase;

    private ExpenseFindByIdUseCase expenseFindByIdUseCase;

    private ExpenseDeleteUseCase expenseDeleteUseCase;

    private ExpenseSaveUseCase expenseSaveUseCase;

    private ExpenseUpdateUseCase expenseUpdateUseCase;

    private ExpenseFindByDescriptionUseCase expenseFindByDescriptionUseCase;

    private ExpenseFindByDataUseCase findByDataUseCase;

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<ExpenseResponse>> findByData(@PathVariable("year") String year,@PathVariable("month") String month){
        return ResponseEntity.ok(findByDataUseCase.findByDate(month, year));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "descricao", required = false) String descricao) {
        return  descricao == null ? ResponseEntity.ok().body(expenseFindAllUseCase.findAll(limit, page))
                : ResponseEntity.ok().body(expenseFindByDescriptionUseCase.findByDescrExpense(descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(expenseFindByIdUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> save(@RequestBody ExpenseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseSaveUseCase.expenseSave(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable("id") Long id,
            @RequestBody ExpenseRequest expenseRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(expenseUpdateUseCase.expenseUpdate(id, expenseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        expenseDeleteUseCase.expenseDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
