package com.pluralsight.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;


@XmlRootElement
@Entity
public class Goal {

	@Id 
	@GeneratedValue
	private Long id;	
	public Long getId() {
		return id;
	}
	
	@Range(min = 1, max = 120)
	private int minutes;	
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
