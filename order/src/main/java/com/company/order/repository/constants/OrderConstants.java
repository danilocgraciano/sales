package com.company.order.repository.constants;

public interface OrderConstants {

	String SCHEMA = "public";

	String TABLE = "order";

	String SCHEMA_TABLE = SCHEMA + "." + TABLE;

	String PREFIX = TABLE + "_";

	String ID = PREFIX + "id";

	String STATUS = PREFIX + "status";

	String CUSTOMER_ID = PREFIX + "customerid";

	String CUSTOMER_NAME = PREFIX + "customername";

	String CUSTOMER_IDENTITY = PREFIX + "customeridentity";

	String CUSTOMER_IDENTITY_TYPE = PREFIX + "customeridentitytype";

	String CUSTOMER_EMAIL = PREFIX + "customeremail";

	String CUSTOMER_GENDER = PREFIX + "customergender";

	String DELIVERY_ADDRESS_STREET = PREFIX + "deliveryaddressstreet";

	String DELIVERY_ADDRESS_NUMBER = PREFIX + "deliveryaddressnumber";

	String DELIVERY_ADDRESS_COMPLEMENT = PREFIX + "deliveryaddresscomplement";

	String DELIVERY_ADDRESS_ZIP_CODE = PREFIX + "deliveryaddresszipcode";

	String DELIVERY_ADDRESS_CITY = PREFIX + "deliveryaddresscity";

	String DELIVERY_ADDRESS_STATE = PREFIX + "deliveryaddressstate";

	String DELIVERY_ADDRESS_COUNTRY = PREFIX + "deliveryaddresscountry";

	String DELIVERY_ADDRESS_DISTRICT = PREFIX + "deliveryaddressdistrict";

	String TOTAL = PREFIX + "ammount";

}
