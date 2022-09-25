drop table bookmark;
drop table good;
drop table review;
drop table profile;
drop table deal;

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
    
-- �������(0,1�� ���� ����)
ALTER TABLE deal ADD CONSTRAINT deal_r_status_ck CHECK (r_status ='0' OR r_status ='1'); 
-- �ֹ�����
ALTER TABLE deal ADD CONSTRAINT deal_o_status_ck CHECK (o_status ='0' OR o_status ='1'); 
-- �Ⱦ�����
ALTER TABLE deal ADD CONSTRAINT deal_pickup_status_ck CHECK (pickup_status ='0' OR pickup_status ='1'); 
  
    
create table profile (  --������ ���̺�
profile_number number(5),  --������ ��ȣ
mem_number number (6),-- ���� ��ȣ
p_number varchar2(5)--��ǰ ��ȣ
);

--�����ʹ�ȣ ������ ����
drop sequence profile_profile_number_seq;
create sequence  profile_profile_number_seq;

--�⺻Ű����
alter table profile add constraint profile_profile_number_pk primary key (profile_number);--������ ��ȣ pk
--�ܷ�Ű����
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
profile_number number (10) --������ ��ȣ
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
alter table review add constraint review_profile_number_fk
    foreign key (profile_number) references profile (profile_number)on delete cascade; --�����ʹ�ȣ ���������̺� fk����
  
    
create table good ( --���ƿ� ���̺�
good_number number(10),  --���ƿ� ��ȣ
mem_number number (6),-- ȸ�� ��ȣ
p_number varchar2(5)-- ��ǰ ��ȣ
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
mem_number number (10),-- ȸ�� ��ȣ
profile_number number(10)-- ������ ��ȣ
);    

--���ƿ��ȣ ������ ����
drop sequence bookmark_bookmark_number_seq;
create sequence  bookmark_bookmark_number_seq;

--�⺻Ű����
alter table bookmark add constraint bookmark_bookmark_number_pk primary key (bookmark_number);--���ƿ� ��ȣ pk
--�ܷ�Ű����
alter table bookmark add constraint bookmark_mem_number_fk
    foreign key (mem_number) references member (mem_number) on delete cascade;-- ȸ����ȣ ȸ�����̺� fk ����    
alter table bookmark add constraint bookmark_profile_number_fk
    foreign key (profile_number) references profile (profile_number) on delete cascade;-- �����ʹ�ȣ ���������̺� fk ����      
    

--�ŷ������� ����
insert into deal values (deal_order_number_seq.nextval, 1, 3,'0001',1,'4000',sysdate,'1','0','0',sysdate,'0');
insert into deal values (deal_order_number_seq.nextval, 2, 3,'0002',1,'5000',sysdate,'1','0','0',sysdate,'0');

--�����ʵ����� ����
insert into profile values (PROFILE_PROFILE_NUMBER_SEQ.nextval,'3','0001');

--���䵥���� ����
insert into review values(REVIEW_REVIEW_NUMBER_SEQ.nextval,'1','3','���վ��',sysdate,'5',1);
insert into review values(REVIEW_REVIEW_NUMBER_SEQ.nextval,'2','3','���վ��',sysdate,'5',1);


--���ƿ䵥���� ����
insert into good values (GOOD_GOOD_NUMBER_SEQ.nextval,'1','0001'); 
insert into good values (GOOD_GOOD_NUMBER_SEQ.nextval,'1','0002');

--���ã�ⵥ���� ����
insert into bookmark values (BOOKMARK_BOOKMARK_NUMBER_SEQ.nextval,'1','1');
    