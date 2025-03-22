Task 4 

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