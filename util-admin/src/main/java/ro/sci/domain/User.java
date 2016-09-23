/**
 * 
 */
package ro.sci.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ro.sci.domain.security.Role;

/**
 * @author yellow
 *
 */
@Entity
public class User extends AbstractModel {

	private String username;
	@Transient
	private String password;
	private String encryptedPassword;
	private Boolean enabled = true;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Apartment apartment;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Charge charge;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Finance finance;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<Role> roles = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
		apartment.setUser(this);
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
		charge.setUser(this);
	}

	public Finance getFinance() {
		return finance;
	}

	public void setFinance(Finance finance) {
		this.finance = finance;
		finance.setUser(this);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (!roles.contains(role)) {
			roles.add(role);
		}
		if (!role.getUsers().contains(this)) {
			role.getUsers().add(this);
		}
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}

}
