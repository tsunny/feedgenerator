package ub.misim.feedgenerator.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Topic extends CommonBaseClass {

	@Column(unique = true)
	private String name;

	private String lowerCasedName;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Topic> concepts = new ArrayList<Topic>();

	public Topic() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLowerCasedName() {
		return lowerCasedName;
	}

	public void setLowerCasedName(String lowerCasedName) {
		this.lowerCasedName = lowerCasedName;
	}

	public List<Topic> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<Topic> concepts) {
		this.concepts = concepts;
	}

}
