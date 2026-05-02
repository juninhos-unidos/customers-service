CREATE TABLE customers (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    sexo CHAR(1) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    status VARCHAR(255) NOT NULL,
    created_at  timestamptz NOT NULL,
    updated_at timestamptz NOT NULL
);


CREATE TABLE address (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(20) NOT NULL,
    complement VARCHAR(100),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code CHAR(8) NOT NULL,
    country VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    customer_id int not null,
    CONSTRAINT  fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers(id)
)
