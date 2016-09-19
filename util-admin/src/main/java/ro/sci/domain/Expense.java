/**
 * 
 */
package ro.sci.domain;

import javax.persistence.Entity;

/**
 * @author luff
 *
 */
@Entity
public class Expense extends AbstractModel {

	private String item;
	private String unitReference;
	private Float unitPrice;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getUnitReference() {
		return unitReference;
	}

	public void setUnitReference(String unitReference) {
		this.unitReference = unitReference;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

}
