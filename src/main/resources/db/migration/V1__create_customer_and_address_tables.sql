CREATE TABLE customers (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    sexo CHAR(1) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    status VARCHAR(255) NOT NULL,
    created_at  timestamptz,
    updated_at timestamptz
)
