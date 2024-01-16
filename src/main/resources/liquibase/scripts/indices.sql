-- liquibase formatted sql

-- changeset andre:1

CREATE INDEX student2_name_idx ON student (name);
CREATE INDEX faculty2_name_color_idx ON faculty (name, color);