package jkz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

}
