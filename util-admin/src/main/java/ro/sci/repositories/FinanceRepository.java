/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.Finance;

/**
 * @author yellow
 *
 */
public interface FinanceRepository extends CrudRepository<Finance, Integer> {

}
