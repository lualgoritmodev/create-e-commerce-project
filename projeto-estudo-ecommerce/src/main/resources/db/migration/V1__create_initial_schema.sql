CREATE TABLE tb_customer
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(150) NOT NULL,
    cpf        VARCHAR(11)  NOT NULL,
    email      VARCHAR(150) NOT NULL,
    birth_date DATE         NOT NULL,
    status     VARCHAR(30)  NOT NULL,

    CONSTRAINT uk_customer_cpf UNIQUE (cpf),
    CONSTRAINT uk_customer_email UNIQUE (email)
);


CREATE TABLE tb_address
(
    id           UUID PRIMARY KEY,
    street       VARCHAR(150) NOT NULL,
    number       VARCHAR(20)  NOT NULL,
    complement   VARCHAR(150) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(2)   NOT NULL,
    zip_code     VARCHAR(8)   NOT NULL,
    customer_id  UUID         NOT NULL,

    CONSTRAINT fk_address_customer
        FOREIGN KEY (customer_id)
            REFERENCES tb_customer (id)
);


CREATE TABLE tb_category
(
    id     UUID PRIMARY KEY,
    name   VARCHAR(100) NOT NULL,
    active BOOLEAN      NOT NULL DEFAULT TRUE,

    CONSTRAINT uk_category_name UNIQUE (name)
);


CREATE TABLE tb_product
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(150)  NOT NULL,
    description VARCHAR(500)  NOT NULL,
    price       NUMERIC(12,2) NOT NULL,
    active      BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP     NOT NULL,
    stock       INTEGER       NOT NULL,
    category_id UUID          NOT NULL,

    CONSTRAINT ck_product_price_non_negative
        CHECK (price >= 0),

    CONSTRAINT ck_product_stock_non_negative
        CHECK (stock >= 0),

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES tb_category (id)
);


CREATE TABLE tb_order
(
    id           UUID PRIMARY KEY,
    created_at   TIMESTAMP     NOT NULL,
    total        NUMERIC(12,2) NOT NULL,
    order_status VARCHAR(30)   NOT NULL,
    customer_id  UUID          NOT NULL,

    CONSTRAINT ck_order_total_non_negative
        CHECK (total >= 0),

    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
            REFERENCES tb_customer (id)
);


CREATE TABLE tb_order_item
(
    id         UUID PRIMARY KEY,
    quantity   INTEGER       NOT NULL,
    unit_price NUMERIC(12,2) NOT NULL,
    sub_total  NUMERIC(12,2) NOT NULL,
    order_id   UUID          NOT NULL,
    product_id UUID          NOT NULL,

    CONSTRAINT ck_order_item_quantity_positive
        CHECK (quantity > 0),

    CONSTRAINT ck_order_item_unit_price_non_negative
        CHECK (unit_price >= 0),

    CONSTRAINT ck_order_item_subtotal_non_negative
        CHECK (sub_total >= 0),

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
            REFERENCES tb_order (id),

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
            REFERENCES tb_product (id)
);


CREATE TABLE tb_payment
(
    id             UUID PRIMARY KEY,
    payment_method VARCHAR(30)   NOT NULL,
    payment_status VARCHAR(30)   NOT NULL,
    value          NUMERIC(12,2) NOT NULL,
    payment_date   TIMESTAMP     NOT NULL,
    order_id       UUID          NOT NULL,

    CONSTRAINT ck_payment_value_non_negative
        CHECK (value >= 0),

    CONSTRAINT uk_payment_order UNIQUE (order_id),

    CONSTRAINT fk_payment_order
        FOREIGN KEY (order_id)
            REFERENCES tb_order (id)
);