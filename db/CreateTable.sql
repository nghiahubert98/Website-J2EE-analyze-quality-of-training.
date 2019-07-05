create table nganh
(
    id int auto_increment primary key,
    ma_nganh char(20) not null unique,
    ten varchar(100) not null
);

create table giang_vien
(
    id int auto_increment primary key,
    ma_gv char(20) not null unique,
    ten varchar(100) not null,
	email varchar(100) not null unique
);

create table lop_sinh_hoat
(
    id int auto_increment primary key,
    ma_lop char(20) not null unique,
    batdau date not null,
    totnghiep date,
    ten_lop varchar(100),
    co_van int not null,
	id_nganh int not null,
    foreign key(co_van) references giang_vien(id),
	foreign key(id_nganh) references nganh(id)
);

create table sinh_vien
(
    id int auto_increment primary key,
    mssv char(20) not null unique,
    ten varchar(100) not null,
    lopsh_id int not null,
    foreign key(lopsh_id) references lop_sinh_hoat(id)
);

create table mon_hoc
(
    id int auto_increment primary key,
    ma_mon char(20) not null unique,
    ten varchar(100) not null
);

create table hoc_ky
(
      id int auto_increment primary key,
      loai varchar(50),
      nam_bat_dau int,
      nam_ket_thuc int
);

create table lop
(
    id int auto_increment primary key,
    ma_lop char(20) not null unique,
    hocky_id int not null,
    id_mon int not null,
    gv_id int not null,
    foreign key(id_mon) references mon_hoc(id),
    foreign key(hocky_id) references hoc_ky(id),
    foreign key(gv_id) references giang_vien(id)
);

create table sinhvien_lop
(
    id int auto_increment primary key,
    lop_id int not null,
    sv_id int not null,
    diem_qt float null,
    unique(lop_id, sv_id),
    foreign key(lop_id) references lop(id),
    foreign key(sv_id) references sinh_vien(id)
);

create table chuan_g
(
	id int auto_increment primary key,
	ma_g char(20) unique,
	mota text
);

create table chuan_lo
(
	id int auto_increment primary key,
	ma_lo VARCHAR(20) UNIQUE,
	mota text
);

create table lo_nganh 
(
    id int auto_increment primary key,
	lo_id int not null,
    nganh_id int not null,
    nien_khoa varchar(20) not null,
    unique(lo_id, nganh_id),
    foreign key(nganh_id) references nganh(id),
    foreign key(lo_id) references chuan_lo(id)
);

create table g_lo(
    id int primary key auto_increment,
	id_g int not null,
	id_lo int not null,
    unique(id_g, id_lo),
    foreign key(id_g) references chuan_g(id),
    foreign key(id_lo) references chuan_lo(id)
);

create table mon_g
(
    id int primary key auto_increment,
    id_monhoc int not null,
	id_g int not null,
    unique(id_monhoc, id_g),
    foreign key(id_g) references chuan_g(id),
    foreign key(id_monhoc) references mon_hoc(id)
);

create table dethi
(
    id int primary key auto_increment,
    loai_de bit, -- 0: de giua ky| 1 de cuoi ky
    id_lop int not null,
    foreign key(id_lop) references lop(id)
);

create table cau_hoi
(
     id int primary key auto_increment,
     sothutu int,
     diemtoida float not null,
     dethi_id int not null,
     unique(sothutu, dethi_id),
     foreign key(dethi_id) references dethi(id)
);

create table cauhoi_g
(
    id int primary key auto_increment,
    cauhoi_id int not null,
    g_id int not null,
    unique(cauhoi_id, g_id),
    foreign key(cauhoi_id) references cau_hoi(id),
    foreign key(g_id) references chuan_g(id)
);

create table diem
(
    id int primary key auto_increment,
    cauhoi_id int not null,
    id_sv int not null,
    diem float not null,
    foreign key(id_sv) references sinh_vien(id),
    foreign key(cauhoi_id) references cau_hoi(id)
);

create table users
(
    id int primary key auto_increment,
    pwd varchar(150) null,
	  salt char(100) null,
    user_type int, -- 0 sinh_vien | 1 giang_vien
    gv_id int null unique,
    sv_id int null unique,
	roles varchar(150) not null,
	status bit default 1,
	unique(sv_id, gv_id),
    foreign key(sv_id) references sinh_vien(id),
    foreign key(gv_id) references giang_vien(id)
);