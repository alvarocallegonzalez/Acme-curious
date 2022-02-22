package com.alvcalgon.acme.AcmeExplorer.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
public class Ranger extends Actor {

	private static final long serialVersionUID = 1L;

	public Ranger() {
		super();
	}

}
