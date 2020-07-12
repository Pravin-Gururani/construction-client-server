package com.pg.construction.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "commissions")
public class Commissions 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMISSION_SEQ")
	@SequenceGenerator(name = "COMMISSION_SEQ", sequenceName = "COMMISSION_SEQ", allocationSize = 1 )
	private Integer commissionId;
	
	@OneToOne(targetEntity = Bookings.class)
	@JoinColumn(name="booking_id", referencedColumnName = "bookingId")
	private Bookings bookingId;
	
	@Column(name="total_commission_amount")
	private Integer totalCommissionAmount;
	
	@Column(name="due_commission_amount")
	private Integer dueCommissionAmount;
	
	@Column(name="commission_status")
	private String commissionStatus;

	public Commissions() {
	}

	public Commissions(Bookings bookingId, Integer totalCommissionAmount, Integer dueCommissionAmount,
			String commissionStatus) {
		super();
		this.bookingId = bookingId;
		this.totalCommissionAmount = totalCommissionAmount;
		this.dueCommissionAmount = dueCommissionAmount;
		this.commissionStatus = commissionStatus;
	}

	public Commissions(Integer commissionId, Bookings bookingId, Integer totalCommissionAmount,
			Integer dueCommissionAmount, String commissionStatus) {
		super();
		this.commissionId = commissionId;
		this.bookingId = bookingId;
		this.totalCommissionAmount = totalCommissionAmount;
		this.dueCommissionAmount = dueCommissionAmount;
		this.commissionStatus = commissionStatus;
	}

	public Integer getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Integer commissionId) {
		this.commissionId = commissionId;
	}

	public Bookings getBookingId() {
		return bookingId;
	}

	public void setBookingId(Bookings bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getTotalCommissionAmount() {
		return totalCommissionAmount;
	}

	public void setTotalCommissionAmount(Integer totalCommissionAmount) {
		this.totalCommissionAmount = totalCommissionAmount;
	}

	public Integer getDueCommissionAmount() {
		return dueCommissionAmount;
	}

	public void setDueCommissionAmount(Integer dueCommissionAmount) {
		this.dueCommissionAmount = dueCommissionAmount;
	}

	public String getCommissionStatus() {
		return commissionStatus;
	}

	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}

}
