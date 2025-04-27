package jkz;

import java.util.Date;

public class Employee {
	private Long id;
	private String name;
	String pesel;
	float salary;
	Date employedFrom;

	public Employee(String name, String pesel, float salary, Date employedFrom) {
		this.name = name;
		this.pesel = pesel;
		this.salary = salary;
		this.employedFrom = employedFrom;
	}

	public Long getId() {
		return id;
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
				'}';
	}

	public void setEmployedFrom(Date employedFrom) {
		this.employedFrom = employedFrom;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public void setName(String name) {
		this.name = name;
	}
}
