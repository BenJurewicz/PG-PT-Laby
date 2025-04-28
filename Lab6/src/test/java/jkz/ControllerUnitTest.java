package jkz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerUnitTest {
	@Mock
	private Repository repository;

	@InjectMocks
	private Controller controller;

	@Test
	public void successfulRemove() {
		Long existingId = 1L;

		doNothing().when(repository).remove(existingId);

		assertThat(controller.remove(existingId)).isEqualTo("done");
	}

	@Test
	public void unsuccessfulRemove() {
		Long nonExistingId = 2L;

		doThrow(IllegalArgumentException.class).when(repository).remove(nonExistingId);

		assertThat(controller.remove(nonExistingId)).isEqualTo("not found");
	}

	@Test
	public void successfulFind() {
		Employee employee = new Employee("John Doe", "12345678901", 5000.0f, new Date());
		Long existingId = employee.getId();

		when(repository.find(existingId)).thenReturn(Optional.of(employee));

		assertThat(controller.find(existingId)).isEqualTo(employee.toString());
	}

	@Test
	public void unsuccessfulFind() {
		Long nonExistingId = 2L;

		when(repository.find(nonExistingId)).thenReturn(Optional.empty());

		assertThat(controller.find(nonExistingId)).isEqualTo("not found");
	}

	@Test
	public void successfulSave() {
		Employee employee = new Employee("John Doe", "12345678901", 5000.0f, new Date());

		doNothing().when(repository).save(any(Employee.class));

		assertThat(controller.save(employee)).isEqualTo("done");
	}

	@Test
	public void unsuccessfulSave() {
		Employee employee = new Employee("John Doe", "12345678901", 5000.0f, new Date());

		doNothing().doThrow(new IllegalArgumentException())
				.when(repository).save(employee);

		controller.save(employee);
        assertThat(controller.save(employee)).isEqualTo("bad request");
	}
}
