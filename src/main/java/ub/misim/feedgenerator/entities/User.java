package ub.misim.feedgenerator.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User extends CommonBaseClass {

	public User() {
	}

	private String username;
	private String email;
	private String firstName;
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserInterestMap> interests = new ArrayList<UserInterestMap>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UserRole> userRoles = new ArrayList<UserRole>();

	/*
	 * View Properties
	 */
	@Transient
	private Set<String> roles = new HashSet<String>();

	@JsonProperty("topics")
	@Transient
	private Map<String, Float> interestWeightMap = new HashMap<String, Float>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public List<UserInterestMap> getInterests() {
		return interests;
	}

	@JsonIgnore
	public void setInterests(List<UserInterestMap> interests) {
		this.interests = interests;
	}

	@JsonIgnore
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	@JsonIgnore
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Map<String, Float> getInterestWeightMap() {
		return interestWeightMap;
	}

	public void setInterestWeightMap(Map<String, Float> interestWeightMap) {
		this.interestWeightMap = interestWeightMap;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
