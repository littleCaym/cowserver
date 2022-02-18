package com.coweco.server.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Good {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String title;
	String brand;
	float brand_rating;
	String date_upload;
	@Column(columnDefinition = "TEXT")
	String description;
	String location;
	float price;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "session_id")
	SessionAvito sessionAvito;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getBrand_rating() {
		return brand_rating;
	}

	public void setBrand_rating(float brand_rating) {
		this.brand_rating = brand_rating;
	}

	public String getDate_upload() {
		return date_upload;
	}

	public void setDate_upload(String date_upload) {
		this.date_upload = date_upload;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public SessionAvito getSessionAvito() {
		return sessionAvito;
	}

	public void setSessionAvito(SessionAvito sessionAvito) {
		this.sessionAvito = sessionAvito;
	}

}
