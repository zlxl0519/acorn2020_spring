create table board_cafe(
	num number primary key,
	writer varchar2(100) not null,
	title varchar2(100) not null,
	content CLOB,
	viewCount number, -- 조회수
	regdate date 
);

create sequence board_cafe_seq;
--댓글 테이블
-- 대댓글은 댓글의 글번호와 comment_group 번호와 다르면 들여쓰기를 한다.
--시퀀스에서 미리 번호를 가지고 와서 insert 할때 같이 집어넣는다. 원글의 댓글 번호를 같은 값으로 하기 위해(댓글의 글번호 = comment_group)
create table board_cafe_comment(
	num number primary key, --댓글의 글번호
	writer varchar2(100), --댓글 작성자의 아이디
	content varchar2(500), --댓글 내용
	target_id varchar2(100), --댓글의 대상자 아이디
	ref_group number, -- 원글 몇번의 댓글들인지 --where ref_group=? 이런식으로 사용해서 댓글 가지고 오기
	comment_group number, -- 댓글과 대댓글이 하나의 그룹 그 그룹을 하나의 번호로 지정, 댓글과 그 대댓글은 같은 댓글의 글번호를 부여 하지만 대댓글의 댓글의 글번호는 각각 부여 된다.
	deleted char(3) default 'no',--댓글이 삭제된 여부, 디폴드 값으로 no를 넣었다가 삭제 됬을시 yes 를 넣음
	regdate date
);

create sequence board_cafe_comment_seq;

create table board_gallery(
	num number primary key,
	writer varchar2(100),
	caption varchar2(100),
	imagePath varchar2(100),
	regdate date
);

create sequence board_gallery_seq;

-- upload 된 파일의 정보를 저장할 테이블
-- 파일명이 겹치지 않도록 서버에 저장되는 파일명을 다르게 한다.
create table board_file(
	num number primary key,
	writer varchar2(100) not null,
	title varchar2(100) not null,
	orgFileName varchar2(100) not null, -- 원본 파일명
	saveFileName varchar2(100) not null, -- 서버에 실제로 저장된 파일명
	fileSize number not null,
	regdate date
);

create sequence board_file_seq;


-- 사용자(회원) 정보를 저장할 테이블
create table users(
	id varchar2(100) primary key,
	pwd varchar2(100) not null,
	email varchar2(100),
	profile varchar2(100), -- 프로필 이미지 경로를 저장할 칼럼
	regdate date
);

