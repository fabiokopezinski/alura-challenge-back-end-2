package br.com.alura.control.financeiro.core.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.response.SummaryCategoryEnumResponse;
import br.com.alura.control.financeiro.core.response.SummaryResponse;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.core.usecase.revenue.IRevenue;
import br.com.alura.control.financeiro.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SummaryUseCase {
 
    private IExpense iExpense;

    private IRevenue iRevenue;

    public SummaryResponse summary(String month,String year){

        List<Expense> expenses=iExpense.findAll();

        List<Revenue> revenues=iRevenue.findAll();
        
        Double sumFood = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Alimentacao).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumHealth = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Saude).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumHome = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Moradia).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumTransport = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Transporte).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumEducation = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Educacao).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumLaze = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Lazer).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumUnforeseen  = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Imprevistos).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumOther = expenses.stream().filter(c->c.getCategory()==CategoryEnum.Outras).mapToDouble(p->p.getValue().doubleValue()).sum();
        
        Double sumExpense = expenses.isEmpty() ? 0: iExpense.findByData(month.concat("-"+year)).stream().mapToDouble(p->p.getValue().doubleValue()).sum();

        Double sumRevenue= revenues.isEmpty()  ?  0: revenues.stream().mapToDouble(p->p.getValue().longValue()).sum();

        Double balance = sumRevenue - sumExpense;
        
        List<SummaryCategoryEnumResponse> listExpenseCategory=new ArrayList<>();

        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Alimentacao, sumFood));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Saude, sumHealth));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Moradia, sumHome));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Transporte, sumTransport));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Educacao, sumEducation));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Lazer, sumLaze));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Imprevistos, sumUnforeseen));
        listExpenseCategory.add(new SummaryCategoryEnumResponse(CategoryEnum.Outras, sumOther));
    
        log.info("sumExpense={} sumRevenue={} balance={} expensesCategory={}", sumExpense, sumRevenue,balance,listExpenseCategory);

        return new SummaryResponse(sumExpense, sumRevenue, balance, listExpenseCategory);
    }
}
