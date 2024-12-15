delimiter $$

create function IsBanned(customer_id INT) RETURNS BOOL
deterministic
begin
	return exists (Select 1 from reviews where reviews.customer_id=customer_id AND reviews.text="Banned");
end

$$