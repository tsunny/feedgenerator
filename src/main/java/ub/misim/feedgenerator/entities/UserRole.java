package ub.misim.feedgenerator.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class UserRole extends CommonBaseClass {

	@Column(unique = true)
	private String name;

	public UserRole() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
