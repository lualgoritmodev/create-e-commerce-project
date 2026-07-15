CREATE TABLE tb_customer (

    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    cpf VARCHAR(11) NOT NULL UNIQUE,

    email VARCHAR(150) NOT NULL UNIQUE,

    birth_date DATE NOT NULL,

    status VARCHAR(20) NOT NULL

);

CREATE TABLE tb_category (
    id UUID PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE tb_address (
    id UUID PRIMARY KEY,
    street VARCHAR(150) NOT NULL,
    number VARCHAR(20) NOT NULL,
    complement VARCHAR(100),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    zip_code VARCHAR(8) NOT NULL,
    customer_id UUID NOT NULL,
    CONSTRAINT fk_address_customer
        FOREIGN KEY (customer_id)
        REFERENCES tb_customer(id)
);

CREATE TABLE tb_product (
    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    description VARCHAR(500) NOT NULL,

    price NUMERIC(10,2) NOT NULL
        CHECK (price > 0),

    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    stock INTEGER NOT NULL
        CHECK (stock >= 0),

    category_id UUID NOT NULL,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES tb_category(id)
);

CREATE TABLE tb_order (

    id UUID PRIMARY KEY,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    total NUMERIC(10,2) NOT NULL
        CHECK (total >= 0),

    order_status VARCHAR(20) NOT NULL,

    customer_id UUID NOT NULL,

    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES tb_customer(id)

);

CREATE TABLE tb_payment (

    id UUID PRIMARY KEY,

    payment_method VARCHAR(30) NOT NULL,

    payment_status VARCHAR(20) NOT NULL,

    value NUMERIC(10,2) NOT NULL
        CHECK (value > 0),

    payment_date TIMESTAMP,

    order_id UUID NOT NULL UNIQUE,

    CONSTRAINT fk_payment_order
        FOREIGN KEY (order_id)
        REFERENCES tb_order(id)

);

CREATE TABLE tb_order_item (

    id UUID PRIMARY KEY,

    quantity INTEGER NOT NULL
        CHECK (quantity > 0),

    unit_price NUMERIC(10,2) NOT NULL
        CHECK (unit_price > 0),

    subtotal NUMERIC(10,2) NOT NULL
        CHECK (subtotal > 0),

    order_id UUID NOT NULL,

    product_id UUID NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES tb_order(id),

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES tb_product(id)

);