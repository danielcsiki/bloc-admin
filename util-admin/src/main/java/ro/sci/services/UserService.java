/**
 * 
 */
package ro.sci.services;

import ro.sci.commands.UserForm;
import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
public interface UserService extends CrudService<User> {

	UserForm saveUserForm(UserForm userForm);

	User findByUserName(String userName);

}
