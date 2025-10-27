SELECT * FROM reservation;
DROP TABLE reservation;

SELECT CASE WHEN EXISTS (SELECT 1 FROM reservation WHERE reservation_id = '41f0da55-0bf5-4320-96fb-a5a557fda970') THEN TRUE ELSE FALSE END;
SELECT status FROM reservation WHERE reservation_id = '41f0da55-0bf5-4320-96fb-a5a557fda970';