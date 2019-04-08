package com.company.orders.repository.item.constants;

public interface ItemConstants {

	String SCHEMA = "public";

	String TABLE = "item";

	String SCHEMA_TABLE = SCHEMA + "." + TABLE;

	String PREFIX = TABLE + "_";

	String SKU = PREFIX + "sku";

	String NAME = PREFIX + "name";

	String UNIT_ID = PREFIX + "unitid";

	String UNIT_PRICE = PREFIX + "unitprice";

}
