ALTER TABLE IF EXISTS public.sections  ADD COLUMN id_file uuid;

CREATE TABLE IF NOT EXISTS public.files_storage (
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    created_date time with time zone NOT NULL DEFAULT now(),
    author character varying COLLATE pg_catalog."default",
    context bytea,
    size bigint,
    file_name character varying,
    CONSTRAINT files_storage_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.files_storage OWNER to postgres;

ALTER TABLE public.sections
    ADD CONSTRAINT foreign_key01
        FOREIGN KEY (id_file)
            REFERENCES public.files_storage(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;