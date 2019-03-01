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