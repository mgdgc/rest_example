create database statistic default character set utf8 collate utf8_bin;

use statistic;

create Table requestInfo
(
    requestID   numeric    not null primary key,
    requestCode varchar(5) not null,
    userID      varchar(5),
    createDate  varchar(10)
);

create table requestCode
(
    requestCode  varchar(5)  not null primary key,
    code_explain varchar(50) not null
);

create table user
(
    userID   varchar(5) not null primary key,
    HR_ORGAN varchar(5) not null,
    USERNAME varchar(5) not null
);

# 데이터 삽입

insert into requestInfo(requestID, requestCode, userID, createDate)
values (1, 'L', 'AAA', '2008180520'),
       (2, 'O', 'DDD', '2004040404'),
       (3, 'L', 'BBB', '2006220920'),
       (4, 'L', 'CCC', '1906220920');

insert into requestInfo(requestID, requestCode, userID, createDate)
values (5, 'L', 'EEE', '2008180520'),
       (6, 'O', 'FFF', '2004040404'),
       (7, 'L', 'GGG', '2006220920'),
       (8, 'L', 'HHH', '1906220920');

insert into requestInfo(requestID, requestCode, userID, createDate)
values (9, 'L', 'EEE', '2008180550'),
       (10, 'O', 'FFF', '2004040304'),
       (11, 'L', 'GGG', '2006230920'),
       (12, 'L', 'HHH', '2006220920');

insert into requestInfo(requestID, requestCode, userID, createDate)
values (13, 'L', 'EEE', '2008180520'),
       (14, 'O', 'FFF', '2004040404'),
       (15, 'L', 'GGG', '1906220920'),
       (16, 'L', 'HHH', '2006220920');

insert into user
values ('AAA', 'WEB', 'aaa'),
       ('BBB', 'WEB', 'aaa'),
       ('CCC', 'APP', 'aaa'),
       ('DDD', 'APP', 'aaa'),
       ('EEE', 'OPS', 'aaa'),
       ('FFF', 'OPS', 'aaa'),
       ('GGG', 'APP', 'aaa'),
       ('HHH', 'DOC', 'aaa');



# SW활용 현황 통계 API 구축을 위한 SQL 작성

# 월별 접속자 수
select substr(createDate, 1, 4), count(*)
from requestInfo
group by substr(createDate, 1, 4);

# 일별 접속자 수
select createDate, count(*)
from requestInfo
group by createDate;

# 일별 접속자 수
select count(*)
from requestInfo
group by createDate;

# 평균 하루 로그인 수
select count(*)
from requestInfo
where requestCode = 'L'
group by createDate;

# 부서별 월별 로그인 수
select createDate, HR_ORGAN, count(*)
from (select requestInfo.userID, requestInfo.requestCode, requestInfo.createDate, user.HR_ORGAN, user.USERNAME
      from requestInfo
               left join
           user on requestInfo.userID = user.userID
      union
      select requestInfo.userID, requestInfo.requestCode, requestInfo.createDate, user.HR_ORGAN, user.USERNAME
      from requestInfo
               right join
           user on requestInfo.userID = user.userID) as a
group by createDate, HR_ORGAN;