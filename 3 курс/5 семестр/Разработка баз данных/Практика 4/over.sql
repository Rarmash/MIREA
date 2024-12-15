SELECT
	product_id,
    count,
    AVG(count) OVER () AS 'Avg'
FROM productcount;
