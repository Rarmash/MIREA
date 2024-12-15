SELECT 
	r.review_id AS review_id,
    r.customer_id AS customer_id,
    c.fullName AS fullName,
    c.phoneNumber AS phoneNumber,
    r.text AS text,
    r.date AS date,
    LAG(r.text) OVER (PARTITION BY c.fullName ORDER BY r.review_id) AS PreviousReviewText
FROM reviews AS r
JOIN customers AS c ON r.customer_id = c.customer_id;
