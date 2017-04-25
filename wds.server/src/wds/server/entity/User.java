package wds.server.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wds_user")
public class User implements Serializable {

	private static final long serialVersionUID = -2885738547050322312L;

	@Id
	private String id;
	
	private String name;
	
	private String password;
	
	private String captch;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "datetime")
    private Date createTime;
	
	public User(){
		this.id = UUID.randomUUID().toString();
        this.createTime = Calendar.getInstance().getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getCaptch() {
		return captch;
	}

	public void setCaptch(String captch) {
		this.captch = captch;
	}
}
