CREATE TABLE Users (
    user_id INT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE UserProfiles (
    profile_id INT PRIMARY KEY,
    user_id INT UNIQUE,
    bio TEXT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);