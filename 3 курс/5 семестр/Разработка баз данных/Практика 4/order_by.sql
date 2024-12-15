SELECT
	product_id,
    count,
    SUM(count) OVER (PARTITION BY count ORDER BY product_id ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING) AS 'Sum'
FROM productcount;
