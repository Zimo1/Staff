package ru.yodata.staff.model.dto;

import com.google.gson.annotations.SerializedName;

// Корневой класс данных компании. Сгенерирован автоматически по образцу REST-ответа сервера
public class Staff{

	@SerializedName("company")
	private Company company;

	public void setCompany(Company company){
		this.company = company;
	}

	public Company getCompany(){
		return company;
	}
}