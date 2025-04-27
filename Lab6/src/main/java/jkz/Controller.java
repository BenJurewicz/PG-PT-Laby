package jkz;

public class Controller {
	Repository repository;

	public String remove(Long id) {
		try {
			repository.remove(id);
			return "done";
		} catch (IllegalArgumentException e) {
			return "not found";
		}
	}
}
