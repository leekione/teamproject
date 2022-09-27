drop table bookmark;
drop table good;
drop table review;
drop table profile;
drop table deal;
drop table product_info;
drop table member;

CREATE TABLE member (
mem_number NUMBER(20),
mem_status VARCHAR2(20) DEFAULT 'active',
mem_type VARCHAR2(20),
mem_userid VARCHAR2(20),
mem_password VARCHAR2(20),
mem_name VARCHAR2(20),
mem_nickname VARCHAR2(20),
mem_email VARCHAR2(30),
mem_businessnumber VARCHAR2(20),
mem_food_category VARCHAR2(20),
mem_store_name VARCHAR2(50),
mem_store_phonenumber NUMBER(20),
mem_location VARCHAR2(100),
mem_introduce VARCHAR2(100),
mem_sns VARCHAR2(100),
mem_regtime DATE DEFAULT SYSDATE,
mem_unregtime DATE,
mem_icon VARCHAR2(100),
mem_admin VARCHAR2(20)
);

alter table member add constraint member_mem_number_pk primary key (mem_number);
 
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

--좋아요번호 시퀀스 생성
drop sequence bookmark_bookmark_number_seq;
create sequence  bookmark_bookmark_number_seq;

--기본키설정
alter table bookmark add constraint bookmark_bookmark_number_pk primary key (bookmark_number);--좋아요 번호 pk
--외래키설정
alter table bookmark add constraint bookmark_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- 회원번호 회원테이블 fk 참조    
alter table bookmark add constraint bookmark_profile_number_fk
    foreign key (profile_number) references profile (profile_number) on delete cascade;-- 프로필번호 프로필테이블 fk 참조      
    
INSERT INTO member VALUES (1, 'active', 'owner', 'wnsgur5508', 'wnsgur5508', '허준혁1',
'허준', 'altruism_tap@naver.com', '111-22-33333', '한식', '싸다구돼지국밥', 01047211443,
'울산광역시 중구 우정동', '반갑습니다.', 'ddd@gmail.com', '2022-08-22', null, null, 'no');

select * from member;
INSERT INTO member VALUES (2, 'active', 'owner', 'wnsgur5508', 'wnsgur5508', '김지연1',
'지연', 'altruism_tap@naver.com', '111-22-33333', '중식', '중국집', 01047211443,
'울산광역시 동구 화정동', '반갑습니다.', 'ddd@gmail.com', '2022-08-22', null, null, 'no');

INSERT INTO member VALUES (3, 'active', 'owner', 'wnsgur5508', 'wnsgur5508', '이기원1',
'기원', 'altruism_tap@naver.com', '111-22-33333', '일식', '카페/디저트', 01047211443,
'울산광역시 중구 우정동', '반갑습니다.', 'ddd@gmail.com', '2022-08-22', null, null, 'no');

INSERT INTO PRODUCT_INFO
 (P_NUMBER, OWNER_NUMBER, P_TITLE, P_NAME, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT, NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO,  P_STATUS) 
 VALUES (0001, '00003',  '담백한 생크림과 부드러운시트', '딸기 생크림 케이크', sysdate,'카페/디저트', 3, 3, 10000, 4000, 60, '온라인 결제', '딸기와 생크림', 0);
 INSERT INTO PRODUCT_INFO
 (P_NUMBER, OWNER_NUMBER, P_TITLE, P_NAME, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT, NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO,  P_STATUS) 
  VALUES (0002, '00003', '맛있는 쿠키', '쿠키앤크림', TO_DATE('2022-09-01T14:50','YYYY-MM-DD"T"HH24:MI:SS'),'카페/디저트', 3, 3, 10000, 4000, 60, 0, '쿠키속에 크림이 듬뿍', 0);

--거래데이터 생성
insert into deal values (deal_order_number_seq.nextval, 1, 3,0001,1,'4000',sysdate,'1','0','0',sysdate,'0');
insert into deal values (deal_order_number_seq.nextval, 2, 3,0002,1,'5000',sysdate,'1','0','0',sysdate,'0');

--프로필데이터 생성
insert into profile values ('1','3',0001);

--리뷰데이터 생성
insert into review values('1','1','3','맛잇어요',sysdate,'5',1);
insert into review values('2','2','3','맛잇어요',sysdate,'5',1);
select * from review;
select * from product_info; 

--좋아요데이터 생성
insert into good values ('1','1',0001); 
insert into good values ('2','1',0002);

--즐겨찾기데이터 생성
insert into bookmark values ('1','1','1');
update product_infO
  set remain_count = 20
  where p_number = 1;
  rollback;
commit;
select p_name, remain_count
from product_info;
select * from product_info; 
select * from deal;
delete from deal;  
delete from product_info;

select  * 
from product_info P, member M
where p.owner_number = m.mem_number and p.p_number= 1;


commit;
select * 
 from deal d, member m, product_info p
 where d.buyer_number = m.mem_number 
 and d.p_number = p.p_number
 and d.seller_number = p.owner_number
 and d.order_number = 1;
  
  select * from member; 
 select *
  from member m, deal d, product_info p
  where m.mem_number = d.buyer_number
   and p.p_number = d.p_number
  and d.order_number= 1;
 
select *
from member m, product_info p
where p.owner_number = m.mem_number
and p.p_number = 2;
        
        
    select * from member;