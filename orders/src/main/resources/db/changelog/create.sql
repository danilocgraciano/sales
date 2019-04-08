--liquibase formatted sql

--changeset danilocgraciano:1
CREATE TABLE public.customer(
  customer_id TEXT NOT NULL,
  customer_name TEXT NOT NULL,
  customer_identity TEXT NOT NULL,
  customer_identitytype TEXT NOT NULL,
  customer_email TEXT NOT NULL,
  customer_birthdate DATE,
  customer_gender TEXT,
  CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.customer

--changeset danilocgraciano:2
CREATE TABLE public.address
(
  address_id TEXT NOT NULL,
  address_city TEXT,
  address_complement TEXT,
  address_country TEXT,
  address_district TEXT,
  address_number TEXT,
  address_state TEXT,
  address_street TEXT,
  address_zipcode TEXT,
  address_customerid TEXT NOT NULL,
  address_type TEXT NOT NULL,
  CONSTRAINT address_pkey PRIMARY KEY (address_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.address

--changeset danilocgraciano:3
ALTER TABLE public.address ADD CONSTRAINT address_customerid_fk FOREIGN KEY (address_customerid) REFERENCES public.customer (customer_id) ON UPDATE NO ACTION ON DELETE CASCADE;
--preconditions onFail:HALT onError:HALT
--preconditions not foreignKeyConstraintExists:customer_addressid_fk

--changeset danilocgraciano:4
CREATE TABLE public.unit(
  unit_id TEXT NOT NULL,
  unit_description TEXT NOT NULL,
  CONSTRAINT unit_pkey PRIMARY KEY (unit_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.unit

--changeset danilocgraciano:5
CREATE TABLE public.item(
  item_sku TEXT NOT NULL,
  item_name TEXT NOT NULL,
  item_unitid TEXT NOT NULL,
  item_unitprice real,
  CONSTRAINT item_pkey PRIMARY KEY (item_sku)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.item

--changeset danilocgraciano:6
ALTER TABLE public.item ADD CONSTRAINT item_unitid_fk FOREIGN KEY (item_unitid) REFERENCES public.unit (unit_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
--preconditions onFail:HALT onError:HALT
--preconditions not foreignKeyConstraintExists:item_unitid_fk

--changeset danilocgraciano:7
CREATE TABLE public.order(
  order_id TEXT NOT NULL,
  order_status TEXT NOT NULL,
  order_customerid TEXT NOT NULL,
  order_deliveryaddressstreet TEXT NOT NULL,
  order_deliveryaddressnumber TEXT NOT NULL,
  order_deliveryaddresscomplement TEXT,
  order_deliveryaddresszipcode TEXT NOT NULL,
  order_deliveryaddresscity TEXT NOT NULL,
  order_deliveryaddressstate TEXT NOT NULL,
  order_deliveryaddresscountry TEXT NOT NULL,
  order_deliveryaddressdistrict TEXT NOT NULL,
  order_ammount REAL NOT NULL,
  CONSTRAINT order_pkey PRIMARY KEY (order_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.order

--changeset danilocgraciano:8
CREATE TABLE public.itemorder
(
  itemorder_id TEXT NOT NULL,
  itemorder_orderid TEXT NOT NULL,
  itemorder_itemsku TEXT NOT NULL,
  itemorder_quantity REAL NOT NULL,
  itemorder_unitprice REAL NOT NULL,
  CONSTRAINT itemorder_pkey PRIMARY KEY (itemorder_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.itemorder

--changeset danilocgraciano:9
ALTER TABLE public.itemorder ADD CONSTRAINT itemorder_orderid_fk FOREIGN KEY (itemorder_orderid) REFERENCES public.order (order_id) ON UPDATE NO ACTION ON DELETE CASCADE;
--preconditions onFail:HALT onError:HALT
--preconditions not foreignKeyConstraintExists:itemorder_orderid_fk