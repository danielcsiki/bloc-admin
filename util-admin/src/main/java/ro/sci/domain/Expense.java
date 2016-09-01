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
	private String calcReference;
	private Integer calcQuantum;
	private Float pricePerCalcQuantum;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCalcReference() {
		return calcReference;
	}

	public void setCalcReference(String calcReference) {
		this.calcReference = calcReference;
	}

	public Integer getCalcQuantum() {
		return calcQuantum;
	}

	public void setCalcQuantum(Integer calcQuantum) {
		this.calcQuantum = calcQuantum;
	}

	public Float getPricePerCalcQuantum() {
		return pricePerCalcQuantum;
	}

	public void setPricePerCalcQuantum(Float pricePerCalcQuantum) {
		this.pricePerCalcQuantum = pricePerCalcQuantum;
	}

}
