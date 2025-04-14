package jkz.Database.Entities;

import jakarta.persistence.*;
import jkz.Database.Misc.Persistable;

import java.util.Collections;
import java.util.List;

@Entity
public class Company implements Persistable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Department> departments;

	private String name;
	private String industry;

	protected Company() {
	}

	public Company(String name, String industry) {
		this.name = name;
		this.industry = industry;
	}

	public Long getId() {
		return id;
	}

	public List<Department> getDepartments() {
		return Collections.unmodifiableList(departments);
	}

	public String getName() {
		return name;
	}

	public String getIndustry() {
		return industry;
	}

	public String toString() {
		return "Company{" +
				"id=" + id +
				", name='" + name + '\'' +
				", industry='" + industry + '\'' +
				'}';
	}
}
