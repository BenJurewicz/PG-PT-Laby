package jkz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ControllerIntegrationTest {
	private Controller controller;

	@BeforeEach
	public void setUp() {
		controller = new Controller();
	}

	@Test
	public void successfulRemove() {
		Long existingId = 1L;

		assertThat(controller.remove(existingId)).isEqualTo("done");
	}

	@Test
	public void unsuccessfulRemove() {
		Long nonExistingId = 2L;

		assertThat(controller.remove(nonExistingId)).isEqualTo("not found");
	}
}
