CREATE TABLE AMS.STUDENT_CLASS (
	ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
	STUDENT_ID INTEGER,
	CLASS_ID INTEGER,
	CLASS_SECTION VARCHAR(128)
)	
ALTER TABLE AMS.STUDENT_CLASS
	ADD FOREIGN KEY (CLASS_ID) 
	REFERENCES AMS.CLASS (ID)	

ALTER TABLE AMS.STUDENT_CLASS
	ADD FOREIGN KEY (STUDENT_ID) 
	REFERENCES AMS.STUDENT (ID)	


