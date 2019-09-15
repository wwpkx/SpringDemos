package cn.itcast.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "person")
public class Person {
	// ���¶����id,name�������ԣ����ֶ�
	private Integer id;
	private String name;
	private Date birthday;// 1987-12-10
	private Gender gender = Gender.MAN;
	private String info;
	private Byte[] file;
	private String imagepath;
	
	
	@Transient
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	@Lob @Basic(fetch=FetchType.LAZY)
	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}

	@Lob
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 5, nullable = false)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	// ��������get��set�����������ģ����ݿ����ֶζ�Ӧ�����ƺ�getIdһ�£�
	// ��Ϊd��Сд��������֮ǰ����ĸҲ��Сд����һ����ĸ�Ƿ��Сд�ɵڶ�����ĸ������
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 10, nullable = false, name = "personName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
