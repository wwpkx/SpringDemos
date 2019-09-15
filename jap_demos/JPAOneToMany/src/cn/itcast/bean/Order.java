package cn.itcast.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	private String Orderid;
	private Float amount;
	private Set<OrderItem> items=new HashSet<OrderItem>();
	
	@Id @Column(length=12)
	public String getOrderid() {
		return Orderid;
	}
	public void setOrderid(String orderid) {
		Orderid = orderid;
	}
	@Column(nullable=false)
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	//cascade级联
	//出现mappedBy的类是关系被维护端
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}
			,mappedBy="order")
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	public void addOrderItem(OrderItem orderItem){
		orderItem.setOrder(this);
		this.items.add(orderItem);
	}
	
}
