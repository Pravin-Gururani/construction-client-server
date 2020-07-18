package com.construction.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Locations 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_SEQ")
	@SequenceGenerator(name = "LOCATION_SEQ", sequenceName = "LOCATION_SEQ", allocationSize = 1)
	private Integer locationId;
	
	@Column(name = "location_name")
	private String locationName;
	
	public Locations() {
	}

	public Locations(Integer locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}

	public Locations(String locationName) {
		this.locationName = locationName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
}
