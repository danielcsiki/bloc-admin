/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.Charge;

/**
 * @author yellow
 *
 */
public interface ChargeRepository extends CrudRepository<Charge, Integer> {

}
