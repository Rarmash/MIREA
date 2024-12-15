SELECT
	product_id,
    count,
    DENSE_RANK() OVER (ORDER BY count ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING) AS DenseRank
FROM productcount;
