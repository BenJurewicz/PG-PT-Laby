package jkz.Database.Entities;

import jakarta.persistence.*;
import jkz.Database.Misc.Persistable;

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

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@OneToOne
	@JoinColumn(name = "manages_id")
	private Employee manager;

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
}