package com.company.order.repository.constants;

public interface ItemOrderConstants {

	String SCHEMA = "public";

	String TABLE = "itemorder";

	String SCHEMA_TABLE = SCHEMA + "." + TABLE;

	String PREFIX = TABLE + "_";

	String ID = PREFIX + "id";
	
	String ORDER_ID = PREFIX + "orderid";

	String ITEM_SKU = PREFIX + "itemsku";

	String ITEM_NAME = PREFIX + "itemname";

	String UNIT_ID = PREFIX + "itemunitid";

	String UNIT_DESCRIPTION = PREFIX + "itemunitdescription";

	String QUANTITY = PREFIX + "quantity";

	String UNIT_PRICE = PREFIX + "unitprice";
	
	String TOTAL = PREFIX + "total";

}
