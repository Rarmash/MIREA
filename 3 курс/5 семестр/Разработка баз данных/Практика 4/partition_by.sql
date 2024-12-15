SELECT
	product_id,
    count,
    SUM(count) OVER (PARTITION BY count) AS 'Sum'
FROM productcount;
