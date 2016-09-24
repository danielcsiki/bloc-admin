/**
 * 
 */
package ro.sci.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author yellow
 *
 */
@Entity
public class Finance extends AbstractModel {

	private Date chargeDate;
	private Float chargeSum;
	private Float payment;
	private Float balance;
	@OneToOne
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "finance", orphanRemoval = true)
	private List<Charge> charges = new ArrayList<>();

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public Float getChargeSum(Charge charge) {
		chargeSum = charge.getChargeSum();
		return chargeSum;
	}

	public void setChargeSum(Float chargeSum) {
		this.chargeSum = chargeSum;
	}

	public Float getPayment() {
		return payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	public Float getBalance(Charge charge) {
		balance = charge.getChargeSum() - payment;
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public void addCharge(Charge charge) {
		if (chargeDate.equals(charge.getChargeDate())) {
			charge.setFinance(this);
			charges.add(charge);
		} else {
			throw new IllegalArgumentException("mismatch between charge and finance");
		}
	}

	public void removeCharge(Charge charge) {
		if (chargeDate.equals(charge.getChargeDate())) {
			charge.setFinance(null);
			charges.remove(charge);
		} else {
			throw new IllegalArgumentException("mismatch between charge and finance");
		}
	}

}
