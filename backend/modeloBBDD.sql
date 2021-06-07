-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP SEQUENCE public.absence_id_absence_seq;

CREATE SEQUENCE public.absence_id_absence_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.company_id_company_seq;

CREATE SEQUENCE public.company_id_company_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.dailyentry_id_daily_seq;

CREATE SEQUENCE public.dailyentry_id_daily_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.employee_cod_user_seq;

CREATE SEQUENCE public.employee_cod_user_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.role_id_role_seq;

CREATE SEQUENCE public.role_id_role_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.absence definition

-- Drop table

-- DROP TABLE public.absence;

CREATE TABLE public.absence (
	id_absence bigserial NOT NULL,
	cod_user int4 NULL,
	end_time int8 NULL,
	id_company int4 NULL,
	start_time int8 NULL,
	"type" int4 NULL,
	validated bool NULL,
	CONSTRAINT absence_pkey PRIMARY KEY (id_absence)
);


-- public.company definition

-- Drop table

-- DROP TABLE public.company;

CREATE TABLE public.company (
	id_company bigserial NOT NULL,
	city varchar(255) NULL,
	country varchar(255) NULL,
	"name" varchar(255) NULL,
	phone_number varchar(255) NULL,
	postal_code int4 NULL,
	street varchar(255) NULL,
	CONSTRAINT company_pkey PRIMARY KEY (id_company)
);


-- public.dailyentry definition

-- Drop table

-- DROP TABLE public.dailyentry;

CREATE TABLE public.dailyentry (
	id_daily bigserial NOT NULL,
	cod_user int4 NULL,
	date_entry date NULL,
	departure_time int8 NULL,
	entry_time int8 NULL,
	CONSTRAINT dailyentry_pkey PRIMARY KEY (id_daily)
);


-- public."role" definition

-- Drop table

-- DROP TABLE public."role";

CREATE TABLE public."role" (
	id_role bigserial NOT NULL,
	type_role varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id_role)
);


-- public.employee definition

-- Drop table

-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	cod_user bigserial NOT NULL,
	email varchar(255) NULL,
	holidays int4 NULL,
	id_company int4 NULL,
	irpf float8 NULL,
	"name" varchar(255) NULL,
	"password" varchar(60) NULL,
	number_of_payments float8 NULL,
	phone_number varchar(255) NULL,
	salary_year float8 NULL,
	username varchar(255) NULL,
	weekly_hours float8 NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (cod_user),
	CONSTRAINT uk_fopic1oh5oln2khj8eat6ino0 UNIQUE (email),
	CONSTRAINT uk_im8flsuftl52etbhgnr62d6wh UNIQUE (username),
	CONSTRAINT ukg9a1u4wt3ti2nw64uww659w3t UNIQUE (id_company, cod_user),
	CONSTRAINT fk2wffng8gf187ewpivekscbkqg FOREIGN KEY (cod_user) REFERENCES public.employee(cod_user),
	CONSTRAINT fkpd642e1ugf41cwns6tft1wxum FOREIGN KEY (id_company) REFERENCES public.company(id_company)
);


-- public.user_role definition

-- Drop table

-- DROP TABLE public.user_role;

CREATE TABLE public.user_role (
	cod_user int8 NOT NULL,
	id_role int8 NOT NULL,
	CONSTRAINT uke97q6chn0eddi4aheouvenko9 UNIQUE (cod_user, id_role),
	CONSTRAINT fk2aam9nt2tv8vcfymi3jo9c314 FOREIGN KEY (id_role) REFERENCES public."role"(id_role),
	CONSTRAINT fkfxn3hb5opusiyjcj26i3y6tov FOREIGN KEY (cod_user) REFERENCES public.employee(cod_user)
);
