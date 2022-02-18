package com.coweco.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SessionAvito {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	Timestamp timestamp;
	@JsonIgnore
	@OneToMany(mappedBy = "sessionAvito", fetch = FetchType.LAZY)
	Set<Good> goodSet = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Set<Good> getGoodSet() {
		return goodSet;
	}

	public void setGoodSet(Set<Good> goodSet) {
		this.goodSet = goodSet;
	}

}
