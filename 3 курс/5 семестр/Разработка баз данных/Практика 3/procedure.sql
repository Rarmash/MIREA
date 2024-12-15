DELIMITER $$

CREATE definer = 'root'@'localhost'
PROCEDURE coffeehouse.BanReview(in date DATE, out text TEXT)
begin
	if (date < "2024-09-10") then
		set text="Banned";
	end if;
end $$

DELIMITER ;