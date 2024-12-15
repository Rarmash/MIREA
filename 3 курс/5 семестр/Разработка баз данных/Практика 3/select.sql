select customer_id, fullname from customers;
select * from reviews where date>="2024-09-10";
select order_id, count from ordersproducts, productcount 
	where ordersproducts.quantity=1 AND ordersproducts.product_id>=2 AND ordersproducts.product_id=productcount.product_id;
select order_id, count from ordersproducts inner join productcount
	on ordersproducts.product_id=productcount.product_id
	where (productcount.count=50 or productcount.count=30);
select product_id from ordersproducts intersect select product_id from productcount;
select product_id from productcount except select product_id from ordersproducts;
select order_id, date, status from orders where order_id between 1 and 2;
select * from orders order by date;
select employees.fullName from employees where not exists (
    select o1.order_id from Orders as o1 where o1.customer_id = 1 and not exists (
		select 1 from Orders AS o2 where o2.employee_id = Employees.employee_id and o2.order_id = o1.order_id
      )
);
