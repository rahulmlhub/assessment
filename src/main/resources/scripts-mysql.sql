DELIMITER $$
CREATE PROCEDURE GET_MOVIE_DETAILS()
BEGIN
SELECT  distinct FILM.TITLE as 'Movie Name', concat(actor.first_name,' ', actor.last_name) as
 'ACTOR NAME' , LANGUAGE.NAME AS 'AVALIABLE LANGUAGE ' ,category.name  as 'Movies Category'
 FROM FILM  JOIN LANGUAGE ON FILM.LANGUAGE_ID = LANGUAGE.language_id  JOIN FILM_CATEGORY ON
 FILM.FILM_ID = FILM_CATEGORY.FILM_ID  JOIN film_actor on film_actor.actor_id=FILM.film_id
 JOIN actor on actor.actor_id=film_actor.actor_id
JOIN category on category.category_id=FILM_CATEGORY.category_id order by LANGUAGE.NAME;

END;
$$
DELIMITER ;