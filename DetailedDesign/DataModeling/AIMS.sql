CREATE TABLE BookCategory (
 bookCategoryID CHAR(10) NOT NULL,
 bookCategoryName VARCHAR(127) NOT NULL
);

ALTER TABLE BookCategory ADD CONSTRAINT PK_BookCategory PRIMARY KEY (bookCategoryID);


CREATE TABLE DeliveryType (
 deliveryTypeID CHAR(10) NOT NULL,
 deliveryTypeName VARCHAR(15) NOT NULL
);

ALTER TABLE DeliveryType ADD CONSTRAINT PK_DeliveryType PRIMARY KEY (deliveryTypeID);


CREATE TABLE DiscCategory (
 discCategoryID CHAR(10) NOT NULL,
 discCategoryName VARCHAR(10) NOT NULL
);

ALTER TABLE DiscCategory ADD CONSTRAINT PK_DiscCategory PRIMARY KEY (discCategoryID);


CREATE TABLE DVDCategory (
 DVDCategoryID CHAR(10) NOT NULL,
 DVDCategoryName VARCHAR(127) NOT NULL
);

ALTER TABLE DVDCategory ADD CONSTRAINT PK_DVDCategory PRIMARY KEY (DVDCategoryID);


CREATE TABLE DVDType (
 DVDTypeID CHAR(10) NOT NULL,
 DVDTypeName VARCHAR(127) NOT NULL
);

ALTER TABLE DVDType ADD CONSTRAINT PK_DVDType PRIMARY KEY (DVDTypeID);


CREATE TABLE Media (
 mediaID CHAR(10) NOT NULL,
 title VARCHAR(10) NOT NULL,
 value DECIMAL(10) NOT NULL,
 price DECIMAL(10) NOT NULL,
 publicationDate DATE NOT NULL,
 description CHAR(10),
 stockEntryDate DATE NOT NULL,
 dimension CHAR(10) NOT NULL,
 quantity INT NOT NULL
);

ALTER TABLE Media ADD CONSTRAINT PK_Media PRIMARY KEY (mediaID);


CREATE TABLE PaymentTransaction (
 transactionID CHAR(10) NOT NULL,
 holderName VARCHAR(127) NOT NULL,
 amountDeducted DECIMAL(10) NOT NULL,
 balance DECIMAL(10) NOT NULL,
 content VARCHAR(127) NOT NULL,
 transactionDate DATE NOT NULL,
 transactionTime TIME(10) NOT NULL
);

ALTER TABLE PaymentTransaction ADD CONSTRAINT PK_PaymentTransaction PRIMARY KEY (transactionID);


CREATE TABLE Song (
 songID CHAR(10) NOT NULL,
 songName VARCHAR(127) NOT NULL
);

ALTER TABLE Song ADD CONSTRAINT PK_Song PRIMARY KEY (songID);


CREATE TABLE Book (
 mediaID CHAR(10) NOT NULL,
 author VARCHAR(127) NOT NULL,
 coverType VARCHAR(127) NOT NULL,
 bookCategoryID CHAR(10) NOT NULL,
 pages INT NOT NULL,
 publisher VARCHAR(127) NOT NULL,
 category VARCHAR(127) NOT NULL,
 language VARCHAR(127) NOT NULL
);

ALTER TABLE Book ADD CONSTRAINT PK_Book PRIMARY KEY (mediaID);


CREATE TABLE DeliveryInfo (
 deliveryID CHAR(10) NOT NULL,
 receiverName VARCHAR(127) NOT NULL,
 phone CHAR(10) NOT NULL,
 city VARCHAR(127) NOT NULL,
 address VARCHAR(127) NOT NULL,
 deliveryTypeID CHAR(10) NOT NULL,
 deliveryInstruction VARCHAR(255) NOT NULL,
 receiverTime DATE NOT NULL
);

ALTER TABLE DeliveryInfo ADD CONSTRAINT PK_DeliveryInfo PRIMARY KEY (deliveryID);


CREATE TABLE Disc (
 mediaID CHAR(10) NOT NULL,
 artists VARCHAR(127) NOT NULL,
 discCategoryID CHAR(10) NOT NULL,
 category VARCHAR(10) NOT NULL,
 recordLable VARCHAR(127) NOT NULL
);

ALTER TABLE Disc ADD CONSTRAINT PK_Disc PRIMARY KEY (mediaID);


CREATE TABLE DVD (
 mediaID CHAR(10) NOT NULL,
 DVDTypeID CHAR(10) NOT NULL,
 DVDCategoryID CHAR(10) NOT NULL,
 director VARCHAR(127) NOT NULL,
 studio VARCHAR(127) NOT NULL,
 runtime TIME(10) NOT NULL,
 subtitle VARCHAR(127) NOT NULL,
 language VARCHAR(50) NOT NULL,
 category VARCHAR(127) NOT NULL
);

ALTER TABLE DVD ADD CONSTRAINT PK_DVD PRIMARY KEY (mediaID);


CREATE TABLE Invoice (
 invoiceID CHAR(10) NOT NULL,
 deliveryID CHAR(10) NOT NULL,
 transactionID CHAR(10) NOT NULL,
 shippingFee DECIMAL(10) NOT NULL,
 totalPrice DECIMAL(10) NOT NULL
);

ALTER TABLE Invoice ADD CONSTRAINT PK_Invoice PRIMARY KEY (invoiceID);


CREATE TABLE InvoiceDetail (
 mediaID CHAR(10) NOT NULL,
 invoiceID CHAR(10) NOT NULL,
 price DECIMAL(10) NOT NULL,
 quantity INT NOT NULL
);

ALTER TABLE InvoiceDetail ADD CONSTRAINT PK_InvoiceDetail PRIMARY KEY (mediaID,invoiceID);


CREATE TABLE ListAlbum (
 mediaID CHAR(10) NOT NULL,
 songID CHAR(10) NOT NULL
);

ALTER TABLE ListAlbum ADD CONSTRAINT PK_ListAlbum PRIMARY KEY (mediaID,songID);


ALTER TABLE Book ADD CONSTRAINT FK_Book_0 FOREIGN KEY (mediaID) REFERENCES Media (mediaID);
ALTER TABLE Book ADD CONSTRAINT FK_Book_1 FOREIGN KEY (bookCategoryID) REFERENCES BookCategory (bookCategoryID);


ALTER TABLE DeliveryInfo ADD CONSTRAINT FK_DeliveryInfo_0 FOREIGN KEY (deliveryTypeID) REFERENCES DeliveryType (deliveryTypeID);


ALTER TABLE Disc ADD CONSTRAINT FK_Disc_0 FOREIGN KEY (mediaID) REFERENCES Media (mediaID);
ALTER TABLE Disc ADD CONSTRAINT FK_Disc_1 FOREIGN KEY (discCategoryID) REFERENCES DiscCategory (discCategoryID);


ALTER TABLE DVD ADD CONSTRAINT FK_DVD_0 FOREIGN KEY (mediaID) REFERENCES Media (mediaID);
ALTER TABLE DVD ADD CONSTRAINT FK_DVD_1 FOREIGN KEY (DVDTypeID) REFERENCES DVDType (DVDTypeID);
ALTER TABLE DVD ADD CONSTRAINT FK_DVD_2 FOREIGN KEY (DVDCategoryID) REFERENCES DVDCategory (DVDCategoryID);


ALTER TABLE Invoice ADD CONSTRAINT FK_Invoice_0 FOREIGN KEY (deliveryID) REFERENCES DeliveryInfo (deliveryID);
ALTER TABLE Invoice ADD CONSTRAINT FK_Invoice_1 FOREIGN KEY (transactionID) REFERENCES PaymentTransaction (transactionID);


ALTER TABLE InvoiceDetail ADD CONSTRAINT FK_InvoiceDetail_0 FOREIGN KEY (mediaID) REFERENCES Media (mediaID);
ALTER TABLE InvoiceDetail ADD CONSTRAINT FK_InvoiceDetail_1 FOREIGN KEY (invoiceID) REFERENCES Invoice (invoiceID);


ALTER TABLE ListAlbum ADD CONSTRAINT FK_ListAlbum_0 FOREIGN KEY (mediaID) REFERENCES Disc (mediaID);
ALTER TABLE ListAlbum ADD CONSTRAINT FK_ListAlbum_1 FOREIGN KEY (songID) REFERENCES Song (songID);

