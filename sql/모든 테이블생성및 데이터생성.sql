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
 
 CREATE TABLE PRODUCT_INFO 
 (
  P_NUMBER VARCHAR2(4 BYTE) NOT NULL --��ǰ ��ȣ
 , OWNER_NUMBER NUMBER(6,0) NOT NULL --���ְ� ��ȣ
 , P_TITLE VARCHAR2(300 BYTE) NOT NULL --��ǰ����
 , P_NAME VARCHAR2(30 BYTE) NOT NULL --��ǰ��
 , DEADLINE_TIME DATE --�������Žð�
 , CATEGORY VARCHAR2(17 BYTE) NOT NULL --����ī�װ�
 , TOTAL_COUNT NUMBER(5, 0) DEFAULT 0 NOT NULL --�� ����
 , REMAIN_COUNT NUMBER(5, 0) DEFAULT 0 --���� ����
 , NORMAL_PRICE NUMBER(8, 0) NOT NULL --����
 , SALE_PRICE NUMBER(8, 0) DEFAULT 0 NOT NULL --���ΰ�
 , DISCOUNT_RATE NUMBER(2, 0) DEFAULT 0 --������
 , PAYMENT_OPTION VARCHAR2(35 BYTE) NOT NULL --�������
 , DETAIL_INFO CLOB --�� ����
 , UPDATE_DATE DATE DEFAULT sysdate --������(default �����)
 , P_STATUS VARCHAR2(20 BYTE) DEFAULT '�Ǹ���' NOT NULL --�ǸŻ���
 );
 --�⺻Ű ����
 alter table PRODUCT_INFO ADD CONSTRAINT product_info_p_id_pk PRIMARY key(p_NUMBER);
 --�ܷ�Ű ����
 alter table PRODUCT_INFO ADD CONSTRAINT product_info_p_o_num_fk FOREIGN key(OWNER_NUMBER) REFERENCES member(mem_number) on delete cascade;
-- �ǸŻ���(0,1�� ���� ����)
ALTER TABLE PRODUCT_INFO ADD CONSTRAINT porduct_info_p_status_ck CHECK (P_STATUS ='0' OR P_STATUS ='1');

create table deal ( --�ŷ����̺� ����
order_number varchar2(6),  --�ֹ���ȣ
buyer_number number (6), --�����ڹ�ȣ
seller_number number (6),--�ɸ��ڹ�ȣ
p_number varchar2 (5),   --�ǸŹ�ȣ
p_count number (3),      --��ǰ����
price number (6),        --����
visittime date,          --�湮�����ð�
buy_type number(1) default 0,     --��������
r_status number(1) default 0,      --�������
o_status number(1) default 0,      --�ֹ�����
orderdate date,          --�ֹ�����
pickup_status number (1) default 0 --�Ⱦ�����
);

--�⺻Ű����
alter table deal add constraint deal_order_number_pk primary key (order_number);
--�ܷ�Ű����
alter table deal add constraint deal_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;  --������ ȸ�����̺� ����
alter table deal add constraint deal_seller_number_fk
    foreign key (seller_number) references member (mem_number)on delete cascade; --�Ǹ��� ȸ�� ���̺� ����
alter table deal add constraint deal_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade; --��ǰ ��ȣ ��ǰ ���̺� ����
  
    
create table profile (  --������ ���̺�
profile_number number(5),  --������ ��ȣ
mem_number number (6),-- ���� ��ȣ
p_number varchar2(5)--��ǰ ��ȣ
--review_number number(10)    --���� ��ȣ
);

alter table profile add constraint profile_profile_number_pk primary key (profile_number);--������ ��ȣ pk
alter table profile add constraint profile_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- ������ȣ ȸ�����̺� fk ����
alter table profile add constraint profile_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade; --�Ǹű� ��ȣ �Ǹ����̺� fk ����


create table review (   --�������̺�
review_number number(1),  --���� ��ȣ
buyer_number number (6),-- �ۼ��� ��ȣ
seller_number number(6),-- �Ǹ��� ��ȣ
content varchar2(150),--���� ����
write_date date, --�ۼ���
grade number(2), -- ����
profile_number number (10)
);    
alter table review add constraint review_review_number_pk primary key (review_number);--���� ��ȣ pk
alter table review add constraint review_buyer_number_fk
    foreign key (buyer_number) references member (mem_number) on delete cascade;-- �ۼ��ڹ�ȣ ȸ�����̺� fk ����
alter table review add constraint review_seller_number_fk   
    foreign key (seller_number) references member (mem_number) on delete cascade;--�Ǹ��ڹ�ȣ ȸ�����̺� fk ����
alter table review add constraint review_profile_number_fk
    foreign key (profile_number) references profile (profile_number)on delete cascade; --�����ʹ�ȣ ���������̺� fk����
  
    
create table good ( --���ƿ� ���̺�
good_number number(10),  --���ƿ� ��ȣ
mem_number number (6),-- ȸ�� ��ȣ
p_number varchar2(5)-- ��ǰ ��ȣ
);   

alter table good add constraint good_good_id_pk primary key (good_number);--���ƿ� ��ȣ pk
alter table good add constraint good_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- ȸ����ȣ ȸ�����̺� fk ����
alter table good add constraint good_p_number_fk
    foreign key (p_number) references product_info (p_number) on delete cascade;-- ��ǰ��ȣ ��ǰ���̺� fk ����
    
create table bookmark ( --���ã�� ���̺�
bookmark_number number(10),  --���ã�� ��ȣ
mem_number number (10),-- ȸ�� ��ȣ
profile_number number(10)-- ������ ��ȣ
);    

alter table bookmark add constraint bookmark_bookmark_number_pk primary key (bookmark_number);--���ƿ� ��ȣ pk
alter table bookmark add constraint bookmark_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- ȸ����ȣ ȸ�����̺� fk ����    
alter table bookmark add constraint bookmark_profile_number_fk
    foreign key (profile_number) references profile (profile_number) on delete cascade;-- �����ʹ�ȣ ���������̺� fk ����      
    
INSERT INTO member VALUES (1, 'active', 'owner', 'wnsgur5508', 'wnsgur5508', '������1',
'����', 'altruism_tap@naver.com', '111-22-33333', '�ѽ�', '�δٱ���������', 01047211443,
'��걤���� �߱� ������', '�ݰ����ϴ�.', 'ddd@gmail.com', '2022-08-22', null, null, 'no');

INSERT INTO member VALUES (2, 'active', 'owner', 'wnsgur5508', 'wnsgur5508', '������1',
'����', 'altruism_tap@naver.com', '111-22-33333', '�߽�', '�߱���', 01047211443,
'��걤���� ���� ȭ����', '�ݰ����ϴ�.', 'ddd@gmail.com', '2022-08-22', null, null, 'no');

INSERT INTO member VALUES (3, 'active', 'owner', 'wnsgur5508', 'wnsgur5508', '�̱��1',
'���', 'altruism_tap@naver.com', '111-22-33333', '�Ͻ�', 'ī��/����Ʈ', 01047211443,
'��걤���� �߱� ������', '�ݰ����ϴ�.', 'ddd@gmail.com', '2022-08-22', null, null, 'no');

INSERT INTO PRODUCT_INFO
 (P_NUMBER, OWNER_NUMBER, P_TITLE, P_NAME, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT, NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO,  P_STATUS) 
 VALUES ('0001', '00003',  '����� ��ũ���� �ε巯���Ʈ', '���� ��ũ�� ����ũ', sysdate,'ī��/����Ʈ', 3, 3, 10000, 4000, 60, '�¶��� ����', '����� ��ũ��', 0);
 INSERT INTO PRODUCT_INFO
 (P_NUMBER, OWNER_NUMBER, P_TITLE, P_NAME, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT, NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO,  P_STATUS) 
  VALUES ('0002', '00003', '���ִ� ��Ű', '��Ű��ũ��', TO_DATE('2022-09-01T14:50','YYYY-MM-DD"T"HH24:MI:SS'),'ī��/����Ʈ', 3, 3, 10000, 4000, 60, 0, '��Ű�ӿ� ũ���� ���', 0);

--�ŷ������� ����
insert into deal values ('000001', 1, 3,'0001',1,'4000',sysdate,'1','0','0',sysdate,'0');
insert into deal values ('000002', 2, 3,'0002',1,'5000',sysdate,'1','0','0',sysdate,'0');

--�����ʵ����� ����
insert into profile values ('1','3','0001');

--���䵥���� ����
insert into review values('1','1','3','���վ��',sysdate,'5',1);
insert into review values('2','2','3','���վ��',sysdate,'5',1);


--���ƿ䵥���� ����
insert into good values ('1','1','0001'); 
insert into good values ('2','1','0002');

--���ã�ⵥ���� ����
insert into bookmark values ('1','1','1');
    