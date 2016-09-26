/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);

}
