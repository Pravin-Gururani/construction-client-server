package com.construction.models;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Bookings 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ")
	@SequenceGenerator(name = "BOOKING_SEQ", sequenceName = "BOOKING_SEQ", allocationSize = 1 )
	private Integer bookingId;
	
	@Column(name = "bookingFrom",nullable=false)
	private Date bookingFrom;
	
	@Column(name = "bookingTo",nullable=false)
	private Date bookingTo;
	
	@Column(name="status")
	private String status;
	
	@Column(name="days_worked")
	private Integer daysWorked;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name="user", referencedColumnName = "Id")
//	 @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
	private User user;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name="employee", referencedColumnName = "Id")
//	 @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
	private User employee;
	
	@OneToOne(targetEntity = Rating.class, cascade = CascadeType.ALL)
	@JoinColumn(name="rating", referencedColumnName = "Id")
	private Rating rating;

	//@OneToOne(targetEntity = Address.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@Column(name="address")
	private String address;
	
	@Column(name="service")
	private String service;
	
	@Column(name="subService")
	private String subService;
	
	@Column(name="description")
	private String description;
	
	public Bookings() {
	}

	public Bookings(Date bookingFrom, Date bookingTo, String status, Integer daysWorked, User user,
			User employee,Rating rating, String address,String service,String subService,String description) {
		super();
		this.bookingFrom = bookingFrom;
		this.bookingTo = bookingTo;
		this.status = status;
		this.daysWorked = daysWorked;
		this.user = user;
		this.employee = employee;
		this.rating=rating;
		this.address=address;
		this.service=service;
		this.subService=subService;
		this.description=description;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookingFrom() {
		return bookingFrom;
	}

	public void setBookingFrom(Date bookingFrom) {
		this.bookingFrom = bookingFrom;
	}

	public Date getBookingTo() {
		return bookingTo;
	}

	public void setBookingTo(Date bookingTo) {
		this.bookingTo = bookingTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDaysWorked() {
		return daysWorked;
	}

	public void setDaysWorked(Integer daysWorked) {
		this.daysWorked = daysWorked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSubService() {
		return subService;
	}

	public void setSubService(String subService) {
		this.subService = subService;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
//	@OneToOne(mappedBy="addressId")
//	private Employees employees;	
//	@OneToOne(mappedBy="addressId")
//	private Users users;
}