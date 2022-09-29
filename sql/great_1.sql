--drop
drop table withdrawal_member;
drop table member;
drop sequence mem_num;


--탈퇴회원 테이블
create table withdrawal_member (
mem_number number(9),
mem_type varchar2(15),
mem_id varchar2(30),
mem_password varchar2(18),
mem_name varchar2(18),
mem_nickname varchar2(18),
mem_email varchar2(30),
mem_businessnumber varchar2(10),
mem_store_name varchar2(45),
mem_store_phonenumber varchar2(15),
mem_store_location varchar2(150),
mem_store_latitude number(15, 9),
mem_store_longitude number(15, 9),
mem_store_introduce varchar2(150),
mem_store_sns varchar2(150),
mem_regtime date,
mem_lock_expiration date,
mem_admin varchar2(3)
);
--primary key
alter table withdrawal_member add constraint withdrawal_member_mem_number_pk primary key (mem_number);
--unique
alter table withdrawal_member add constraint withdrawal_member_mem_id_un unique (mem_id);
alter table withdrawal_member add constraint withdrawal_member_mem_nickname_un unique (mem_nickname);
alter table withdrawal_member add constraint withdrawal_member_mem_email_un unique (mem_email);
alter table withdrawal_member add constraint withdrawal_member_mem_businessnumber_un unique (mem_businessnumber);
alter table withdrawal_member add constraint withdrawal_member_mem_store_location_un unique (mem_store_location);
alter table withdrawal_member add constraint withdrawal_member_mem_store_latitude_un unique (mem_store_latitude);
alter table withdrawal_member add constraint withdrawal_member_mem_store_longitude_un unique (mem_store_longitude);
--not null
alter table withdrawal_member modify mem_number constraint withdrawal_member_mem_number_nn not null;
alter table withdrawal_member modify mem_type constraint withdrawal_member_mem_type_nn not null;
alter table withdrawal_member modify mem_id constraint withdrawal_member_mem_id_nn not null;
alter table withdrawal_member modify mem_password constraint withdrawal_member_mem_password_nn not null;
alter table withdrawal_member modify mem_name constraint withdrawal_member_mem_name_nn not null;
alter table withdrawal_member modify mem_nickname constraint withdrawal_member_mem_nickname_nn not null;
alter table withdrawal_member modify mem_email constraint withdrawal_member_mem_email_nn not null;
alter table withdrawal_member modify mem_regtime constraint withdrawal_member_mem_regtime_nn not null;


--회원번호 시퀀스
create sequence mem_num
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;

--회원 테이블
create table member (
mem_number number(9),
mem_type varchar2(15),
mem_id varchar2(30),
mem_password varchar2(18),
mem_name varchar2(18),
mem_nickname varchar2(18),
mem_email varchar2(30),
mem_businessnumber varchar2(10),
mem_store_name varchar2(45),
mem_store_phonenumber varchar2(15),
mem_store_location varchar2(150),
mem_store_latitude number(15, 9),
mem_store_longitude number(15, 9),
mem_store_introduce varchar2(150),
mem_store_sns varchar2(150),
mem_regtime date,
mem_lock_expiration date,
mem_admin varchar2(3)
);
--primary key
alter table member add constraint member_mem_number_pk primary key (mem_number);
--unique
alter table member add constraint member_mem_id_un unique (mem_id);
alter table member add constraint member_mem_nickname_un unique (mem_nickname);
alter table member add constraint member_mem_email_un unique (mem_email);
alter table member add constraint member_mem_businessnumber_un unique (mem_businessnumber);
alter table member add constraint member_mem_store_location_un unique (mem_store_location);
alter table member add constraint member_mem_store_latitude_un unique (mem_store_latitude);
alter table member add constraint member_mem_store_longitude_un unique (mem_store_longitude);
--not null
alter table member modify mem_number constraint member_mem_number_nn not null;
alter table member modify mem_type constraint member_mem_type_nn not null;
alter table member modify mem_id constraint member_mem_id_nn not null;
alter table member modify mem_password constraint member_mem_password_nn not null;
alter table member modify mem_name constraint member_mem_name_nn not null;
alter table member modify mem_nickname constraint member_mem_nickname_nn not null;
alter table member modify mem_email constraint member_mem_email_nn not null;
alter table member modify mem_regtime constraint member_mem_regtime_nn not null;
--default
alter table member modify mem_number number(9) default mem_num.nextval;
alter table member modify mem_regtime date default sysdate;
alter table member modify mem_admin varchar2(3) default 'n';


--고객회원 회원가입 쿼리
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email)
values ('customer', 'customer123', '@pwcust123', '김고객', '별명1', 'test1@test.com');
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email)
values ('customer', 'customer456', '@pwcust456', '이고객', '별명2', 'test2@test.com');
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email)
values ('customer', 'customer789', '@pwcust789', '박고객', '별명3', 'test3@test.com');
--점주회원 회원가입 쿼리
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email,
mem_businessnumber, mem_store_name, mem_store_phonenumber, mem_store_location, mem_store_introduce, mem_store_sns)
values ('owner', 'owner123', '@pwown123', '김점주', '별칭1', 'tester1@test.com',
'1112233333', '싸다구돼지국밥', '0521112222', '울산광역시 중구 달동', null, null);
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email,
mem_businessnumber, mem_store_name, mem_store_phonenumber, mem_store_location, mem_store_introduce, mem_store_sns)
values ('owner', 'owner456', '@pwown456', '이점주', '별칭2', 'tester2@test.com',
'4445566666', '그레이트스시', '0523334444', '울산광역시 동구 일산동', '마감 직전이어도 신선하게 나갑니다', 'https://www.instagram.com/greatsushi/');
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email,
mem_businessnumber, mem_store_name, mem_store_phonenumber, mem_store_location, mem_store_introduce, mem_store_sns)
values ('owner', 'owner789', '@pwown789', '박점주', '별칭3', 'tester3@test.com',
'7778899999', '삼팀커피', '0525556666', '울산광역시 남구 신정동', '아인슈페너 맛집입니다', 'https://www.instagram.com/3teamcoffee/');

--판매테이블
create table product_info(
    P_NUMBER NUMBER(30, 0)
,    OWNER_NUMBER NUMBER(6, 0)
,    P_TITLE VARCHAR2(300 BYTE)
,    P_NAME VARCHAR2(30 BYTE)
,    DEADLINE_TIME DATE
,    CATEGORY VARCHAR2(17 BYTE)
,    TOTAL_COUNT NUMBER(5, 0)
,    REMAIN_COUNT NUMBER(5, 0)
,    NORMAL_PRICE NUMBER(8, 0)
,    SALE_PRICE NUMBER(8, 0)
,    DISCOUNT_RATE NUMBER(2, 0)
,    PAYMENT_OPTION VARCHAR2(32 BYTE)
,    DETAIL_INFO VARCHAR2(4000 BYTE)
,    R_DATE DATE default sysdate
,    U_DATE DATE default sysdate
,    P_STATUS NUMBER(1, 0) default 0
);

--기본키 설정
ALTER TABLE PRODUCT_INFO ADD CONSTRAINT product_info_p_id_pk PRIMARY key(p_NUMBER);
 --외래키 설정
 alter table PRODUCT_INFO ADD CONSTRAINT product_info_p_num_fk FOREIGN key(OWNER_NUMBER) REFERENCES member(mem_number) on delete cascade;

drop sequence PRODUCT_P_NUMBER_SEQ;
-- 상품번호 시퀀스 생성
create sequence PRODUCT_P_NUMBER_SEQ;

-- 판매상태(0,1만 선택 가능)
ALTER TABLE PRODUCT_INFO ADD CONSTRAINT porduct_info_p_status_ck CHECK (P_STATUS =0 OR P_STATUS =1);

create table deal ( --거래테이블 생성
order_number number(10),  --주문번호
buyer_number number (6), --구매자번호
seller_number number (6),--핀매자번호
p_number number (30),   --판매번호
p_count number (3),      --상품수량
price number (6),        --가격
visittime date,          --방문예정시간
buy_type number(1),     --결제유형
r_status number(1) default 0,      --리뷰상태
o_status number(1) default 0,      --주문상태
orderdate date default sysdate,          --주문일자
pickup_status number (1) default 0 --픽업상태
);

--거래번호 시퀀스 생성
drop sequence deal_order_number_seq;
create sequence deal_order_number_seq;

--기본키설정
alter table deal add constraint deal_order_number_pk primary key (order_number);
--외래키설정
alter table deal add constraint deal_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;  --구매자 회원테이블 참조
alter table deal add constraint deal_seller_number_fk
    foreign key (seller_number) references member (mem_number)on delete cascade; --판매자 회원 테이블 참조
alter table deal add constraint deal_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade; --상품 번호 상품 테이블 참조

-- 결제유형
ALTER TABLE deal ADD CONSTRAINT deal_buy_type_ck CHECK (buy_type ='0' OR buy_type ='1');
-- 리뷰상태(0,1만 선택 가능)
ALTER TABLE deal ADD CONSTRAINT deal_r_status_ck CHECK (r_status ='0' OR r_status ='1'); 
-- 주문상태
ALTER TABLE deal ADD CONSTRAINT deal_o_status_ck CHECK (o_status ='0' OR o_status ='1'); 
-- 픽업상태
ALTER TABLE deal ADD CONSTRAINT deal_pickup_status_ck CHECK (pickup_status ='0' OR pickup_status ='1'); 
  
    
create table profile (  --프로필 테이블
profile_number number(5),  --프로필 번호
mem_number number (6),-- 유저 번호
p_number number(5)--상품 번호
);

--프로필번호 시퀀스 생성
drop sequence profile_profile_number_seq;
create sequence  profile_profile_number_seq;

--기본키설정
alter table profile add constraint profile_profile_number_pk primary key (profile_number);--프로필 번호 pk
--외래키설정
alter table profile add constraint profile_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- 유저번호 회원테이블 fk 참조
alter table profile add constraint profile_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade; --판매글 번호 판매테이블 fk 참조


create table review (   --리뷰테이블
review_number number(10),  --리뷰 번호
buyer_number number (6),-- 작성자 번호
seller_number number(6),-- 판매자 번호
content varchar2(150),--본문 내용
write_date date default sysdate, --작성일
grade number(2), -- 평점
profile_number number (10) --프로필 번호
);    

--리뷰번호 시퀀스 생성
drop sequence review_review_number_seq;
create sequence  review_review_number_seq;

--기본키설정
alter table review add constraint review_review_number_pk primary key (review_number);--리뷰 번호 pk
--외래키설정
alter table review add constraint review_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;-- 작성자번호 회원테이블 fk 참조
alter table review add constraint review_seller_number_fk   
    foreign key (seller_number) references member (mem_number) on delete cascade;--판매자번호 회원테이블 fk 참조
alter table review add constraint review_profile_number_fk
    foreign key (profile_number) references profile (profile_number)on delete cascade; --프로필번호 프로필테이블 fk참조
  
    
create table good ( --좋아요 테이블
good_number number(10),  --좋아요 번호
mem_number number (6),-- 회원 번호
p_number number(5)-- 상품 번호
);   

--좋아요번호 시퀀스 생성
drop sequence good_good_number_seq;
create sequence  good_good_number_seq;

--기본키설정
alter table good add constraint good_good_id_pk primary key (good_number);--좋아요 번호 pk
--외래키설정
alter table good add constraint good_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- 회원번호 회원테이블 fk 참조
alter table good add constraint good_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade;-- 상품번호 상품테이블 fk 참조
    
create table bookmark ( --즐겨찾기 테이블
bookmark_number number(10),  --즐겨찾기 번호
mem_number number (10),-- 회원 번호
profile_number number(10)-- 프로필 번호
);    

--즐겨찾기번호 시퀀스 생성
drop sequence bookmark_bookmark_number_seq;
create sequence  bookmark_bookmark_number_seq;

--기본키설정
alter table bookmark add constraint bookmark_bookmark_number_pk primary key (bookmark_number);--좋아요 번호 pk
--외래키설정
alter table bookmark add constraint bookmark_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- 회원번호 회원테이블 fk 참조    
alter table bookmark add constraint bookmark_profile_number_fk
    foreign key (profile_number) references profile (profile_number) on delete cascade;-- 프로필번호 프로필테이블 fk 참조  
    
insert into product_info(p_number, owner_number, p_title, p_name, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT ,NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO ) ");
        sql.append("values(122, 9,"달달한 딸기 생크림 케이크입니다", "딸기 생크림 케이크", "2022-09-29", 30000, 23000, ?, ?, ?, ?, ?, ?) ");    

select * from member;

update member
   set mem_store_name = "디저트카페"
   where mem_number = 4;