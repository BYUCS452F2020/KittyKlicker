create table Users
  (
    id text not null primary key,
    Username text not null unique,
    Password text not null,
    Email text not null,
    FirstName text not null,
    LastName text not null,
    Gender text not null,
    CHECK (Gender IN ("f", "m"))
  );

create table Persons
  (
    id text not null primary key,
    Descendant text not null,
    FirstName text not null,
    LastName text not null,
    Gender text not null,
    Father integer,
    Mother integer,
    Spouse integer,
    CHECK (Gender IN ("f","m"))
  );

create table Events
  (
    id text not null primary key,
    Descendant text not null,
    Person text not null,
    Latitude numeric not null,
    Longitude numeric not null,
    Country text not null,
    City text not null,
    EventType text not null,
    Year integer not null
  );

create table Auth
  (
    Username text not null,
    Token text not null
  );
