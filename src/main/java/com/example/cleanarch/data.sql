insert into lecture (lecture_id, title)
values (1, 'lecture');

insert into lecture_option (lecture_option_id, date, start_at, end_at, lecture_id)
values (1, '2024-10-10', '2024-10-10 10:00:00', '2024-10-10 12:00:00', 1);

insert into users(user_id, name)
values (1, 'user');

insert into enrollment(enrollment_id, user_id, lecture_id, lecture_option_id)
values (1, 1, 1, 1);