SELECT
	product_id,
    count,
    MIN(count) OVER () AS 'MIN'
FROM productcount;
