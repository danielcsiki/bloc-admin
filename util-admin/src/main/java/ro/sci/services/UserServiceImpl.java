/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.commands.UserForm;
import ro.sci.converters.UserFormToUser;
import ro.sci.converters.UserToUserForm;
import ro.sci.domain.User;
import ro.sci.repositories.UserRepository;
import ro.sci.services.security.EncryptionService;

/**
 * @author luff
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private EncryptionService encryptionService;

	private UserFormToUser userFormToUser;
	private UserToUserForm userToUserForm;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Autowired
	public void setUserFormToUser(UserFormToUser userFormToUser) {
		this.userFormToUser = userFormToUser;
	}

	@Autowired
	public void setUserToUserForm(UserToUserForm userToUserForm) {
		this.userToUserForm = userToUserForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public User save(User user) {

		if (user.getPassword() != null) {
			user.setEncryptedPassword(encryptionService.encryptString(user.getPassword()));
		}
		return userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.UserService#saveUserForm(java.lang.Object)
	 */
	@Override
	public UserForm saveUserForm(UserForm userForm) {
		User newUser = userFormToUser.convert(userForm);
		if (newUser.getId() != null) {
			User storedUser = this.getById(newUser.getId());
			return userToUserForm.convert(this.save(storedUser));
		} else {
			return userToUserForm.convert(this.save(newUser));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.UserService#findByUserName(java.lang.Object)
	 */
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

}
