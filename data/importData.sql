--SELECT (POINT(12.34, 0.03)<->POINT(latitude, longitude)) as distance FROM resto
BEGIN;

CREATE TABLE resto_tmp
(
  id int,
  raison_sociale character varying(255),
  mobile character varying(255),
  mail character varying(255),
  latitude double precision,
  longitude double precision,
  type character varying(255),
  categorie character varying(255),
  specialite character varying(255),
  adresse character varying(255),
  code_postale character varying(255),
  commune character varying(255),
  telephone character varying(255),
  internet character varying(255),
  classement character varying(255),
  marque character varying(255),
  tourisme character varying(255),
  adresse_1 character varying(255),
  adresse_2 character varying(255)
);
\COPY resto_tmp(id,raison_sociale,mobile,mail,latitude,longitude,type,categorie,adresse,code_postale,commune,telephone,internet,classement,marque,tourisme,adresse_1,adresse_2)
FROM 'C:\perso\data\resto_33.csv' WITH  CSV DELIMITER ';'  HEADER ENCODING 'LATIN1';
INSERT INTO resto SELECT id,raison_sociale,mobile, 
mail,latitude,longitude,type,categorie,adresse,code_postale,commune,telephone,internet,classement,marque,tourisme FROM resto_tmp;
DROP TABLE resto_tmp;
END;
--COPY resto TO 'C:\perso\data\resto_33_out.csv' WITH  CSV DELIMITER ';'  HEADER;