CREATE TABLE IF NOT EXISTS public.sections
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(), CONSTRAINT sections_pkey PRIMARY KEY (id),
    section_name varchar

    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sections OWNER to postgres;


CREATE TABLE IF NOT EXISTS  public.classes
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(), CONSTRAINT classes_pkey PRIMARY KEY (id),
    parent_id uuid,
    section_name    varchar,
    code      varchar,
    CONSTRAINT foreign_key01 FOREIGN KEY (parent_id)
    REFERENCES public.sections (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.classes OWNER TO postgres;
