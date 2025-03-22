1. CREATING CUSTOMERS TABLE:
create table customers(
    customerid int primary key,
    firstname varchar(10) not null,
    lastname varchar(10),
    email varchar(50) unique not null,
    phone char(10) not null,
    address varchar(225)
);

2. CREATING PRODUCTS TABLE:
create table products(
    productid int primary key,
    productname varchar(30) not null,
    description varchar(50) not null,
    price decimal(10,2) not null
);

3. CREATING ORDERS TABLE:
create table orders(
    orderid int primary key,
    customerid int not null,
    foreign key (customerid) references customers(customerid),
    orderdate date not null,
    totalamount decimal(10,2) not null
);

4. CREATING ORDERDETAILS TABLE:
create table orderdetails(
    orderdetailid int primary key,
    orderid int not null,
    foreign key (orderid) references orders(orderid),
    productid int not null,
    foreign key (productid) references products(productid),
    quantity int not null
);

5. CREATING INVENTORY TABLE:
create table Inventory(InventoryID int auto_increment primary key,
                       ProductID int auto_increment not null, 
                       foreign key(ProductID) references Products(ProductID), 
                       QuantityInStock varchar(10) not null, 
                       LastStockUpdate varchar(10) not null);
