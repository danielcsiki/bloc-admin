/**
 * 
 */
package ro.sci.domain;

import javax.persistence.Entity;

/**
 * @author yellow
 *
 */
@Entity
public class Apartment extends AbstractModel {

	private Integer nr;
	private Integer nrResidents;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;

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
