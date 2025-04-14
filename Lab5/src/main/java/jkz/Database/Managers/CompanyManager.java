package jkz.Database.Managers;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;

import java.util.List;

public class CompanyManager extends Manager<Company> {
	public CompanyManager() {
		super(Company.class);
	}
}
