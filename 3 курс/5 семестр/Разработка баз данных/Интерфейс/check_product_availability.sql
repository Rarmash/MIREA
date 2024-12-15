DELIMITER $$

CREATE TRIGGER check_product_availability_before_order_insert
BEFORE INSERT ON coffeehouse.ordersproducts
FOR EACH ROW
BEGIN
    DECLARE available_count INT;
    SELECT count INTO available_count FROM coffeehouse.productcount WHERE product_id = NEW.product_id;
    IF available_count < NEW.quantity THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Недостаточно товара на складе';
    END IF;
END; $$

DELIMITER ;