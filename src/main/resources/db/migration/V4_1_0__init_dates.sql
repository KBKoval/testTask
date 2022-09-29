INSERT INTO public.sections (section_name) VALUES ('Section 1');

INSERT INTO public.classes ( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 1'), 'Geo Class 11','GC11');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 1'), 'Geo Class 12','GC12');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 1'), 'Geo Class 1M','GC1M');

INSERT INTO public.sections
(section_name)
VALUES ('Section 2');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 2'), 'Geo Class 21','GC21');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 2'), 'Geo Class 22','GC22');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 2'), 'Geo Class 2M','GC2M');


INSERT INTO public.sections
(section_name)
VALUES ('Section 3');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 3'), 'Geo Class 31','GC31');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 3'), 'Geo Class 32','GC32');

INSERT INTO public.classes
( parent_id, section_name, code)
VALUES
    ( (select id from public.sections where section_name = 'Section 3'), 'Geo Class 3M','GC3M');

