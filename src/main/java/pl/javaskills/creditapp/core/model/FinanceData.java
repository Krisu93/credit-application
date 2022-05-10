package pl.javaskills.creditapp.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidationCollection;

import java.io.Serializable;
import java.util.*;

public class FinanceData implements Serializable {
    public static final long serialVersionUID = 1;
    private static final Logger log = LoggerFactory.getLogger(FinanceData.class);

    @NotNull()
    @ValidationCollection
    private final List<SourceOfIncome> sourceOfIncome;
    @NotNull()
    @ValidationCollection
    private final Set<Expense> expenses;

    public FinanceData(SourceOfIncome... sourceOfIncomes){
        this.sourceOfIncome = sourceOfIncomes != null ? List.of(sourceOfIncomes) : new ArrayList<>();
        this.expenses = new HashSet<>();
    }

    public FinanceData(Set<Expense> expenses, SourceOfIncome... sourceOfIncomes){
        this.sourceOfIncome = sourceOfIncomes != null ? List.of(sourceOfIncomes) : new ArrayList<>();
        this.expenses = expenses;
    }

    public List<SourceOfIncome> getSourceOfIncome() {
        return sourceOfIncome;
    }
    public Set<Expense> getExpenses() {
        return expenses;
    }

    public double sumMonthlyIncomeInPln(){
        double monthlyIncomeInPln = 0;
        for(SourceOfIncome source :  sourceOfIncome){
            monthlyIncomeInPln += source.getNetMonthlyIncome();
        }
        log.info("Sum monthly income in pln = {}", monthlyIncomeInPln);
        return monthlyIncomeInPln;
    }

    private Map<ExpenseType, Set<Expense>> getExpensesMap(){
        Map<ExpenseType, Set<Expense>> expenseMap = new HashMap<>();
        for (Expense expense : this.expenses){
            if(expenseMap.containsKey(expense.getType()))
                expenseMap.get(expense.getType()).add(expense);
            else {
                Set<Expense> expenseSet = new HashSet<>();
                expenseSet.add(expense);
                expenseMap.put(expense.getType(), expenseSet);
            }
        }
        return expenseMap;
    }

    public double getSumOfExpenses(ExpenseType type) {
        Set<Expense> expenses = getExpensesMap().get(type);
        double sumAmount = 0.0;
        if (expenses != null)
            for (Expense expense : expenses)
                sumAmount += expense.getAmount();

        return sumAmount;
    }
}
