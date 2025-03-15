package jkz;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Human {
	private String name;
	private char gender;
	private int age;
	private final Set<Human> children;

	/**
	 * Create a new human with a name, gender and age
	 * @param name   - person's name
	 * @param gender - person's gender, valid values are 'm', 'f' or 'o', if not valid value is provided, defaults to 'o'
	 * @param age    - person's age, valid range is 0 to 200, if not valid value is provided, defaults to 0
	 */
	public Human(String name, char gender, int age) {
		this.name = name;
		this.gender = isValidGender(gender) ? gender : 'o';
		this.age = isValidAge(age) ? age : 0;
		this.children = new HashSet<>();
	}

	/**
	 * Create a new human with a name, gender and age
	 * @param name     - person's name
	 * @param gender   - person's gender, valid values are 'm', 'f' or 'o', if not valid value is provided, defaults to 'o'
	 * @param age      - person's age, valid range is 0 to 200, if not valid value is provided, defaults to 0
	 * @param children - person's children
	 */
	public Human(String name, char gender, int age, Set<Human> children) {
		this(name, gender, age);
		addChildren(children);
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
	 * @param age - persons age, valid range is 0 to 200
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

	public boolean removeChildren(Set<Human> children) {
		return this.children.removeAll(children);
	}
	
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public void removeAllChildren() {
		this.children.clear();
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
}
