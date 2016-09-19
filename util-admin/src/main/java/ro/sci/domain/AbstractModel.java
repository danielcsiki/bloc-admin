/**
 * 
 */
package ro.sci.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date lastUpdated;

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

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	@PrePersist
	public void onCreate() {
		lastUpdated = dateCreated = new Date();
	}

	@PreUpdate
	public void updateTimeStamps() {
		lastUpdated = new Date();
	}

}
