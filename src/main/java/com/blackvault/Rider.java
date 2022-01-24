package com.blackvault;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String email;

	private String surname;

	private int phonenumber;

	private int age;

	private String address;

	private Date dob;

	public Rider() {
	}

	public Rider(String name, String email) {

		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Rider))
			return false;
		Rider rider = (Rider) o;
		return Objects.equals(this.id, rider.id) && Objects.equals(this.name, rider.name)
				&& Objects.equals(this.email, rider.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.email);
	}

	@Override
	public String toString() {
		return "Rider{" + "id=" + this.id + ", name='" + this.name + '\'' + ", Email='" + this.email + '\'' + '}';
	}
}
