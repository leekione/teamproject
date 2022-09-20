--������ ��ȸ
select m.mem_nickname, m.mem_type, m.mem_food_category, m.mem_store_name, m.mem_location
       ,m.mem_email, m.mem_sns
  from profile p, member m
 where p.mem_number = m.mem_number
   and p.mem_number=3;

--�����ʿ��� ���� ��ȸ   
select m.mem_name,r.buyer_number, r.content, r.write_date, r.grade
  from profile p, member m, review r
 where r.buyer_number = m.mem_number
   and r.profile_number = p.profile_number
   and p.profile_number =1;
   
--�Ǹű� ��ȸ
select p2.p_title, p2.detail_info
  from profile p1, product_info p2
 where p1.mem_number = p2.owner_number
   and p2.owner_number = 3;
 
 
--���ų��� ��ȸ
select d.order_number, d.orderdate, m.mem_store_name, 
       p.p_name, d.price, d.visittime, d.buy_type,
       d.r_status, d.o_status
  from deal d, product_info p, member m
 where d.seller_number = m.mem_number
   and p.owner_number = m.mem_number
   and d.buyer_number =2;

--�������
delete
  from deal
  where order_number =1
  and buyer_number=1;
  

--������ȸ
select m.mem_name, r.content,r.write_date,r.grade
  from review r, member m
  where r.buyer_number = mem_number
  and buyer_number =1;
  
--�������
update review
set content = '�������վ��',
    grade=4
    where review_number=1;

--�������
delete 
  from review
where review_number=1
and buyer_number=1;



--���ƿ� ��ȸ 
select m.mem_store_name, p.detail_info
  from good g, member m, product_info p
  where g.mem_number = m.mem_number
   and g.p_number = p.p_number
   and g.mem_number = 1;

--���ƿ� ����   
delete 
  from good g
  where good_number = 1
  and mem_number=1;

  
--���ã�� ��ȸ
select m.mem_introduce, m.mem_store_name
  from bookmark b, member m
  where b.mem_number = m.mem_number
  and b.mem_number=1;

--���ã�� ����
delete
  from bookmark
  where bookmark_number = 1
    and mem_number=1;

  
  

  


