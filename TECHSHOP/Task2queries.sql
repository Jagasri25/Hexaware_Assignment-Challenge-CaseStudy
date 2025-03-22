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