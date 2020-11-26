package ru.yodata.staff.model.dto;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

// Класс данных сотрудников. Сгенерирован автоматически по образцу REST-ответа сервера
public class EmployeesItem implements Comparable<EmployeesItem> {

	// Конструктор, присваивающий полям значения по умолчанию.
	// Создан из-за того, что в некоторых записях на сервере отсутствуют отдельные поля,
	// что вызывает сбой приложения при попытке отобразить несуществующее значение.
	public EmployeesItem() {
		//super();
		this.name = "-";
		this.phoneNumber = "отсутствует";
		this.skills = new ArrayList<String>();
		this.skills.add("Не имеет компетенций");
	}

	@SerializedName("name")
	private String name;

	@SerializedName("phone_number")
	private String phoneNumber;

	@SerializedName("skills")
	private List<String> skills;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setSkills(List<String> skills){
		this.skills = skills;
	}

	public List<String> getSkills(){
		return skills;
	}

	// Метод сравнения записей по имени сотрудника.
	// Нужен для сортировки списка по именам (условие задачи)
	@Override
	public int compareTo(EmployeesItem other) {
		return this.name.compareTo(other.name);
	}
}