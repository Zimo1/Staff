package ru.yodata.staff.model.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

// Класс данных компании. Сгенерирован автоматически по образцу REST-ответа сервера
public class Company{

	@SerializedName("competences")
	private List<String> competences;

	@SerializedName("name")
	private String name;

	@SerializedName("employees")
	private List<EmployeesItem> employees;

	@SerializedName("age")
	private String age;

	public void setCompetences(List<String> competences){
		this.competences = competences;
	}

	public List<String> getCompetences(){
		return competences;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmployees(List<EmployeesItem> employees){
		this.employees = employees;
	}

	public List<EmployeesItem> getEmployees(){
		return employees;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}
}