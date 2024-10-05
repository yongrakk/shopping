create table shopping.shoppingItem
(
    itemId          int not null primary key auto_increment,    --상품 코드 PK
    itemCateId      int not null,                               --상품 카테고리코드
    itemName        varchar(200) not null,                      --상품명
    itemThumbnail1  varchar(500),                               --상품 대표이미지
    itemThumbnail2  varchar(500),                               --상품 썸네일사진
    itemThumbnail3  varchar(500),                               --상품 썸네일사진2
    itemThumbnail4  varchar(500),                               --상품 썸네일사진3
    itemThumbnail5  varchar(500),                               --상품 썸네일사진4
    itemDetail      varchar(500) not null,                      --상품 상세설명
    itemOriPrice    int not null,                               --상품 원가
    itemSalePrice   int null,                                   --상품 할인가
    itemCompany     varchar(100),                               --제조사
    itemDistributor varchar(100)                                --유통사
);

create table shopping.shoppingCategory(
    cateId      int not null primary key,                       --카테고리 코드 PK
    cateName    varchar(100) not null                           --카테고리 이름
);

create table shopping.shoppingUser(
    userId      int not null primary key auto_increment,        --유저 코드 PK
    userName    varchar(100) not null UNIQUE ,                  --유저 이름
    userEmail   varchar(100) not null,                          --유저 이메일
    password    varchar(200) not null                           --유저 패스워드
);

create table shopping.shoppingLikeItem(
    likeId int not null primary key auto_increment,                 --찜 목록 코드 PK
    userId int not null,                                            --찜 유저 코드 shoppingUser(userId) FK
    itemId int not null,                                            --찜 상품 코드 shoppingItem(itemId) FK
    foreign key (userId) references shopping.shoppingUser(userId),
    foreign key (itemId) references shopping.shoppingItem(itemId)
);