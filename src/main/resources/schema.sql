CREATE TABLE IF NOT EXISTS PRICES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID BIGINT NOT NULL,
    START_DATE DATETIME NOT NULL,
    END_DATE DATETIME NOT NULL,
    PRICE_LIST INT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    PRIORITY BIGINT NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURR VARCHAR(3) NOT NULL
);
CREATE TABLE IF NOT EXISTS "role" (
    name VARCHAR(255) NOT NULL PRIMARY KEY
);
CREATE TABLE IF NOT EXISTS "app_user" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) UNIQUE,
    enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS "user_roles" (
    user_id BIGINT NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role_name)
);