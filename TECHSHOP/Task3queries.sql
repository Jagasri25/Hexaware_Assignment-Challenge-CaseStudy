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