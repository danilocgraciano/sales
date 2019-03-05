--liquibase formatted sql

--changeset danilocgraciano:1
CREATE TABLE public.order(
  order_id TEXT NOT NULL,
  order_status TEXT NOT NULL,
  order_customerid TEXT NOT NULL,
  order_customername TEXT NOT NULL,
  order_customeridentity TEXT NOT NULL,
  order_customeridentitytype TEXT NOT NULL,
  order_customeremail TEXT NOT NULL,
  order_customergender TEXT NOT NULL,
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

--changeset danilocgraciano:2
CREATE TABLE public.itemorder
(
  itemorder_id TEXT NOT NULL,
  itemorder_orderid TEXT NOT NULL,
  itemorder_itemsku TEXT NOT NULL,
  itemorder_itemname TEXT NOT NULL,
  itemorder_itemunitid TEXT NOT NULL,
  itemorder_itemunitdescription TEXT NOT NULL,
  itemorder_quantity REAL NOT NULL,
  itemorder_unitprice REAL NOT NULL,
  CONSTRAINT itemorder_pkey PRIMARY KEY (itemorder_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.itemorder

--changeset danilocgraciano:3
ALTER TABLE public.itemorder ADD CONSTRAINT itemorder_orderid_fk FOREIGN KEY (itemorder_orderid) REFERENCES public.order (order_id) ON UPDATE NO ACTION ON DELETE CASCADE;
--preconditions onFail:HALT onError:HALT
--preconditions not foreignKeyConstraintExists:itemorder_orderid_fk