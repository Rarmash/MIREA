delimiter $$

CREATE TRIGGER coffeehouse.trigger1
	BEFORE INSERT ON coffeehouse.orders
    FOR EACH ROW
BEGIN
	if new.status is null or new.status = '' then
		set new.status='Pending';
	end if;
END $$

delimiter ;