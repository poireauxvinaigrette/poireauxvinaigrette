# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table menu (
  id                        integer not null,
  message_id                varchar(255),
  creation_date             timestamp,
  destinataire              varchar(255),
  text                      varchar(255),
  type                      varchar(255),
  reception_date            timestamp,
  resto                     integer,
  constraint pk_menu primary key (id))
;

create table resto (
  id                        integer not null,
  mobile                    varchar(255),
  raison_sociale            varchar(255),
  mail                      varchar(255),
  latitude                  float,
  longitude                 float,
  type                      varchar(255),
  categorie                 varchar(255),
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

create sequence menu_seq;

create sequence resto_seq;

alter table menu add constraint fk_menu_resto_1 foreign key (resto) references resto (id);
create index ix_menu_resto_1 on menu (resto);



# --- !Downs

drop table if exists menu cascade;

drop table if exists resto cascade;

drop sequence if exists menu_seq;

drop sequence if exists resto_seq;

