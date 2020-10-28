CREATE TABLE user(
    userID text NOT NULL PRIMARY KEY,
    password text NOT NULL,
    kittiesKlicked int NOT NULL,
    kittiesPerKlick int NOT NULL,
    teamID Text
);
CREATE TABLE team(
    teamID text NOT NULL PRIMARY KEY,
    motto text NOT NULL,
    totalKittiesKlicked int NOT NULL
);
CREATE TABLE powerup(
    powerupID text NOT NULL PRIMARY key,
    requirements text NOT NULL,
    benefits text NOT NULL,
    userID text
);