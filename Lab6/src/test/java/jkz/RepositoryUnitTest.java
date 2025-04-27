package jkz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@ExtendWith(MockitoExtension.class)
public class RepositoryUnitTest {
	Repository repository;

	Employee existingEmployee;
	Long existingId;
	Long nonExistingId = -1L;


	@BeforeEach
	public void setUp() {
		repository = new Repository();
		existingEmployee = new Employee("John Doe", "12345678901", 5000.0f, new Date());
		repository.save(existingEmployee);
		existingId = existingEmployee.getId();
	}

	@Test
	public void successfulRemove() {
		assertThatCode(() -> repository.remove(existingId)).doesNotThrowAnyException();
	}

	@Test
	public void unsuccessfulRemove() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> repository.remove(nonExistingId));
	}

	@Test
	public void successfulFind() {
		assertThat(repository.find(existingId)).isEqualTo(Optional.of(existingEmployee));
	}

	@Test
	public void unsuccessfulFind() {
		assertThat(repository.find(nonExistingId)).isEqualTo(Optional.empty());
	}

	@Test
	public void successfulSave() {
		Employee employee = new Employee("John Doe", "12345678901", 5000.0f, new Date());
		assertThatCode(() -> repository.save(employee)).doesNotThrowAnyException();
	}

	@Test
	public void unsuccessfulSave() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
				() -> repository.save(existingEmployee)
		);
	}
}
