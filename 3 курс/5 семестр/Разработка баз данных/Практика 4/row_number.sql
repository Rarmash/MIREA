SELECT
	product_id,
    count,
    ROW_NUMBER() OVER (PARTITION BY count ORDER BY product_id ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING) AS 'RowNumber'
FROM productcount;
