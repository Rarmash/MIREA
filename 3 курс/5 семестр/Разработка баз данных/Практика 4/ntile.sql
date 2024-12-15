SELECT
	product_id,
    count,
    NTILE(4) OVER (ORDER BY count DESC) AS CountGroup
FROM productcount;
