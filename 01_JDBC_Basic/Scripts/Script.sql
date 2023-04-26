DROP TABLE TEST;
CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

DROP SEQUENCE SEQ_TESTNO;
CREATE SEQUENCE SEQ_TESTNO NOCACHE;

INSERT INTO TEST VALUES(SEQ_TESTNO.NEXTVAL, '테스트1', '2022-04-01');
INSERT INTO TEST VALUES(SEQ_TESTNO.NEXTVAL, '테스트2', '2022-04-12');
INSERT INTO TEST VALUES(SEQ_TESTNO.NEXTVAL, '테스트3', '2022-04-23');


DROP TABLE MEMBER;

CREATE TABLE MEMBER(
    USER_NO NUMBER PRIMARY KEY,                  -- 회원번호
    USER_ID VARCHAR2(15) NOT NULL UNIQUE,        -- 회원아이디
    USER_PWD VARCHAR2(15) NOT NULL,              -- 회원비번
    USER_NAME VARCHAR2(20) NOT NULL,             -- 회원명
    GENDER CHAR(1) CHECK(GENDER IN ('M', 'F')), -- 성별
    AGE NUMBER,                                 -- 나이
    EMAIL VARCHAR2(30),                         -- 이메일
    PHONE CHAR(11),                             -- 전화번호
    ADDRESS VARCHAR2(100),                      -- 주소
    HOBBY VARCHAR2(50),                         -- 취미
    ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL    -- 회원가입일
);

DROP SEQUENCE SEQ_USERNO;
CREATE SEQUENCE SEQ_USERNO NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '관리자', 'M', 45, 'admin@iei.or.kr', '01012345555', '서울', null, '2022-04-11');

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '홍길동', null, 23, 'user01@iei.or.kr', '01022221111', '부산', '등산,영화보기', '2022-04-23');

SELECT * FROM MEMBER;
SELECT * FROM TEST;

COMMIT;
​



/* Book 데이터 생성 */
DROP TABLE Book; 
CREATE TABLE Book (
bookid NUMBER(2) PRIMARY KEY,
bookname VARCHAR2(40),
publisher VARCHAR2(40),
price NUMBER(8)
);
 
 /* Book 데이터 생성 */
INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7000);
INSERT INTO Book VALUES(2, '축구아는 여자', '나무수', 13000);
INSERT INTO Book VALUES(3, '축구의 이해', '대한미디어', 22000);
INSERT INTO Book VALUES(4, '골프 바이블', '대한미디어', 35000);
INSERT INTO Book VALUES(5, '피겨 교본', '굿스포츠', 8000);
INSERT INTO Book VALUES(6, '역도 단계별기술', '굿스포츠', 6000);
INSERT INTO Book VALUES(7, '야구의 추억', '이상미디어', 20000);
INSERT INTO Book VALUES(8, '야구를 부탁해', '이상미디어', 13000);
INSERT INTO Book VALUES(9, '올림픽 이야기', '삼성당', 7500);
INSERT INTO Book VALUES(10, 'Olympic Champions', 'Pearson', 13000);

commit;
select * from book;