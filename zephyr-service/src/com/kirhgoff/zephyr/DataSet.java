package com.kirhgoff.zephyr;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DataSet {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String key;

    @Persistent
	private String id;

	@Persistent
	private String dataType;

	@Persistent
	private String data;

	public DataSet(String id, String dataType, String data) {
		this.id = id;
		this.dataType = dataType;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getDataType() {
		return dataType;
	}

	public String getData() {
		return data;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder ();
		sb.append ("id=" + id).append(", ");
		sb.append ("dataType=" + dataType).append(", ");
		sb.append ("data=" + data);
		return sb.toString();
	}
}