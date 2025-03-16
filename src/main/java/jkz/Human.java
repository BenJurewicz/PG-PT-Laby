package jkz;

import java.util.*;


public class Human implements Comparable<Human> {
	public enum Sort {
		NONE, DEFAULT, ALTERNATIVE
	}

	private String name;
	private char gender;
	private int age;
	private final Set<Human> children;

	/**
	 * Create a new human with a name, gender and age
	 * @param name       person's name
	 * @param gender     person's gender, valid values are 'm', 'f' or 'o',
	 *                   if not valid value is provided, defaults to 'o'
	 * @param age        person's age, valid range is 0 to 200, if not valid value is provided, defaults to 0
	 * @param sort       sort type, valid values are 'none', 'default' or 'alternative',
	 *                   if not valid value is provided, defaults to 'none'
	 * @param comparator comparator used for sorting children (used only when sort is 'alternative', can be null
	 *                   otherwise)
	 */
	public Human(String name, char gender, int age, Sort sort, Comparator<Human> comparator) {
		this.name = name;
		this.gender = isValidGender(gender) ? gender : 'o';
		this.age = isValidAge(age) ? age : 0;

		switch (sort) {
			case DEFAULT:
				this.children = new TreeSet<>();
				break;
			case ALTERNATIVE:
				this.children = new TreeSet<>(comparator);
				break;
			default: // NONE
				this.children = new HashSet<>();
				break;
		}
	}

	/**
	 * Create a new human with a name, gender and age
	 * @param name   person's name
	 * @param gender person's gender, valid values are 'm', 'f' or 'o',
	 *               if not valid value is provided, defaults to 'o'
	 * @param age    person's age, valid range is 0 to 200, if not valid value is provided, defaults to 0
	 */
	public Human(String name, char gender, int age) {
		this.name = name;
		this.gender = isValidGender(gender) ? gender : 'o';
		this.age = isValidAge(age) ? age : 0;
		this.children = new HashSet<>();
	}

	private static boolean isValidGender(char gender) {
		return gender == 'm' || gender == 'f' || gender == 'o';
	}

	private static boolean isValidAge(int age) {
		return age >= 0 && age <= 200;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	/**
	 * Set gender to 'm', 'f' or 'o'
	 * <ul>
	 *     <li> m - male </li>
	 *     <li> f - female </li>
	 *     <li> o - other </li>
	 * </ul>
	 * @param gender 'm', 'f' or 'o'
	 * @return true if gender is set successfully, false otherwise
	 */
	public boolean setGender(char gender) {
		if (!isValidGender(gender)) {
			return false;
		}
		this.gender = gender;
		return true;
	}

	public int getAge() {
		return age;
	}

	/**
	 * Set age between 0 and 200
	 * @param age persons age, valid range is 0 to 200
	 * @return true if age is set successfully, false otherwise
	 */
	public boolean setAge(int age) {
		if (!isValidAge(age)) {
			return false;
		}
		this.age = age;
		return true;
	}

	public boolean isParentOf(Human child) {
		return children.contains(child);
	}

	public boolean isChildOf(Human parent) {
		return parent.isParentOf(this);
	}

	public boolean addChild(Human child) {
		return children.add(child);
	}

	public boolean removeChild(Human child) {
		return children.remove(child);
	}

	public boolean addChildren(Set<Human> children) {
		return this.children.addAll(children);
	}

	public boolean addChildren(Human... children) {
		boolean result = false;
		for (Human child : children) {
			result = addChild(child) || result;
		}
		return result;
	}

	public boolean removeChildren(Set<Human> children) {
		return this.children.removeAll(children);
	}

	public boolean removeChildren(Human... children) {
		boolean result = false;
		for (Human child : children) {
			result = result || removeChild(child);
		}
		return result;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public void removeAllChildren() {
		this.children.clear();
	}

	/**
	 * Get children as list, does not allow for modification of the original list, but allows for modification of the
	 * children themselves
	 * @return a shallow copy of children, can be modified without affecting the original list
	 */
	public List<Human> getChildren() {
		return new ArrayList<>(children);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Human human = (Human) object;
		return getAge() == human.getAge() && Objects.equals(getName(), human.getName()) && Objects.equals(children,
		                                                                                                  human.children);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getAge(), children);
	}

	@Override
	public int compareTo(Human human) {
		return this.getName().compareTo(human.getName());
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder(name + "(" + gender + ")(" + age + ")" + "[");

		Iterator<Human> iterator = children.iterator();
		while (iterator.hasNext()) {
			output.append(iterator.next().getName());
			if (iterator.hasNext()) {
				output.append(", ");
			}
		}

		output.append("]");
		return output.toString();
	}

	public String printHierarchy() {
		StringBuilder output = new StringBuilder();
		buildHierarchy(0, output); // Start with indentation level 0
		return output.toString();
	}

	private void buildHierarchy(int indentLevel, StringBuilder output) {
		// Add indentation based on the current level
		for (int i = 0; i < indentLevel; i++) {
			output.append("    "); // 4 spaces per level
		}

		// Print the current human's details
		output.append(this.toString()).append("\n");

		// Recursively build the hierarchy for each child
		for (Human child : children) {
			child.buildHierarchy(indentLevel + 1, output);
		}
	}
}
