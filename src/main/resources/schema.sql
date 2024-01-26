drop table if exists PATIENT;

create table PATIENT(
        id int auto_increment,
        patient_name varchar not null ,
        date_Of_Birth date not null,
        address varchar,
        gender varchar
);