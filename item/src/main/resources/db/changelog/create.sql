--liquibase formatted sql

--changeset danilocgraciano:1
CREATE TABLE public.unit(
  unit_id TEXT NOT NULL,
  unit_description TEXT NOT NULL,
  CONSTRAINT unit_pkey PRIMARY KEY (unit_id)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.unit

--changeset danilocgraciano:2
CREATE TABLE public.item(
  item_sku TEXT NOT NULL,
  item_name TEXT NOT NULL,
  item_unitid TEXT NOT NULL,
  item_unitprice real,
  CONSTRAINT item_pkey PRIMARY KEY (item_sku)
);
--preconditions onFail:HALT onError:HALT
--preconditions not tableExists:public.item

--changeset danilocgraciano:3
ALTER TABLE public.item ADD CONSTRAINT item_unitid_fk FOREIGN KEY (item_unitid) REFERENCES public.unit (unit_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
--preconditions onFail:HALT onError:HALT
--preconditions not foreignKeyConstraintExists:item_unitid_fk