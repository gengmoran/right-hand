package com.misterfat.righthand.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Coder implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ApiModelProperty("id")
	private Integer id;

	@NotNull
	@ApiModelProperty("name")
	private String name;

	@NotNull
	@ApiModelProperty("sex")
	private String sex;

	@ApiModelProperty("age")
	private Integer age;

	@ApiModelProperty("address")
	private String address;

	@ApiModelProperty("phone")
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Coder [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", address=" + address
				+ ", phone=" + phone + "]";
	}

}
