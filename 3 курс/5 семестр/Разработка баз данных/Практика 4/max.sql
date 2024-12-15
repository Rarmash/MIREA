SELECT
	product_id,
    count,
    MAX(count) OVER () AS 'MAX'
FROM productcount;
