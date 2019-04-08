package com.company.orders.entity.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.company.orders.entity.customer.Customer;
import com.company.orders.repository.order.constants.OrderConstants;

@Entity
@Table(schema = OrderConstants.SCHEMA, name = OrderConstants.TABLE)
public class Order {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = OrderConstants.ID)
	private String id;

	@Column(name = OrderConstants.STATUS)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	private OrderStatus status = OrderStatus.CREATED;

	@ManyToOne
	@JoinColumn(name = OrderConstants.CUSTOMER_ID)
	@NotNull(message = "Customer is required")
	private Customer customer;

	@Embedded
	@NotNull(message = "Delivery Address is required")
	private DeliveryAddress deliveryAddress;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull(message = "Itens are required")
	private List<ItemOrder> itens = new ArrayList<>();

	@Column(name = OrderConstants.TOTAL, scale = 2)
	private double ammount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {

		if (status != null && status != this.status) {
			switch (status) {
				case CREATED:
					this.status = status.created(this);
					break;
				case PENDING:
					this.status = status.pending(this);
					break;
				case APPROVED:
					this.status = status.approved(this);
					break;
				default:
					throw new RuntimeException("Invalid status");
			}
			System.out.println("Order#" + id + ": changing status from " + this.status + " to " + status);
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public ItemOrder[] getItens() {
		return this.itens.toArray(new ItemOrder[itens.size()]);
	}

	public void addItem(ItemOrder... itensOrder) {
		for (ItemOrder itemOrder : itensOrder) {
			this.itens.add(itemOrder);
			this.ammount += (itemOrder.getQuantity() * itemOrder.getUnitPrice());
		}
	}

	public void removeItem(ItemOrder... itensOrder) {
		for (ItemOrder itemOrder : itensOrder) {
			this.itens.remove(itemOrder);
			this.ammount -= (itemOrder.getQuantity() * itemOrder.getUnitPrice());
		}
	}

	public double getAmmount() {
		return ammount;
	}

}
