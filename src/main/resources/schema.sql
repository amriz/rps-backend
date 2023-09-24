drop table if exists SCORES;

CREATE TABLE SCORES(
    ID int not null AUTO_INCREMENT,
    NAME varchar(150) not null,
    SCORE int,
    PRIMARY KEY ( ID )
 );