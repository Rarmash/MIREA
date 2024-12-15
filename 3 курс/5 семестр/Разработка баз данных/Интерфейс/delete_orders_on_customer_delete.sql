DELIMITER $$

CREATE TRIGGER delete_orders_on_customer_delete
BEFORE DELETE ON coffeehouse.customers
FOR EACH ROW
BEGIN
    DELETE FROM coffeehouse.orders WHERE customer_id = OLD.customer_id;
    DELETE FROM coffeehouse.ordersproducts WHERE order_id IN (SELECT order_id FROM coffeehouse.orders WHERE customer_id = OLD.customer_id);
END; $$

DELIMITER ;