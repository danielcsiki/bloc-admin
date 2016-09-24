/**
 * 
 */
package ro.sci.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author yellow
 *
 */
@Entity
public class ChargeItem extends AbstractModel {

	@ManyToOne
	private Charge charge;

	@OneToOne
	
	private Expense expense;
	@NotNull
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
