package jkz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class Controller {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private final Repository repository;

	Controller() {
		this.repository = new Repository();
	}

	Controller(Repository repository) {
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

	public String save(Employee employee) {
		try {
			repository.save(employee);
			return "done";
		} catch (IllegalArgumentException e) {
			return "bad request";
		}
	}
}
