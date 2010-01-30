package com.kirhgoff.zephyr;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DataSet {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String encodedKey;

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

	public void setEncodedKey(String encodedKey) {
		this.encodedKey = encodedKey;
	}

	public String getEncodedKey() {
		return encodedKey;
	}
}