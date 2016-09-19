/**
 * 
 */
package ro.sci.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author yellow
 *
 */
@Entity
public class Charge extends AbstractModel {

	private Date chargeDate;

	private Float chargeSum;

	@OneToOne
	private User user;

	@ManyToOne
	private Finance finance;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "charge", orphanRemoval = true)
	private List<ChargeItem> chargeItems = new ArrayList<>();

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public Float getChargeSum() {
		chargeSum = 0f;
		for (ChargeItem chargeItem : chargeItems) {
			chargeSum += chargeItem.getCost();
		}
		return chargeSum;
	}

	public void setChargeSum(Float chargeSum) {
		this.chargeSum = chargeSum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Finance getFinance() {
		return finance;
	}

	public void setFinance(Finance finance) {
		this.finance = finance;
	}

	public List<ChargeItem> getChargeItems() {
		return chargeItems;
	}

	public void setChargeItems(List<ChargeItem> chargeItems) {
		this.chargeItems = chargeItems;
	}

	public void addChargeItem(ChargeItem chargeItem) {
		chargeItem.setCharge(this);
		chargeItems.add(chargeItem);
	}

	public void removeChargeItem(ChargeItem chargeItem) {
		chargeItem.setCharge(null);
		chargeItems.remove(chargeItem);
	}

}
