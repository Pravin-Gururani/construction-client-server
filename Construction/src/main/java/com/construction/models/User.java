package com.construction.models;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	private String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "phone",unique=true,nullable=false)
	private Long phone;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "location")
	private String location;
	
//	@OneToOne(targetEntity = Locations.class)
//	@JoinColumn(name="location", referencedColumnName = "locationId",nullable=false)
//	private Locations location;
	
//	@OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
//	@JoinColumn(name="address", referencedColumnName = "addressId")
//	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	@JoinTable(	name = "user_address", 
//		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
//		inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "addressId"))
	
//	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "user", referencedColumnName = "id")
//	private List<Address> address;
	
	@OneToOne(targetEntity = EmployeeData.class,cascade = CascadeType.ALL)
	@JoinColumn(name="employeeData", referencedColumnName = "id")
	private EmployeeData employeeData;
	
	
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Roles> roles = new HashSet<>();
	
	
	@Column(name = "dob",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dob;
	
	@Column(name = "is_enabled", columnDefinition ="BIT DEFAULT 1")
	private Boolean isEnabled;
	
	@Column(name = "created_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDate;
	
	@Column(name = "last_modified_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date lastModifiedDate;
	
	
	private String lastModifiedBy;

	public User() {
	}
	
	public User(String name, String username, String email,
			String password, Long phone, Integer age,String image,String location,
			/* List<Address> address, */ EmployeeData employeeData,Date date, Boolean isEnabled, Date createDate, Date lastModifiedDate, String lastModifiedBy) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.age = age;
		this.image = image;
		this.location = location;
//		this.address = address;
		this.employeeData=employeeData;
		this.dob=date;
		this.isEnabled=isEnabled;
		this.createdDate=createDate;
		this.lastModifiedDate=lastModifiedDate;
		this.lastModifiedBy=lastModifiedBy;
	}

	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

//	public List<Address> getAddress() {
//		return address;
//	}
//
//	public void setAddress(List<Address> address) {
//		this.address = address;
//	}

	public EmployeeData getEmployeeData() {
		return employeeData;
	}

	public void setEmployeeData(EmployeeData employeeData) {
		this.employeeData = employeeData;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
}