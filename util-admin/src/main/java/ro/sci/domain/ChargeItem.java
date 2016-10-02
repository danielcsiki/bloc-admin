/**
 * 
 */
package ro.sci.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yellow
 *
 */
@Entity
public class ChargeItem extends AbstractModel {

	@ManyToOne
	private Charge charge;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date chargeDate;
	@OneToOne
	private Expense expense;
	private Integer amount;

	private Float cost;

	public Float getCost() {
		cost = expense.getUnitPrice() * amount;
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
