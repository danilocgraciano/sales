package com.company.orders.repository.order.constants;

public interface ItemOrderConstants {

	String SCHEMA = "public";

	String TABLE = "itemorder";

	String SCHEMA_TABLE = SCHEMA + "." + TABLE;

	String PREFIX = TABLE + "_";

	String ID = PREFIX + "id";

	String ORDER_ID = PREFIX + "orderid";

	String ITEM_SKU = PREFIX + "itemsku";

	String QUANTITY = PREFIX + "quantity";

	String UNIT_PRICE = PREFIX + "unitprice";

}
