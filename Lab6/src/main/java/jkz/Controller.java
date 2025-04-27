package jkz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Controller {
	private final Repository repository;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public Controller() {
		this.repository = new Repository();
	}

	public Controller(Repository repository) {
		this.repository = repository;
	}

	public String remove(Long id) {
		try {
			repository.remove(id);
			return "done";
		} catch (IllegalArgumentException e) {
			return "not found";
		}
	}

	public String find(Long id) {
		Optional<Employee> employee = repository.find(id);
		if (employee.isPresent()) {
			return employee.get().toString();
		} else {
			return "not found";
		}
	}

	public String save(String employeeData) {
		try {
			Employee employeeToSave = parseEmployeeString(employeeData);
			repository.save(employeeToSave);
			return "done";
		} catch (ParseException | IllegalArgumentException e) {
			return "bad request";
		}
	}

	private Employee parseEmployeeString(String employeeData)
			throws ParseException, NumberFormatException, IllegalArgumentException {
		String[] parts = employeeData.split(",");
		if (parts.length != 4) {
			throw new IllegalArgumentException("Invalid employee data format");
		}

		String name = parts[0].trim();
		String pesel = parts[1].trim();
		float salary = Float.parseFloat(parts[2].trim());
		Date employedFrom = DATE_FORMAT.parse(parts[3].trim());
		return new Employee(name, pesel, salary, employedFrom);
	}
}
