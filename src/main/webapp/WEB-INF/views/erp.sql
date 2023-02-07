create table code_skill(skill_code number(3),
             skill_name VARCHAR2(20) not null UNIQUE,
             PRIMARY key(skill_code)
);
create SEQUENCE code_skill_seq start with 1 INCREMENT by 1;

create table code_department(department_code number(3),
             department_name VARCHAR2(30) not null UNIQUE,
             PRIMARY key(department_code)
);
create SEQUENCE code_department_seq start with 1 INCREMENT by 1;

create table code_school(school_code number(3),
             school_name VARCHAR2(20) not null UNIQUE,
             PRIMARY key(school_code)
);
create SEQUENCE code_school_seq start with 1 INCREMENT by 1;

create table staff(
    staff_no NUMBER(3),
    staff_name VARCHAR2(14) not null,
    jumin_no VARCHAR2(14) not null UNIQUE,
    jumin_no2 NUMBER(8),
    school_code NUMBER(3) not null,
    department_code NUMBER(3) not null,
    graduate_day char(10) not null,
    FOREIGN KEY(department_code) REFERENCES code_department(department_code),
    FOREIGN KEY(school_code) REFERENCES code_school(school_code),
    PRIMARY KEY(staff_no)
); 
create SEQUENCE staff_seq start with 1 INCREMENT by 1;

create table staff_skill(
    staff_skill_no NUMBER(3),
    staff_no NUMBER(3) not null,
    skill_code number(3) not null,
    FOREIGN KEY(staff_no) REFERENCES staff(staff_no),
    FOREIGN KEY(skill_code) REFERENCES code_skill(skill_code),
    PRIMARY KEY(staff_skill_no)
); 
create SEQUENCE staff_skill_seq start with 1 INCREMENT by 1;

insert into code_skill(skill_code, skill_name) values(1, 'Java');
insert into code_skill(skill_code, skill_name) values(2, 'JSP');
insert into code_skill(skill_code, skill_name) values(3, 'ASP');
insert into code_skill(skill_code, skill_name) values(4, 'PHP');
insert into code_skill(skill_code, skill_name) values(5, 'Delphi');

insert into code_department(department_code, department_name) values(1, '컨설팅사업부');
insert into code_department(department_code, department_name) values(2, '하이테크사업부');
insert into code_department(department_code, department_name) values(3, 'SI사업부');
insert into code_department(department_code, department_name) values(4, '반도체사업부');
insert into code_department(department_code, department_name) values(5, '기업부설연구소');
insert into code_department(department_code, department_name) values(6, '전략기획팀');
insert into code_department(department_code, department_name) values(7, '경영지원팀');

insert into code_school(school_code, school_name) values(1, '고졸');
insert into code_school(school_code, school_name) values(2, '전문대졸');
insert into code_school(school_code, school_name) values(3, '일반대졸');