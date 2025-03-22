                                                                                CREATING THE TECHSHOP DBMS SCHEMA
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
                                                                      INSERTING THE VALUES FOR THE TECHSHOP TABLE
                                                                          
1. INSERTING INTO CUSTOMERS TABLE:
insert into customers (customerid, firstname, lastname, email, phone, address) values
(1, 'john', 'doe', 'john.doe@gmail.com', '9876543210', '123 elm street'),
(2, 'jane', 'smith', 'jane.smith@yahoo.com', '8765432109', '456 oak avenue'),
(3, 'michael', 'johnson', 'michael.j@yahoo.com', '7654321098', '789 pine road'),
(4, 'emily', 'davis', 'emily.d@gmail.com', '6543210987', '321 maple lane'),
(5, 'chris', 'brown', 'chris.brown@mail.com', '5432109876', '654 birch drive'),
(6, 'anna', 'taylor', 'anna.t@aol.com', '4321098765', '987 spruce court'),
(7, 'david', 'wilson', 'david@live.com', '3210987654', '111 cedar blvd'),
(8, 'sophia', 'martinez', 'sophia.m@gmail.com', '2109876543', '222 willow way'),
(9, 'james', 'anderson', 'james.a@hotmail.com', '1098765432', '333 chestnut st'),
(10, 'olivia', 'thomas', 'olivia.t@xyz.com', '1987654321', '444 redwood ave');

2. INSERTING INTO PRODUCTS TABLE:
insert into products (ProductID, ProductName, Description, Price) values, 
(1, 'laptop', '15-inch gaming laptop', 1200.50),
(2, 'smartphone', '5g-enabled flagship phone', 899.99),
(3, 'headphones', 'wireless noise-canceling headphones', 199.99),
(4, 'monitor', '27-inch 4k uhd display', 350.00),
(5, 'keyboard', 'mechanical rgb gaming keyboard', 129.99),
(6, 'mouse', 'wireless ergonomic mouse', 79.99),
(7, 'tablet', '10-inch android tablet', 499.99),
(8, 'smartwatch', 'fitness tracking smartwatch', 299.99),
(9, 'printer', 'all-in-one color laser printer', 249.99),
(10, 'external hdd', '2tb portable hard drive', 99.99);

3. INSERTING INTO ORDERS TABLE:
insert into orders (orderid, customerid, orderdate, totalamount) values
(1, 1, '2024-03-01', 1400.49),
(2, 2, '2024-03-02', 599.99),
(3, 3, '2024-03-03', 120.00),
(4, 4, '2024-03-04', 899.99),
(5, 5, '2024-03-05', 250.00),
(6, 6, '2024-03-06', 1300.00),
(7, 7, '2024-03-07', 750.99),
(8, 8, '2024-03-08', 299.99),
(9, 9, '2024-03-09', 125.50),
(10, 10, '2024-03-10', 900.00);

4. INSERTING INTO ORDERDETAILS TABLE:
insert into orderdetails (orderdetailid, orderid, productid, quantity) values
(1, 1, 1, 1),
(2, 1, 3, 2),
(3, 2, 4, 1),
(4, 3, 5, 3),
(5, 4, 2, 1),
(6, 5, 6, 2),
(7, 6, 7, 1),
(8, 7, 8, 1),
(9, 8, 9, 2),
(10, 9, 10, 1);

5. INSERTING INTO INVENTORY TABLE:
insert into inventory (inventoryid, productid, quantityinstock, laststockupdate) values
(1, 1, 50, '2024-03-01'),
(2, 2, 30, '2024-03-02'),
(3, 3, 75, '2024-03-03'),
(4, 4, 40, '2024-03-04'),
(5, 5, 90, '2024-03-05'),
(6, 6, 60, '2024-03-06'),
(7, 7, 20, '2024-03-07'),
(8, 8, 35, '2024-03-08'),
(9, 9, 15, '2024-03-09'),
(10, 10, 100, '2024-03-10');

                                                                                               TASK 2
                                                                                                   
1. select FirstName, LastName, Email from Customers;

2. select o.OrderID, o.OrderDate, c.FirstName, c.LastName from Orders o join Customers c on o.CustomerID = c.CustomerID;

3.insert into customers (customerid, firstname, lastname, email, phone, address) 
values (11, 'alice', 'johnson', 'alice.j@gmail.com', '9876543211', '555 maple street'); 

4. update table Products set price = price+(price*0.10);

5. delete from Orderdetails where OrderId = @order_id;
   delete from Orders where OrderId = @order_id;

6. insert into Customers(CustomerID, FirstName, LastName, Email, Phone, Address) values
(11, 'Jasmine', 'Edward', 'jas@xyz.com', '1987654321', '47 koney ave');
insert into Orders(OrderID, CustomerID, OrderDate, TotalAmount) values
(11, 11, '2022-03-19', 1400.49);

7. update Customers set Email = @email, Phone = @phone, Address= @address where CustomerID = @custmer_id;

8. update Orders set TotalAmount= (select sum(p.Price, od.Quantity) from Products p join OrderDetails od on p.ProductID = od.ProductID; 

9. delete from OrderDetails where OrderID=(select OrderID from OrderDetails where OrderID= @customer_id );
  delete Orders where CustomerID = @customer_id;

10. insert into Products(ProductID, ProductName, Description, Price) values(11, 'gaming console', 'next-gen gaming console', 599.99); 

11. alter table Orders add column Status varchar(10) default = 'pending';
    update table Orders set status = @cust_status where OrderID= @order_id;

12.  alter table Customers add column Ordercount int default 0;
     update Customers c set Ordercount=(select count(*) from Orders o where o.CustomerID = c.Customers);

                                                                                      TASK 3

1.select o.OrderID, c.FirstName, c.LastName, c.CustomerID, c.Email, c.Phone, c.Address, o.OrderDate, o.TotalAmount from Customers c, Orders O join Customers on o.CustomerID = c.CustomerID;

2. Select p.ProductID, p.ProductName, sum(p.Price* od.Quantity) as Totalrevenue from OrderDetails od join Products p on od.ProductID= p.ProductID group by ProductID; 

3. select distinct CustomerID, FirstName, LastName, Email, Phone, Address from Customers c join Orders o on c.CustomerID = o.CustomerID;

4. select p.productname, sum(od.quantity) as total_quantity_ordered
from products p join orderdetails od on p.productid = od.productid group by p.productid, p.productname order by total_quantity_ordered desc
limit 1;

5. select p.ProductID, p.ProductName, i.InventoryID from Products p join Inventory i on p.ProductID = i.ProductID; 

6. select c.FirstName, c.LastName, avg(o.TotalAmount)as avgordervalue from Customers c join Orders o on c.CustomerID = o.CustomerID group by c.CustomerID, c.FirstName, c.LastName; 

7. select o.OrderID, c.FirstName, c.LastName, c.CustomerID, c.Email, c.Address, o.TotalAmount from Customer c join Orders o on c.CustomerID = o.CustomerID groupby o.OrderID order by o.TotalAmount desc limit 1;  

8. select p.ProductID, p.ProductName, count(o.OrderID) as Timesoforder from Products p join Orders o on p.ProductID = o.ProductID group by ProductName order by Timesoforder desc;

9. select c.CustomerID, c.FirstName, c.LastName, c.Email, c.Phone, c.Address from Customers c join Orders o on o.CustomerID = c.CustomerID join OrderDetails od on o.OrderID = od.OrderID
join Products p on od.ProductID = p.ProductID  where p.ProductName = @product_name;

10. select sum(TotalAmount) as TotalRevenue from Orders where OrderDate between @startdate and @enddate;

                                                                                       TASK 4

1. select c.CustomerID, c.FirstName, c.LastName from Customer c join Orders o on c.CustomerID = o.CustomerID where o.OrderID is null;

2. select count(*) as TotalProducts from Products;

3. select sum(TotalAmount) as TotalRevenue from Orders;

4. select avg(Quantity) as avg_quantity from OrderDetails where ProductName ='@productname';

5. select sum(o.TotalAmount) as total_revenue from Orders o join Customers c on o.CustomerID = c.CustomerID where o.CustomerID = '@customerid';

6. select c.FirstName, c.LastName, count(o.OrderID) as NoofOrders from Customers c join Orders o on c.CustomerID = o.CustomerID group by FirstName, LastName order by NoofOrders desc limit 1;

7. select p.ProductID, p.ProductName, p.Price from Products p join OrderDetails od on p.ProductID = od.ProductID where od.Quantity = (select max(Quantity) from OrderDetails);

8. select c.CustomerID, c.FirstName, c.LastName, sum(o.TotalAmount) as Totalrevenue from Customers c join Orders o on c.CustomerID = o.CustomerID groupby c.CustomerID, c.FirstName, c.LastName order by Totalrevenue desc limit 1;

9. select avg(TotalAmount) as avg_order_value from Orders; 

10. select c.FirstName, c.LastName, count(o.OrderID) as countoforders from Customers c join Orders o on c.CustomerID = o.CustomerID group by c.FirstName, c.LastName order by countoforders desc; 
