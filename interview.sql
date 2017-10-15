having 
not in 
as
insert into .. select ..../values(1,’A’,22,’男’,123456,’小学’) （行）
Alter table stu add 学历 varchar(6)   （列） /drop column columnname
Update stu set 学历=’大专’ where 联系电话 like ‘11%’ //like set的用法
delete from stu where ..
create view name as (select ..) 
create table tablename(id int,name varchar(8))
create procedure
order by ... asc /desc 
(case when  then when then else) as ..
Select top 25 percent * from stu

顺序是from where select

create temporary table  AS (
	 DECLARE @var1 date,@var2 date,@usr int(11);
set @num := 0;
set  @col1 := 0;
select user_id,trade_success_date
   @num := if(@col1 = user_id, @num + 1, 1) as row_number,
   @col1 := user_id
)


create procedure name()
	BEGIN
	DECLARE .. DATE;
    DECLARE i INT DEFAULT 0;
    set @s='select user_id';
    WHILE  .. DO
    set  first_d = ();
    END WHILE;
	END;


同一个表在select中出现
select year, 
(select amount from aaa m where month=1 and m.year=aaa.year) as m1,
from aaa group by year

(case when 语文>=80 then '优秀'
when 语文>=60 then '及格'
else '不及格') as 语文,



事务 Transaction 触发器 TRIGGER 继续 continue 唯一 unqiue

主键 primary key 标识列 identity 外键 foreign key 检查 check

约束 constraint

select top 10 * from tablename order by newid()

select * into b from a where 1<>1  

select *
from timp a, timp b 
where a.name > b.name  两个表先join再比较


