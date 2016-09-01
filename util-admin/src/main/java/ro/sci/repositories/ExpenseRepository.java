/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.Expense;

/**
 * @author luff
 *
 */
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

}
