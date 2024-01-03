select *
from student s
order by id desc
limit 5;
alter table student
add constraint student_age_greater_than_sixsteen check (age >= 16);

alter table student
add constraint student_name_unique_not_null unique (name);

alter table student
alter column name set not null;

alter table faculty
add constraint faculty_name_color_unique(name, color);

alter table student
alter column age set default 20;