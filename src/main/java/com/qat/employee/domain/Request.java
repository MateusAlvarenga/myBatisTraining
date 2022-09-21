package com.qat.employee.domain;

import java.util.List;
import java.util.Objects;

public abstract class Request<T, ID, O> {

	private ID id;
	private T data;
	private List<T> dataList;

	public ID getId() {
		return id;
	}

	public T getData() {
		return data;
	}
	
	public List<T> getDataList() {
		return dataList;
	}

	public O withId(ID id) {
		setId(id);
		return (O) this;
	}

	public O withData(T data) {
		setData(data);
		return (O) this;
	}

	public O withDataList(List<T> dataList) {
		setDataList(dataList);
		return (O) this;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, dataList, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(data, other.data) && Objects.equals(dataList, other.dataList)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", data=" + data + ", dataList=" + dataList + "]";
	}

}
