/**
 * 
 */
package ro.sci.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author luff
 *
 */
@MappedSuperclass
public abstract class AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

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

}
