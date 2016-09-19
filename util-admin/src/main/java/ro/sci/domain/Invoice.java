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
public class Invoice extends AbstractModel {

	@OneToOne
	private Apartment apartment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", orphanRemoval = true)
	private List<InvoiceItem> invoiceItems = new ArrayList<>();

	private String invoiceStatus;

	private Date invoiceDate;

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public void addInvoiceItem(InvoiceItem invoiceItem) {
		invoiceItem.setInvoice(this);
		invoiceItems.add(invoiceItem);
	}

	public void removeInvoiceItem(InvoiceItem invoiceItem) {
		invoiceItem.setInvoice(null);
		invoiceItems.remove(invoiceItem);
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

}
