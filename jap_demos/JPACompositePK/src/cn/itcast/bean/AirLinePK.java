package cn.itcast.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * 
 * 建立联合主键类有三个要求：1.实现一个无参的构造函数
 * 					2.实现序列化接口
 * 					3.重写equal和hashcode两个方法
 *
 */
@Embeddable//告诉JPA，我们只是使用联合主键类里面的属性作为实体类的持久化字段
public class AirLinePK implements Serializable {
	private String startCity;
	private String endCity;
	
	
	public AirLinePK() {}
	public AirLinePK(String startCity, String endCity) {
		this.startCity = startCity;
		this.endCity = endCity;
	}
	@Column(length=3)
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	@Column(length=3)
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
		result = prime * result
				+ ((startCity == null) ? 0 : startCity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirLinePK other = (AirLinePK) obj;
		if (endCity == null) {
			if (other.endCity != null)
				return false;
		} else if (!endCity.equals(other.endCity))
			return false;
		if (startCity == null) {
			if (other.startCity != null)
				return false;
		} else if (!startCity.equals(other.startCity))
			return false;
		return true;
	}
	
	
}
