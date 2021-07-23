package com.javaex.vo;

public class PersonVo {

	// 필드
	private int person_id;
	private String name;
	private String hp;
	private String company;

	// 생성자
	public PersonVo() {

	}

	public PersonVo(int person_id) {
		this.person_id = person_id;
	}

	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PersonVo(int person_id, String name, String hp, String company) {
		this.person_id = person_id;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	// 메소드 - GS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	// 메소드 - 일반
	@Override
	public String toString() {
		return "PersonVo [person_id=" + person_id + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
}
