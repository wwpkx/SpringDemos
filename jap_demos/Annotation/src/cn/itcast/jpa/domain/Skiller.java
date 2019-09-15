package cn.itcast.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Skiller extends Employee{
	private String Skill;

	public String getSkill() {
		return Skill;
	}

	public void setSkill(String skill) {
		Skill = skill;
	}
	
	
}
