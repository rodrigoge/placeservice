CREATE TABLE IF NOT EXISTS public.place
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) NOT NULL,
    slug character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,

    CONSTRAINT place_pkey PRIMARY KEY (id)
);
