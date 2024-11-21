-- Database: Luxmed


-- DROP TABLE IF EXISTS public.companies;

CREATE TABLE IF NOT EXISTS public.companies
(
company_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
name text COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT company_pkey PRIMARY KEY (company_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.companies
OWNER to postgres;

-- Table: public.departments

-- DROP TABLE IF EXISTS public.departments;

CREATE TABLE IF NOT EXISTS public.departments
(
department_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
name text COLLATE pg_catalog."default" NOT NULL,
company_id integer NOT NULL,
CONSTRAINT departments_pkey PRIMARY KEY (department_id),
CONSTRAINT departments_company_id_fkey FOREIGN KEY (company_id)
    REFERENCES public.companies (company_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.departments
OWNER to postgres;

-- Table: public.managers

-- DROP TABLE IF EXISTS public.managers;

CREATE TABLE IF NOT EXISTS public.managers
(
manager_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
name text COLLATE pg_catalog."default" NOT NULL,
email character varying(1000) COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT manager_pkey PRIMARY KEY (manager_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.managers
OWNER to postgres;



-- Table: public.projects

-- DROP TABLE IF EXISTS public.projects;

CREATE TABLE IF NOT EXISTS public.projects
(
project_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
name text COLLATE pg_catalog."default" NOT NULL,
manager_id integer,
CONSTRAINT projects_pkey PRIMARY KEY (project_id),
CONSTRAINT managers_manager_id FOREIGN KEY (manager_id)
    REFERENCES public.managers (manager_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.projects
OWNER to postgres;



-- Table: public.teams

-- DROP TABLE IF EXISTS public.teams;

CREATE TABLE IF NOT EXISTS public.teams
(
team_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
name text COLLATE pg_catalog."default" NOT NULL,
department_id integer NOT NULL,
project_id integer,
CONSTRAINT teams_pkey PRIMARY KEY (team_id),
CONSTRAINT departments_department_id FOREIGN KEY (department_id)
    REFERENCES public.departments (department_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
CONSTRAINT teams_project_id_fkey FOREIGN KEY (project_id)
    REFERENCES public.projects (project_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.teams
OWNER to postgres;
-- Index: teams_project_id_idx

-- DROP INDEX IF EXISTS public.teams_project_id_idx;

CREATE UNIQUE INDEX IF NOT EXISTS teams_project_id_idx
ON public.teams USING btree
(project_id ASC NULLS LAST)
TABLESPACE pg_default;


--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-21 20:36:17
--
-- TOC entry 3391 (class 0 OID 16386)
-- Dependencies: 218
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (1, 'New company 1');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (2, 'New company 2');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (3, 'Luxmed company 2');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (4, 'One more company');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (5, 'Sales improvements');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (6, 'Sales improvements');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (7, 'Sales improvements');
INSERT INTO public.companies OVERRIDING SYSTEM VALUE VALUES (8, 'Company LTD');


--
-- TOC entry 3393 (class 0 OID 16394)
-- Dependencies: 220
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (1, 'Some department', 1);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (2, 'One more department', 1);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (3, 'Very importan department', 1);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (4, 'HR Ddepartment', 2);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (5, 'HR Ddepartment', 3);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (6, 'IT Ddepartment', 3);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (7, 'Tech Ddepartment', 2);
INSERT INTO public.departments OVERRIDING SYSTEM VALUE VALUES (8, 'Sales Ddepartment', 2);


--
-- TOC entry 3395 (class 0 OID 16407)
-- Dependencies: 222
-- Data for Name: managers; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3397 (class 0 OID 16415)
-- Dependencies: 224
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.projects OVERRIDING SYSTEM VALUE VALUES (1, 'New application project', NULL);
INSERT INTO public.projects OVERRIDING SYSTEM VALUE VALUES (2, 'Migration to cloud', NULL);
INSERT INTO public.projects OVERRIDING SYSTEM VALUE VALUES (3, 'Migration to Azure', NULL);
INSERT INTO public.projects OVERRIDING SYSTEM VALUE VALUES (4, 'Migration to AWS', NULL);
INSERT INTO public.projects OVERRIDING SYSTEM VALUE VALUES (5, 'Sales improvements', NULL);


--
-- TOC entry 3399 (class 0 OID 16428)
-- Dependencies: 226
-- Data for Name: teams; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (2, 'Legal team', 1, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (3, 'Some next team', 1, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (4, 'And one more team', 1, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (5, 'Nice name team', 2, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (6, 'Dummy team', 2, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (7, 'Just one team', 3, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (8, 'Network and opeartions team', 6, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (9, 'Developmet team', 6, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (10, 'Change manage team', 6, NULL);
INSERT INTO public.teams OVERRIDING SYSTEM VALUE VALUES (1, 'Internal platforms team', 1, NULL);


--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 217
-- Name: companies_company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.companies_company_id_seq', 8, true);


--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 219
-- Name: departments_department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.departments_department_id_seq', 8, true);


--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 221
-- Name: managers_manager_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.managers_manager_id_seq', 1, false);


--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 223
-- Name: projects_project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.projects_project_id_seq', 5, true);


--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 225
-- Name: teams_team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.teams_team_id_seq', 10, true);


-- Completed on 2024-11-21 20:36:17

--
-- PostgreSQL database dump complete
--

