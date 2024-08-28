CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(100)
);

CREATE TABLE Readers (
    reader_id INT PRIMARY KEY,
    reader_name VARCHAR(100),
    membership_date DATE
);

CREATE TABLE ReaderBooks (
    reader_id INT,
    book_id INT,
    return_date DATE,
    PRIMARY KEY (reader_id, book_id),
    FOREIGN KEY (reader_id) REFERENCES Readers(reader_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);
