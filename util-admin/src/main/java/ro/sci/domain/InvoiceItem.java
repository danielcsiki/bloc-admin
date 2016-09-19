/**
 * 
 */
package ro.sci.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author yellow
 *
 */
@Entity
public class InvoiceItem extends AbstractModel {

	@ManyToOne
	private Invoice invoice;

	@OneToOne
	private Expense expense;

	private Integer amount;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
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
