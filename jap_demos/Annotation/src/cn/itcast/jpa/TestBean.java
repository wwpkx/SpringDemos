package cn.itcast.jpa;
@HelloWord
public class TestBean {
	@HelloWord("ÄãºÃ")
	private String name;
	
	public TestBean(String name) {
		this.name = name;
	}


	@Override
	@HelloWord
	public String toString(){
		System.out.println(this.name);
		return this.name;
	}
}
