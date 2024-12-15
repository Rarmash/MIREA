DELIMITER $$

CREATE TRIGGER check_review_length_before_insert
BEFORE INSERT ON coffeehouse.reviews
FOR EACH ROW
BEGIN
    IF LENGTH(NEW.text) > 500 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Отзыв слишком длинный. Максимальная длина отзыва - 500 символов';
    END IF;
END; $$

DELIMITER ;