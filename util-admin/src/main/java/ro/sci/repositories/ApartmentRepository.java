/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.Apartment;

/**
 * @author yellow
 *
 */
public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

}
