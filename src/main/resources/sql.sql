CREATE TABLE `public`.`user` (
  `userId` INT NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `birthDate` DATETIME NOT NULL,
  `emailAddress` VARCHAR(45) NULL,
  `lastUpdateDate` DATETIME NOT NULL,
  `lastUpdatedBy` VARCHAR(50) NULL,
  `createdDate` DATETIME NOT NULL,
  `createdBy` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `idUser_UNIQUE` (`userId` ASC) VISIBLE);
