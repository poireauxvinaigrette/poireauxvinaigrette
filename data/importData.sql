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
  twitter character varying(255),
  adresse_1 character varying(255),
  adresse_2 character varying(255)
);
\COPY resto_tmp(id,raison_sociale,mobile,mail,latitude,longitude,type,categorie,adresse,code_postale,commune,telephone,internet,classement,marque,twitter,adresse_1,adresse_2) FROM 'resto_33.csv' WITH  CSV DELIMITER ';'  HEADER ENCODING 'LATIN1';

--INSERT INTO resto SELECT id,raison_sociale,mobile,mail,latitude,longitude,type,categorie,adresse,code_postale,commune,telephone,internet,classement,tourisme,twitter FROM resto_tmp;
 
DROP TABLE resto_tmp;
END;
--COPY resto TO 'C:\perso\data\resto_33_out.csv' WITH  CSV DELIMITER ';'  HEADER;

-- select (POINT(44.8388128378145,-0.5730056762695312)<->POINT(latitude, longitude)) as distance, r.id , r.raison_sociale, r.raison_sociale, r.categorie, r.telephone, r.mobile, r.adresse, r.code_postale,r.commune, r.latitude, r.longitude, r.internet from resto r where (POINT(44.8388128378145,-0.5730056762695312)<->POINT(latitude, longitude))*6367445*pi()/180<245 order by (POINT(44.8388128378145,-0.5730056762695312)<->POINT(latitude, longitude)) limit 200

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
  divers character varying(255),
  adresse character varying(255),
  code_postale character varying(255),
  commune character varying(255),
  telephone character varying(255),
  internet character varying(255),
  tourisme character varying(255),
  twitter character varying(255)
);
\COPY resto_tmp FROM 'resto_tlse.csv' CSV DELIMITER ';'  HEADER ENCODING 'LATIN1';
INSERT INTO resto SELECT id,mobile,raison_sociale,mail,latitude,longitude,type,categorie,adresse,code_postale,commune,telephone,internet, twitter FROM resto_tmp;
DROP TABLE resto_tmp;
END;
