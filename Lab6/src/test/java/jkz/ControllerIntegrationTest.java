package jkz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ControllerIntegrationTest {
	private Controller controller;
	private Employee existingEmployee;
	private long existingId;
	private final long nonExistingId = -1L;

	@BeforeEach
	public void setUp() {
		controller = new Controller();
		existingEmployee = new Employee("John Doe", "12345678901", 5000.0f, new Date());
		existingId = existingEmployee.getId();
		controller.save(existingEmployee);
	}

	@Test
	public void successfulRemove() {
		assertThat(controller.remove(existingId)).isEqualTo("done");
	}

	@Test
	public void unsuccessfulRemove() {
		assertThat(controller.remove(nonExistingId)).isEqualTo("not found");
	}

	@Test
	public void successfulFind() {
		assertThat(controller.find(existingId)).isEqualTo(existingEmployee.toString());
	}

	@Test
	public void unsuccessfulFind() {
		assertThat(controller.find(nonExistingId)).isEqualTo("not found");
	}

	@Test
	public void successfulSave() {
		Employee employee = new Employee("John Doe", "12345678901", 5000.0f, new Date());
		assertThat(controller.save(employee)).isEqualTo("done");
	}

	@Test
	public void unsuccessfulSave() {
		assertThat(controller.save(existingEmployee)).isEqualTo("bad request");
	}
}
