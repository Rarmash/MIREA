SELECT
	product_id,
    count,
    RANK() OVER (ORDER BY count ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING) AS CountRank
FROM productcount;
