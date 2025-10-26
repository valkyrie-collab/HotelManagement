DROP TABLE hotel;
DROP TABLE hotel_image;
DROP TABLE rate;
DROP TABLE room;
DROP TABLE room_image;

SELECT * FROM hotel;
SELECT * FROM hotel_image;
SELECT * FROM room;
SELECT * FROM room_image;

TRUNCATE TABLE room CASCADE;
TRUNCATE TABLE room_image;

UPDATE room SET room_number = 102 WHERE id = 2;

-- DELETE FROM room WHERE room_number = 209 AND hotel_id = 'ght-ieib';