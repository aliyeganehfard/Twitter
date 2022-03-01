create table if not exists Twitter_Account(
    id serial primary key ,
    user_name varchar(100),
    password varchar(100),
    account_name varchar(100)
);
create table if not exists Twitter_Tweet(
    id serial primary key ,
    "date" date,
    description varchar(250),
    title varchar(50),
    account_id Integer ,
    constraint Twitter_T_A_ID foreign key (account_id) references Twitter_Account(id)
);
create table if not exists Twitter_Comment(
    id serial primary key ,
    comment varchar(280),
    account_id Integer,
    tweet_id Integer,
    constraint Twitter_C_A_ID foreign key (account_id) references Twitter_Account(id),
    constraint Twitter_C_T_ID foreign key (tweet_id) references Twitter_Tweet(id)
);