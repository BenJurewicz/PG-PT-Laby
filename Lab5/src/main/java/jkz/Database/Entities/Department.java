package jkz.Database.Entities;

import jakarta.persistence.*;
import jkz.Database.Misc.Persistable;

import java.util.Collections;
import java.util.List;

@Entity
public class Department implements Persistable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String location;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
	private List<Employee> employees;

	protected Department() {
	}

	public Department(String name, String location, Company company) {
		this.name = name;
		this.location = location;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Company getCompany() {
		return company;
	}

	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				", location='" + location + '\'' +
				", company=" + company +
				'}';
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}