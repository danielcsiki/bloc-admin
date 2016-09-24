/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.ChargeItem;

/**
 * @author yellow
 *
 */
public interface ChargeItemRepository extends CrudRepository<ChargeItem, Integer> {

}
