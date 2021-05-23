package com.construction.payload.request;
import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.construction.models.EmployeeData;
 
public class SignupRequest {
 
    //@NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> roles;
    
    //@NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    //@NotBlank
    @Size(min = 10, max = 10)
    private Long phone;
    
	private String name;
	
	
	private Integer age;
	
	private String experience;
	
	private Integer commissionRate;
	
	private boolean isVerified;
	
	private String occupation;
	
	private String image;
	
	private boolean availability;
	
	private Date jobStartDate;
	
	private String aadharFront;
	
	private String aadharBack;
	
	private String location;
	
	private EmployeeData employeeData;
	
	private Date dob;
	
	private Boolean isEnabled;

  
    public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
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
    
    public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRoles(Set<String> role) {
      this.roles = role;
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Integer getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Integer commissionRate) {
		this.commissionRate = commissionRate;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Date getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(Date jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public String getAadharFront() {
		return aadharFront;
	}

	public void setAadharFront(String aadharFront) {
		this.aadharFront = aadharFront;
	}

	public String getAadharBack() {
		return aadharBack;
	}

	public void setAadharBack(String aadharBack) {
		this.aadharBack = aadharBack;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public EmployeeData getEmployeeData() {
		return employeeData;
	}

	public void setEmployeeData(EmployeeData employeeData) {
		this.employeeData = employeeData;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}