package jkz;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jkz.Employee;

public class Repository {
	private final Map<Long, Employee> employees = new HashMap<>();

	public void save(Employee employee) throws IllegalArgumentException {
		if (employees.containsKey(employee.getId())) {
			throw new IllegalArgumentException("Employee with id " + employee.getId() + " already exists");
		}
		employees.put(employee.getId(), employee);
	}

	public Optional<Employee> find(Long id) {
		Employee employee = employees.get(id);
		if (employee == null) {
			return Optional.empty();
		}
		return Optional.of(employee);
	}

	public void remove(Long id) throws IllegalArgumentException {
		if(employees.remove(id) == null) {
			throw new IllegalArgumentException("Employee with id " + id + " does not exist");
		}
	}
}
