package com.capstore.app.models;

public class LocalCart {

	private int quantity;
	private Product pid;
	private int cid;
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getPid() {
		return pid;
	}

	public void setPid(Product pid) {
		this.pid = pid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public LocalCart(int quantity, Product pid, int cid) {
		super();
		this.quantity = quantity;
		this.pid = pid;
		this.cid = cid;
	}

	public LocalCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LocalCart [quantity=" + quantity + ", pid=" + pid + ", cid=" + cid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + quantity;
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
		LocalCart other = (LocalCart) obj;
		if (cid != other.cid)
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}



}
