/**
 * 
 */
package ro.sci.commands;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author yellow
 *
 */
public class UserForm {

	private Integer id;
	private Integer version;
	private Integer apartmentId;
	private Integer apartmentVersion;
	@NotEmpty
	private String username;
	@NotEmpty
	private String passwordText;
	@NotEmpty
	private String passwordTextConf;
	private Integer nr;
	private Integer nrResidents;
	private String firstName;
	private String lastName;
	@NotEmpty
	@Email
	private String email;
	private String phoneNumber;
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}

	public Integer getApartmentVersion() {
		return apartmentVersion;
	}

	public void setApartmentVersion(Integer apartmentVersion) {
		this.apartmentVersion = apartmentVersion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}

	public String getPasswordTextConf() {
		return passwordTextConf;
	}

	public void setPasswordTextConf(String passwordTextConf) {
		this.passwordTextConf = passwordTextConf;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public Integer getNrResidents() {
		return nrResidents;
	}

	public void setNrResidents(Integer nrResidents) {
		this.nrResidents = nrResidents;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
