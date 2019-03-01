package com.company.customer.repository.constants;

public interface CustomerConstants {
	
	String SCHEMA = "public";
	
	String TABLE = "customer";
	
	String SCHEMA_TABLE = SCHEMA + "." + TABLE;
	
	String PREFIX = TABLE + "_";
	
	String ID = PREFIX + "id";
	
	String NAME = PREFIX + "name";
	
	String IDENTITY = PREFIX + "identity";
	
	String IDENTITY_TYPE = PREFIX + "identitytype";
	
	String EMAIL = PREFIX + "email";
	
	String BIRTH_DATE = PREFIX + "birthdate";
	
	String GENDER = PREFIX + "gender";
	
	String ADDRESS_ID = PREFIX + "addressid";
	
	String DELIVERY_ADDRESS_ID = PREFIX + "deliveryaddressid";
	
	String BILLING_ADDRESS_ID = PREFIX + "billingaddressid";

}