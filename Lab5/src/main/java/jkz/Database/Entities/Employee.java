package jkz.Database.Entities;

import jakarta.persistence.*;
import jkz.Database.Misc.Persistable;

import java.util.Date;

@Entity
public class Employee implements Persistable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "department_id")
	Department department;

	@OneToOne
	@JoinColumn(name = "manager_id")
	private Department manages;

	private String name;
	String pesel;
	float salary;
	Date employedFrom;

	protected Employee() {
	}

	public Employee(String name, String pesel, float salary, Date employedFrom, Department department) {
		this.name = name;
		this.pesel = pesel;
		this.salary = salary;
		this.employedFrom = employedFrom;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public Department getDepartment() {
		return department;
	}

	public String getName() {
		return name;
	}

	public String getPesel() {
		return pesel;
	}

	public float getSalary() {
		return salary;
	}

	public Date getEmployedFrom() {
		return employedFrom;
	}

	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pesel='" + pesel + '\'' +
				", salary=" + salary +
				", employedFrom=" + employedFrom +
				", department=" + department +
				'}';
	}
}
