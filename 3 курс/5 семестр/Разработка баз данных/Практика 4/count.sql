SELECT
	product_id,
    count,
    COUNT(count) OVER () AS 'Count'
FROM productcount;
