/**
 * 
 */
package ro.sci.domain.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ro.sci.domain.AbstractModel;
import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
@Entity
public class Role extends AbstractModel {

	private String role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<User> users = new ArrayList<>();

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
		if (!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}

	public void removeUser(User user) {
		users.remove(user);
		user.getRoles().remove(this);
	}

}
