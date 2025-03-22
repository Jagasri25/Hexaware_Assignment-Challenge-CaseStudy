INSERTING INTO CUSTOMERS TABLE:
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

INSERTING INTO PRODUCTS TABLE:
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

INSERTING INTO ORDERS TABLE:
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

INSERTING INTO ORDERDETAILS TABLE:
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

INSERTING INTO INVENTORY TABLE:
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
