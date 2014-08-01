# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table resto (
  id                        bigint not null,
  raison_sociale            varchar(255),
  mobile                    varchar(255),
  mail                      varchar(255),
  latitude                  float,
  longitude                 float,
  type                      varchar(255),
  categorie                 varchar(255),
  specialite                varchar(255),
  adresse                   varchar(255),
  code_postale              varchar(255),
  commune                   varchar(255),
  telephone                 varchar(255),
  internet                  varchar(255),
  classement                varchar(255),
  marque                    varchar(255),
  tourisme                  varchar(255),
  constraint pk_resto primary key (id))
;

create sequence resto_seq;




# --- !Downs

drop table if exists resto cascade;

drop sequence if exists resto_seq;

