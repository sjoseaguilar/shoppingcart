CREATE SCHEMA shoppingcart;
use shoppingcart

CREATE TABLE shoppingcart.USERS (
	USER_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50),
    BIO VARCHAR(200),
    EMAIL VARCHAR(200) NOT NULL,
    AREA_OF_INTEREST VARCHAR(300),
    PRIMARY KEY (USER_ID)
);
CREATE TABLE shoppingcart.PRODUCTS(
	PRODUCT_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR (100) NOT NULL,
    PRICE NUMERIC(10) NOT NULL,
    IMAGE LONGBLOB,
    DESCRIPTION VARCHAR(200),
    TOTAL_PRODUCTS_INVENTORY NUMERIC(10) NOT NULL,
    STATUS BOOLEAN NOT NULL,
    PRIMARY KEY (PRODUCT_ID)
);

CREATE TABLE shoppingcart.ORDER_HISTORY(
	ORDER_ID INT NOT NULL,
    ORDER_DATE DATE NOT NULL,
    USER_ID int,
    PRODUCT_ID int,
    PRIMARY KEY (ORDER_ID)
);


ALTER TABLE shoppingcart.ORDER_HISTORY
add FOREIGN KEY (USER_ID) REFERENCES shoppingcart.USERS(USER_ID),
add FOREIGN KEY (PRODUCT_ID) REFERENCES shoppingcart.PRODUCTS(PRODUCT_ID); 

insert into shoppingcart.USERS  values(1, "Sarahi", "Jose", "Yo", "sarahi@hotmail.com", "Coffee");


insert into shoppingcart.PRODUCTS  values(1, "Cafe", 200, NULL, "jd", 19, True )
insert into shoppingcart.PRODUCTS  values(2, "Lapiz", 20, NULL, "jd", 12, True )


insert into shoppingcart.ORDER_HISTORY  values(1, CURRENT_DATE, 1, 1)
insert into shoppingcart.ORDER_HISTORY  values(2, CURRENT_DATE, 1, 2)






