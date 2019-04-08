package com.company.orders.repository.customer.constans;

public interface AddressConstants {

	String SCHEMA = "public";

	String TABLE = "address";

	String SCHEMA_TABLE = SCHEMA + "." + TABLE;

	String PREFIX = TABLE + "_";

	String ID = PREFIX + "id";
	
	String CUSTOMER_ID = PREFIX + "customerid";
	
	String STREET = PREFIX + "street";
	
	String NUMBER = PREFIX + "number";
	
	String COMPLEMENT = PREFIX + "complement";
	
	String ZIP_CODE = PREFIX + "zipcode";
	
	String CITY = PREFIX + "city";
	
	String STATE = PREFIX + "state";
	
	String COUNTRY = PREFIX + "country";
	
	String DISTRICT = PREFIX + "district";
	
	String TYPE = PREFIX + "type";

}