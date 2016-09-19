/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.security.Role;

/**
 * @author yellow
 *
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
