--drop
drop table bookmark;
drop table good;
drop table review;
drop table deal;
drop table product_info;
drop table withdrawal_member;
drop table member;
drop sequence mem_num;

--Ż��ȸ�� ���̺�
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


--ȸ����ȣ ������
create sequence mem_num
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;

--ȸ�� ���̺�
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


--��ȸ�� ȸ������ ����
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email)
values ('customer', 'customer123', '@pwcust123', '���', '����1', 'test1@test.com');
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email)
values ('customer', 'customer456', '@pwcust456', '�̰�', '����2', 'test2@test.com');
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email)
values ('customer', 'customer789', '@pwcust789', '�ڰ�', '����3', 'test3@test.com');
--����ȸ�� ȸ������ ����
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email,
mem_businessnumber, mem_store_name, mem_store_phonenumber, mem_store_location, mem_store_introduce, mem_store_sns)
values ('owner', 'owner123', '@pwown123', '������', '��Ī1', 'tester1@test.com',
'1112233333', '�δٱ���������', '0521112222', '��걤���� �߱� �޵�', null, null);
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email,
mem_businessnumber, mem_store_name, mem_store_phonenumber, mem_store_location, mem_store_introduce, mem_store_sns)
values ('owner', 'owner456', '@pwown456', '������', '��Ī2', 'tester2@test.com',
'4445566666', '�׷���Ʈ����', '0523334444', '��걤���� ���� �ϻ굿', '���� �����̾ �ż��ϰ� �����ϴ�', 'https://www.instagram.com/greatsushi/');
insert into member (mem_type, mem_id, mem_password, mem_name, mem_nickname, mem_email,
mem_businessnumber, mem_store_name, mem_store_phonenumber, mem_store_location, mem_store_introduce, mem_store_sns)
values ('owner', 'owner789', '@pwown789', '������', '��Ī3', 'tester3@test.com',
'7778899999', '����Ŀ��', '0525556666', '��걤���� ���� ������', '���ν���� �����Դϴ�', 'https://www.instagram.com/3teamcoffee/');

--�Ǹ����̺�
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

--�⺻Ű ����
ALTER TABLE PRODUCT_INFO ADD CONSTRAINT product_info_p_id_pk PRIMARY key(p_NUMBER);
 --�ܷ�Ű ����
 alter table PRODUCT_INFO ADD CONSTRAINT product_info_p_num_fk FOREIGN key(OWNER_NUMBER) REFERENCES member(mem_number) on delete cascade;

drop sequence PRODUCT_P_NUMBER_SEQ;
-- ��ǰ��ȣ ������ ����
create sequence PRODUCT_P_NUMBER_SEQ;

-- �ǸŻ���(0,1�� ���� ����)
ALTER TABLE PRODUCT_INFO ADD CONSTRAINT porduct_info_p_status_ck CHECK (P_STATUS =0 OR P_STATUS =1);

create table deal ( --�ŷ����̺� ����
order_number number(10),  --�ֹ���ȣ
buyer_number number (6), --�����ڹ�ȣ
seller_number number (6),--�ɸ��ڹ�ȣ
p_number number (30),   --�ǸŹ�ȣ
p_count number (3),      --��ǰ����
price number (6),        --����
visittime date,          --�湮�����ð�
buy_type number(1),     --��������
r_status number(1) default 0,      --�������
o_status number(1) default 0,      --�ֹ�����
orderdate date default sysdate,          --�ֹ�����
pickup_status number (1) default 0 --�Ⱦ�����
);

--�ŷ���ȣ ������ ����
drop sequence deal_order_number_seq;
create sequence deal_order_number_seq;

--�⺻Ű����
alter table deal add constraint deal_order_number_pk primary key (order_number);
--�ܷ�Ű����
alter table deal add constraint deal_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;  --������ ȸ�����̺� ����
alter table deal add constraint deal_seller_number_fk
    foreign key (seller_number) references member (mem_number)on delete cascade; --�Ǹ��� ȸ�� ���̺� ����
alter table deal add constraint deal_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade; --��ǰ ��ȣ ��ǰ ���̺� ����

-- ��������
ALTER TABLE deal ADD CONSTRAINT deal_buy_type_ck CHECK (buy_type ='0' OR buy_type ='1');
-- �������(0,1�� ���� ����)
ALTER TABLE deal ADD CONSTRAINT deal_r_status_ck CHECK (r_status ='0' OR r_status ='1'); 
-- �ֹ�����
ALTER TABLE deal ADD CONSTRAINT deal_o_status_ck CHECK (o_status ='0' OR o_status ='1'); 
-- �Ⱦ�����
ALTER TABLE deal ADD CONSTRAINT deal_pickup_status_ck CHECK (pickup_status ='0' OR pickup_status ='1'); 
  
    
create table review (   --�������̺�
review_number number(10),  --���� ��ȣ
buyer_number number (6),-- �ۼ��� ��ȣ
seller_number number(6),-- �Ǹ��� ��ȣ
content varchar2(150),--���� ����
write_date date default sysdate, --�ۼ���
grade number(2) -- ����

);    

--�����ȣ ������ ����
drop sequence review_review_number_seq;
create sequence  review_review_number_seq;

--�⺻Ű����
alter table review add constraint review_review_number_pk primary key (review_number);--���� ��ȣ pk
--�ܷ�Ű����
alter table review add constraint review_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;-- �ۼ��ڹ�ȣ ȸ�����̺� fk ����
alter table review add constraint review_seller_number_fk   
    foreign key (seller_number) references member (mem_number) on delete cascade;--�Ǹ��ڹ�ȣ ȸ�����̺� fk ����
  
  
create table good ( --���ƿ� ���̺�
good_number number(10),  --���ƿ� ��ȣ
mem_number number (6),-- ȸ�� ��ȣ
p_number number(5)-- ��ǰ ��ȣ
);   

--���ƿ��ȣ ������ ����
drop sequence good_good_number_seq;
create sequence  good_good_number_seq;

--�⺻Ű����
alter table good add constraint good_good_id_pk primary key (good_number);--���ƿ� ��ȣ pk
--�ܷ�Ű����
alter table good add constraint good_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- ȸ����ȣ ȸ�����̺� fk ����
alter table good add constraint good_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade;-- ��ǰ��ȣ ��ǰ���̺� fk ����
    
create table bookmark ( --���ã�� ���̺�
bookmark_number number(10),  --���ã�� ��ȣ
buyer_number number (10),-- ȸ�� ��ȣ
seller_number number(10)-- ������ ��ȣ
);       

--���ã���ȣ ������ ����
drop sequence bookmark_bookmark_number_seq;
create sequence  bookmark_bookmark_number_seq;

--�⺻Ű����
alter table bookmark add constraint bookmark_bookmark_number_pk primary key (bookmark_number);--���ƿ� ��ȣ pk
--�ܷ�Ű����
alter table bookmark add constraint bookmark_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;-- ȸ����ȣ ȸ�����̺� fk ����    
alter table bookmark add constraint bookmark_seller_number_fk
    foreign key (seller_number) references member (mem_number) on delete cascade;-- �Ǹ��� ��ȣ ȸ�����̺� fk ���� 
    
    INSERT INTO PRODUCT_INFO
 (P_NUMBER, OWNER_NUMBER, P_TITLE, P_NAME, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT, NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO,  P_STATUS) 
 VALUES (PRODUCT_P_NUMBER_SEQ.nextval, '00004',  '����� ��ũ���� �ε巯���Ʈ', '���� ��ũ�� ����ũ', sysdate,'ī��/����Ʈ', 3, 3, 10000, 4000, 60, '�¶��� ����', '����� ��ũ��', 0);
 INSERT INTO PRODUCT_INFO
 (P_NUMBER, OWNER_NUMBER, P_TITLE, P_NAME, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT, NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO,  P_STATUS) 
  VALUES (PRODUCT_P_NUMBER_SEQ.nextval, '00005', '���ִ� ��Ű', '��Ű��ũ��', TO_DATE('2022-09-01T14:50','YYYY-MM-DD"T"HH24:MI:SS'),'ī��/����Ʈ', 3, 3, 10000, 4000, 60, '���� ����', '��Ű�ӿ� ũ���� ���', 0);
  commit;
--�ŷ������� ����
insert into deal values (deal_order_number_seq.nextval, 1, 3,0001,1,'4000',sysdate,'1','0','0',sysdate,'0');
insert into deal values (deal_order_number_seq.nextval, 2, 3,0002,1,'5000',sysdate,'1','0','0',sysdate,'0');


--���䵥���� ����
insert into review values(review_review_number_seq.nextval,'1','3','���վ��',sysdate,'4');
insert into review values(review_review_number_seq.nextval,'2','3','���վ��',sysdate,'5');
select * from review;   
select * from product_info; 

--���ƿ䵥���� ����
insert into good values (GOOD_GOOD_NUMBER_SEQ.nextval,'1',0001); 
insert into good values (GOOD_GOOD_NUMBER_SEQ.nextval,'1',0002);

--���ã�ⵥ���� ����
insert into bookmark values (BOOKMARK_BOOKMARK_NUMBER_SEQ.nextval,'1','5');
    
  
    
select * from member;
select * from product_info;

