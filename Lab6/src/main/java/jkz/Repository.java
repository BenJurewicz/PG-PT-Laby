package jkz;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jkz.Employee;

public class Repository {
	private final Map<Long, Employee> employees = new HashMap<>();

	public Long save(Employee employee) throws IllegalArgumentException {
		// Returns added employee's id
		throw new IllegalArgumentException("Not implemented yet");
	}

	public Optional<Employee> find(Long id) {
		return Optional.empty();
	}

	public void remove(Long id) throws IllegalArgumentException {
		throw new IllegalArgumentException("Not implemented yet");
	}
}
