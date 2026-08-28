ALTER TABLE tb_category
DROP CONSTRAINT IF EXISTS uk_category_name;

CREATE UNIQUE INDEX uk_category_name
    ON tb_category (LOWER(name));