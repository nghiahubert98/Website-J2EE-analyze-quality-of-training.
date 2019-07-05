SET NAMES utf8;
INSERT INTO `nganh` VALUES (1,'NGANH1','Công nghệ Thông tin');
INSERT INTO `nganh` VALUES (2,'NGANH2','Khoa học Dữ liệu');
INSERT INTO `nganh` VALUES (3,'NGANH3','Công nghệ Thông tin định hướng Nhật Bản');

INSERT INTO `giang_vien` VALUES (1,'GV1','Huỳnh Văn An', 'huynhva@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (2,'GV2','Nguyễn Văn Kiệt', 'kietnv@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (3,'GV3','Võ Tấn Khoa', 'khoatv@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (4,'GV4','Võ Ngọc Tân', 'tannv@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (5,'GV5','Nguyễn Thị Thu Thủy', 'thuyttn@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (6,'GV6','Lê Thị Thư', 'thult@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (7,'GV7','Nguyễn Công Trí', 'trinc@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (8,'GV8','Ngô Kiến Huy', 'huykn@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (9,'GV9','Lê Đức Bình', 'binhdl@gm.uit.edu.vn');
INSERT INTO `giang_vien` VALUES (10,'GV10','Trần Trung Trực', 'tructt@gm.uit.edu.vn');

INSERT INTO `lop_sinh_hoat` VALUES (1,'LSH1','2015-01-01','2019-09-09','CNTT2015',1, 1);
INSERT INTO `lop_sinh_hoat` VALUES (2,'LSH2','2016-01-01','2020-09-09','CNTT2016',2, 1);
INSERT INTO `lop_sinh_hoat` VALUES (3,'LSH3','2017-01-01','2021-09-09','CNTT2017',3, 2);
INSERT INTO `lop_sinh_hoat` VALUES (4,'LSH4','2018-01-01','2022-09-09','CNTT2018',4, 2);
INSERT INTO `lop_sinh_hoat` VALUES (5,'LSH5','2018-01-01','2022-09-09','CNTTNB2018',5, 2);
INSERT INTO `lop_sinh_hoat` VALUES (6,'LSH6','2018-01-01','2022-09-09','KHDL2018',6, 2);

INSERT INTO `sinh_vien` VALUES (1,'15521001', 'Nguyễn Ngọc Phong',1);
INSERT INTO `sinh_vien` VALUES (2,'15521002', 'Nguyễn Văn La',1);
INSERT INTO `sinh_vien` VALUES (3,'15521003', 'Trần Huy Cung',1);
INSERT INTO `sinh_vien` VALUES (4,'15521004', 'Nguyễn Thị Mỹ',1);
INSERT INTO `sinh_vien` VALUES (5,'15521005', 'La Quốc Hùng',1);

INSERT INTO `sinh_vien` VALUES (6,'16521001', 'Nguyễn Mỹ Duyên',2);
INSERT INTO `sinh_vien` VALUES (7,'16521002', 'Lê Quan Hà',2);
INSERT INTO `sinh_vien` VALUES (8,'16521003', 'Trần Minh Quốc',2);
INSERT INTO `sinh_vien` VALUES (9,'16521004', 'Nguyễn Thị Hòa',2);
INSERT INTO `sinh_vien` VALUES (10,'16521005', 'Trần Thanh Sang',2);


INSERT INTO `sinh_vien` VALUES (11,'17521001', 'Nguyễn Quốc Phong',3);
INSERT INTO `sinh_vien` VALUES (12,'17521002', 'Nguyễn Thoại My',3);
INSERT INTO `sinh_vien` VALUES (13,'17521003', 'Trần Huy Hùng',3);
INSERT INTO `sinh_vien` VALUES (14,'17521004', 'Nguyễn Thị Mỹ Duyên',3);
INSERT INTO `sinh_vien` VALUES (15,'17521005', 'La Quy Quốc',3);


INSERT INTO `sinh_vien` VALUES (16,'18521001', 'Trần Giang Phi',4);
INSERT INTO `sinh_vien` VALUES (17,'18521002', 'Lê Kim Quy',4);
INSERT INTO `sinh_vien` VALUES (18,'18521003', 'Trần Quốc Toản',4);
INSERT INTO `sinh_vien` VALUES (19,'18521004', 'Nguyễn Thị Thủy',4);
INSERT INTO `sinh_vien` VALUES (20,'18521005', 'Trần Đại Nghĩa',4);


INSERT INTO `sinh_vien` VALUES (21,'18521011', 'Huỳnh Văn Tín',5);
INSERT INTO `sinh_vien` VALUES (22,'18521012', 'Lê Văn Phú',5);
INSERT INTO `sinh_vien` VALUES (23,'18521013', 'Trần Ngọc Phú',5);
INSERT INTO `sinh_vien` VALUES (24,'18521014', 'Nguyễn Thị Mỹ Lệ',5);
INSERT INTO `sinh_vien` VALUES (25,'18521015', 'La Lý Sơn',5);

INSERT INTO `sinh_vien` VALUES (26,'18521021', 'Trần Minh Đức',6);
INSERT INTO `sinh_vien` VALUES (27,'18521022', 'Quốc Minh Khang',6);
INSERT INTO `sinh_vien` VALUES (28,'18521023', 'Võ Minh Tuyền',6);
INSERT INTO `sinh_vien` VALUES (29,'18521024', 'Nguyễn Mâu Thủy',6);
INSERT INTO `sinh_vien` VALUES (30,'18521025', 'Trần Hoàng Thùy',6);

INSERT INTO `mon_hoc` VALUES (1,'IT001', 'Nhập môn lập trình');
INSERT INTO `mon_hoc` VALUES (2,'IT002', 'Lập trình hướng đối tượng');
INSERT INTO `mon_hoc` VALUES (3,'IT003', 'Cấu trúc dữ liệu và giải thuật');
INSERT INTO `mon_hoc` VALUES (4,'IS252', 'Khai thác dữ liệu');
INSERT INTO `mon_hoc` VALUES (5,'IS217', 'Kho dữ liệu và OLAP (Online Analytical Processing)');
INSERT INTO `mon_hoc` VALUES (6,'IE254', 'Hệ hỗ trợ ra quyết định');
INSERT INTO `mon_hoc` VALUES (7,'IE202', 'Quản trị doanh nghiệp');
INSERT INTO `mon_hoc` VALUES (8,'IE203', 'Hệ thống quản trị qui trình nghiệp vụ (BPM)');
INSERT INTO `mon_hoc` VALUES (9,'IS208', 'Quản lý dự án công nghệ thông tin');
INSERT INTO `mon_hoc` VALUES (10,'IS336', 'Hoạch định nguồn lực doanh nghiệp');
INSERT INTO `mon_hoc` VALUES (11,'IE204', 'Tối ưu hóa công cụ tìm kiếm (SEO)');
INSERT INTO `mon_hoc` VALUES (12,'IS207', 'Phát triển ứng dụng Web');
INSERT INTO `mon_hoc` VALUES (13,'IS353', 'Mạng xã hội');
INSERT INTO `mon_hoc` VALUES (14,'IS334', 'Thương mại điện tử');
INSERT INTO `mon_hoc` VALUES (15,'IS251', 'Nhập môn hệ thống thông tin địa lý');
INSERT INTO `mon_hoc` VALUES (16,'IS352', 'Hệ cơ sở dữ liệu không gian');
INSERT INTO `mon_hoc` VALUES (17,'IS351', 'Phân tích không gian');
INSERT INTO `mon_hoc` VALUES (18,'IE205', 'Xử lý ảnh vệ tinh');
INSERT INTO `mon_hoc` VALUES (19,'IE301', 'Quản trị quan hệ khách hàng (CRM)');
INSERT INTO `mon_hoc` VALUES (20,'IE303', 'Công nghệ Java');

INSERT INTO `chuan_lo` VALUES (1,'LO12016', 'Nắm vững kiến thức khoa học cơ bản và có khả năng vận dụng vào chuyên ngành CNTT');
INSERT INTO `chuan_lo` VALUES (2,'LO22016', 'Nắm vững kiến thức về công nghệ thông tin.');
INSERT INTO `chuan_lo` VALUES (3,'LO32016', 'Có kiến thức về dữ liệu, thông tin, và tri thức');
INSERT INTO `chuan_lo` VALUES (4,'LO42016', 'Có kiến thức và khả năng lập trình và phát triển phần mềm.');
INSERT INTO `chuan_lo` VALUES (5,'LO52016', ' Có kiến thức về phân tích, tích hợp, và áp dụng cho chuyên môn.');
INSERT INTO `chuan_lo` VALUES (6,'LO62016', 'Nắm vững các kiến thức, công cụ phù hợp để quản lý và ứng dụng công nghệ thông tin vào lĩnh vực thực tế.');
INSERT INTO `chuan_lo` VALUES (7,'LO72016', 'Có kiến thức về quản lý nguồn tài nguyên, các hoạt động của cơ quan / tổ chức, các giải pháp sử dụng công nghệ thông tin để nâng cao khả năng lãnh đạo, quản lý của cơ quan / tổ chức.');
INSERT INTO `chuan_lo` VALUES (8,'LO82016', 'Trình độ Anh văn theo qui định chung của Trường.');
INSERT INTO `chuan_lo` VALUES (9,'LO92016', 'Kỹ năng quản lý đề án công nghệ thông tin và nhóm đề án.');
INSERT INTO `chuan_lo` VALUES (10,'LO102016', 'Kỹ năng trình bày công việc về công nghệ thông tin đang làm, phản biện dựa trên thực nghiệm, kỹ năng sáng tạo, kỹ năng giải quyết vấn đề.');
INSERT INTO `chuan_lo` VALUES (11,'LO112016', 'Có kỹ năng giao tiếp xã hội, hợp tác và làm việc nhóm, chia sẻ tri thức và kinh nghiệm, khả năng điều hành nhóm công tác.');
INSERT INTO `chuan_lo` VALUES (12,'LO122016', 'Có ý thức vai trò, trách nhiệm, đạo đức nghề nghiệp trong xã hội, hành xử chuyên nghiệp, tôn trong cam kết, trung thực, uy tín, trung thành và có khả năng nhận thức, đánh giá các hiện tượng một cách logic và tích cực.');
INSERT INTO `chuan_lo` VALUES (13,'LO132016', 'Có nhận thức về sự cần thiết và khả năng tham gia vào việc học tập suốt đời, có kiến thức rộng để có thể làm việc hiệu quả trong bối cảnh những công nghệ mới liên tục xuất hiện để từ đó hiểu được tác động của các công nghệ mới trong bối cảnh xã hội, kinh tế toàn cầu.');

INSERT INTO `chuan_lo` VALUES (14,'LO12017', 'Vận dụng kiến thức nền tảng về khoa học tự nhiên và khoa học xã hội để giải quyết vấn đề liên quan chuyên ngành.');
INSERT INTO `chuan_lo` VALUES (15,'LO22017', 'Vận dụng kiến thức nền tảng của ngành Công nghệ Thông tin và ứng dụng vào thực tiễn liên quan đến dữ liệu, thông tin, tri thức, và kỹ thuật công nghệ mới.');
INSERT INTO `chuan_lo` VALUES (16,'LO32017', 'Phân tích, lập luận,và giải quyết vấn đề liên quan đến ngành Công nghệ Thông tin (quản lý nguồn tài nguyên, các hoạt động của cơ quan/doanh nghiệp, các giải pháp sử dụng công nghệ thông tin để nâng cao khả năng lãnh đạo, quản lý, hoạt động của cơ quan/doanh nghiệp).');
INSERT INTO `chuan_lo` VALUES (17,'LO42017', 'Biếtkỹ năng nghiên cứu khoa học (khảo sát tài liệu, phân tích, đánh giá, vận dụng các công trình khoa học).');
INSERT INTO `chuan_lo` VALUES (18,'LO52017', ' Hiểu và nhận thứctư duy hệ thống, phân tích, thiết kế, đánh giá các thành phần hoặc toàn hệ thống thuộc lĩnh vực ngành Công nghệ Thông tin,vận dụng nhanh các công nghệ, kỹ thuật, công cụ phù hợp để quản lý và ứng dụng công nghệ thông tin vào lĩnh vực thực tế.');
INSERT INTO `chuan_lo` VALUES (19,'LO62017', ' Hiểu về sự cần thiết để học tập suốt đời, hiểu biết về các giá trị đạo đức và trách nhiệm nghề nghiệp.');
INSERT INTO `chuan_lo` VALUES (20,'LO72017', 'Vận dụng đượckỹ năng làm việc nhóm (thành lập, điều hành và duy trì công tác nhóm).');
INSERT INTO `chuan_lo` VALUES (21,'LO82017', 'Vận dụng đượckỹ năng giao tiếp (kỹ năng làm chủ đối thoại, thuyết trình tốt).');
INSERT INTO `chuan_lo` VALUES (22,'LO92017', 'Có năng lực ngoại ngữ bậc 3/6 Khung năng lực ngoại ngữ của Việt Nam (kỹ năng nghe nói, đọc hiểu tài liệu, viết khá tốt tiếng Anh).');
INSERT INTO `chuan_lo` VALUES (23,'LO102017', 'Nhận biết bối cảnh và nhu cầu xã hội, xác định ý tưởng, thiết kế, xây dựng,triển khai, vận hành ứng dụng các hệ thống Công nghệ Thông tin đáp ứng nhu cầu xã hội. Khả năng xây dựng tốt ý tưởng, thiết kế, phát triển, triển khai, vận hành.');

INSERT INTO `chuan_lo` VALUES (24,'LO12018DL', 'Có kiến thức nền tảng về khoa học tự nhiên và khoa học xã hội');
INSERT INTO `chuan_lo` VALUES (25,'LO22018DL', 'Có kiến thức nền tảng và nâng cao của ngành Khoa học dữ liệu (ngôn ngữ lập trình thống kê, các công cụ phân tích dữ liệu …)');
INSERT INTO `chuan_lo` VALUES (26,'LO32018DL', 'Có khả năng lập luận, phân tích, dự báo, thống kê cao cấp và giải quyết vấn đề liên quan đến ngành Khoa học dữ liệu');
INSERT INTO `chuan_lo` VALUES (27,'LO42018DL', 'Có kỹ năng nghiên cứu khoa học và khám phá tri thức (khảo sát tài liệu, phân tích, đánh giá)');
INSERT INTO `chuan_lo` VALUES (28,'LO52018DL', 'Có tư duy hệ thống, có khả năng thiết kế các thành phần hoặc toàn bộ hệ thống thu thập và phân tích dữ liệu');
INSERT INTO `chuan_lo` VALUES (29,'LO62018DL', 'Hiểu biết về các giá trị đạo đức, có kỹ năng cá nhân và nghề nghiệp, năng lực học tập suốt đời');
INSERT INTO `chuan_lo` VALUES (30,'LO72018DL', 'Có kỹ năng làm việc nhóm với tác phong chuyên nghiệp ');
INSERT INTO `chuan_lo` VALUES (31,'LO82018DL', 'Có kỹ năng giao tiếp');
INSERT INTO `chuan_lo` VALUES (32,'LO92018DL', 'Có kỹ năng ngoại ngữ');
INSERT INTO `chuan_lo` VALUES (33,'LO102018DL', 'Hiểu nhu cầu xã hội, tác động của các công nghệ khai thác dữ liệu lớn trong bối cảnh cách mạng công nghiệp 4.0. Có khả năng hình thành ý tưởng, phân tích, thiết kế, áp dụng các công cụ thống kê và triển khai các ứng dụng Khoa học dữ liệu vào thực tiễn đáp ứng nhu cầu xã hội, giải quyết các bài toán kinh tế,nâng cao sức cạnh tranh, phát triển, khởi nghiệp & sáng tạo');

INSERT INTO `chuan_lo` VALUES (34,'LO12015', 'Nắm vững kiến thức khoa học cơ bản và có khả năng vận dụng vào chuyên ngành CNTT');
INSERT INTO `chuan_lo` VALUES (35,'LO22015', 'Nắm vững kiến thức về công nghệ thông tin.');
INSERT INTO `chuan_lo` VALUES (36,'LO32015', 'Có kiến thức về dữ liệu, thông tin, và tri thức');
INSERT INTO `chuan_lo` VALUES (37,'LO42015', 'Có kiến thức và khả năng lập trình và phát triển phần mềm.');
INSERT INTO `chuan_lo` VALUES (38,'LO52015', ' Có kiến thức về phân tích, tích hợp, và áp dụng cho chuyên môn.');
INSERT INTO `chuan_lo` VALUES (39,'LO62015', 'Nắm vững các kiến thức, công cụ phù hợp để quản lý và ứng dụng công nghệ thông tin vào lĩnh vực thực tế.');
INSERT INTO `chuan_lo` VALUES (40,'LO72015', 'Có kiến thức về quản lý nguồn tài nguyên, các hoạt động của cơ quan / tổ chức, các giải pháp sử dụng công nghệ thông tin để nâng cao khả năng lãnh đạo, quản lý của cơ quan / tổ chức.');
INSERT INTO `chuan_lo` VALUES (41,'LO82015', 'Trình độ Anh văn theo qui định chung của Trường.');
INSERT INTO `chuan_lo` VALUES (42,'LO92015', 'Kỹ năng quản lý đề án công nghệ thông tin và nhóm đề án.');
INSERT INTO `chuan_lo` VALUES (43,'LO102015', 'Kỹ năng trình bày công việc về công nghệ thông tin đang làm, phản biện dựa trên thực nghiệm, kỹ năng sáng tạo, kỹ năng giải quyết vấn đề.');
INSERT INTO `chuan_lo` VALUES (44,'LO112015', 'Có kỹ năng giao tiếp xã hội, hợp tác và làm việc nhóm, chia sẻ tri thức và kinh nghiệm, khả năng điều hành nhóm công tác.');
INSERT INTO `chuan_lo` VALUES (45,'LO122015', 'Có ý thức vai trò, trách nhiệm, đạo đức nghề nghiệp trong xã hội, hành xử chuyên nghiệp, tôn trong cam kết, trung thực, uy tín, trung thành và có khả năng nhận thức, đánh giá các hiện tượng một cách logic và tích cực.');
INSERT INTO `chuan_lo` VALUES (46,'LO132015', 'Có nhận thức về sự cần thiết và khả năng tham gia vào việc học tập suốt đời, có kiến thức rộng để có thể làm việc hiệu quả trong bối cảnh những công nghệ mới liên tục xuất hiện để từ đó hiểu được tác động của các công nghệ mới trong bối cảnh xã hội, kinh tế toàn cầu.');

INSERT INTO `chuan_lo` VALUES (47,'LO12018', 'Vận dụng kiến thức nền tảng về khoa học tự nhiên và khoa học xã hội để giải quyết vấn đề liên quan chuyên ngành.');
INSERT INTO `chuan_lo` VALUES (48,'LO22018', 'Vận dụng kiến thức nền tảng của ngành Công nghệ Thông tin và ứng dụng vào thực tiễn liên quan đến dữ liệu, thông tin, tri thức, và kỹ thuật công nghệ mới.');
INSERT INTO `chuan_lo` VALUES (49,'LO32018', 'Phân tích, lập luận,và giải quyết vấn đề liên quan đến ngành Công nghệ Thông tin (quản lý nguồn tài nguyên, các hoạt động của cơ quan/doanh nghiệp, các giải pháp sử dụng công nghệ thông tin để nâng cao khả năng lãnh đạo, quản lý, hoạt động của cơ quan/doanh nghiệp).');
INSERT INTO `chuan_lo` VALUES (50,'LO42018', 'Biếtkỹ năng nghiên cứu khoa học (khảo sát tài liệu, phân tích, đánh giá, vận dụng các công trình khoa học).');
INSERT INTO `chuan_lo` VALUES (51,'LO52018', ' Hiểu và nhận thứctư duy hệ thống, phân tích, thiết kế, đánh giá các thành phần hoặc toàn hệ thống thuộc lĩnh vực ngành Công nghệ Thông tin,vận dụng nhanh các công nghệ, kỹ thuật, công cụ phù hợp để quản lý và ứng dụng công nghệ thông tin vào lĩnh vực thực tế.');
INSERT INTO `chuan_lo` VALUES (52,'LO62018', ' Hiểu về sự cần thiết để học tập suốt đời, hiểu biết về các giá trị đạo đức và trách nhiệm nghề nghiệp.');
INSERT INTO `chuan_lo` VALUES (53,'LO72018', 'Vận dụng đượckỹ năng làm việc nhóm (thành lập, điều hành và duy trì công tác nhóm).');
INSERT INTO `chuan_lo` VALUES (54,'LO82018', 'Vận dụng đượckỹ năng giao tiếp (kỹ năng làm chủ đối thoại, thuyết trình tốt).');
INSERT INTO `chuan_lo` VALUES (55,'LO92018', 'Có năng lực ngoại ngữ bậc 3/6 Khung năng lực ngoại ngữ của Việt Nam (kỹ năng nghe nói, đọc hiểu tài liệu, viết khá tốt tiếng Anh).');
INSERT INTO `chuan_lo` VALUES (56,'LO102018', 'Nhận biết bối cảnh và nhu cầu xã hội, xác định ý tưởng, thiết kế, xây dựng,triển khai, vận hành ứng dụng các hệ thống Công nghệ Thông tin đáp ứng nhu cầu xã hội. Khả năng xây dựng tốt ý tưởng, thiết kế, phát triển, triển khai, vận hành.');


INSERT INTO `users`( pwd, salt, user_type, gv_id, sv_id, roles)
VALUES ('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,1,NULL,'LECTURER;ADMIN'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,2,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,3,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,4,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,5,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,6,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,7,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,8,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,9,NULL,'LECTURER'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',0,10,NULL,'LECTURER'),

('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,1,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,2,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,3,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,4,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,5,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,6,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,7,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,8,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,9,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,10,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,11,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,12,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,13,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,14,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,15,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,16,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,17,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,18,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,19,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,20,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,21,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,22,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,23,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,24,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,25,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,26,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,27,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,28,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,29,'STUDENT'),
('TEONLrFn9EhIra9NofcT82AGzMm2pGgS+bwS95OZie0=','HeiuhUQ2Nm',1,NULL,30,'STUDENT');

INSERT INTO `hoc_ky` VALUES (1,'HỌC KỲ 1', 2015,2016);
INSERT INTO `hoc_ky` VALUES (2,'HỌC KỲ 2', 2015,2016);
INSERT INTO `hoc_ky` VALUES (3,'HỌC KỲ HÈ', 2015,2016);

INSERT INTO `hoc_ky` VALUES (4,'HỌC KỲ 1', 2016,2017);
INSERT INTO `hoc_ky` VALUES (5,'HỌC KỲ 2', 2016,2017);
INSERT INTO `hoc_ky` VALUES (6,'HỌC KỲ HÈ', 2016,2017);

INSERT INTO `hoc_ky` VALUES (7,'HỌC KỲ 1', 2017,2018);
INSERT INTO `hoc_ky` VALUES (8,'HỌC KỲ 2', 2017,2018);
INSERT INTO `hoc_ky` VALUES (9,'HỌC KỲ HÈ', 2017,2018);

INSERT INTO `hoc_ky` VALUES (10,'HỌC KỲ 1', 2018,2019);
INSERT INTO `hoc_ky` VALUES (11,'HỌC KỲ 2', 2018,2019);
INSERT INTO `hoc_ky` VALUES (12,'HỌC KỲ HÈ', 2018,2019);


INSERT INTO `lop` VALUES (1,'LOP1', 1, 1, 1);
INSERT INTO `lop` VALUES (2,'LOP2', 1, 2, 2);
INSERT INTO `lop` VALUES (3,'LOP3', 1, 3, 3);
INSERT INTO `lop` VALUES (4,'LOP4', 1, 4, 4);
INSERT INTO `lop` VALUES (5,'LOP5', 1, 5, 5);

INSERT INTO `lop` VALUES (6,'LOP6', 2, 6, 6);
INSERT INTO `lop` VALUES (7,'LOP7', 2, 7, 7);
INSERT INTO `lop` VALUES (8,'LOP8', 2, 8, 8);
INSERT INTO `lop` VALUES (9,'LOP9', 2, 9, 9);
INSERT INTO `lop` VALUES (10,'LOP10', 2, 10, 10);

INSERT INTO `lop` VALUES (11,'LOP11', 3, 11, 1);
INSERT INTO `lop` VALUES (12,'LOP12', 3, 12, 2);
INSERT INTO `lop` VALUES (13,'LOP13', 3, 13, 3);
INSERT INTO `lop` VALUES (14,'LOP14', 3, 14, 4);
INSERT INTO `lop` VALUES (15,'LOP15', 3, 15, 5);

INSERT INTO `lop` VALUES (16,'LOP16', 4, 16, 6);
INSERT INTO `lop` VALUES (17,'LOP17', 4, 17, 7);
INSERT INTO `lop` VALUES (18,'LOP18', 4, 18, 8);
INSERT INTO `lop` VALUES (19,'LOP19', 4, 19, 9);
INSERT INTO `lop` VALUES (20,'LOP20', 4, 20, 10);

INSERT INTO `lop` VALUES (21,'LOP21', 5, 1, 1);
INSERT INTO `lop` VALUES (22,'LOP22', 5, 2, 2);
INSERT INTO `lop` VALUES (23,'LOP23', 5, 3, 3);
INSERT INTO `lop` VALUES (24,'LOP24', 5, 4, 4);
INSERT INTO `lop` VALUES (25,'LOP25', 5, 5, 5);

INSERT INTO `lop` VALUES (26,'LOP26', 6, 6, 6);
INSERT INTO `lop` VALUES (27,'LOP27', 6, 7, 7);
INSERT INTO `lop` VALUES (28,'LOP28', 6, 8, 8);
INSERT INTO `lop` VALUES (29,'LOP29', 6, 9, 9);
INSERT INTO `lop` VALUES (30,'LOP30', 6, 10, 10);

INSERT INTO `lop` VALUES (31,'LOP31', 7, 11, 1);
INSERT INTO `lop` VALUES (32,'LOP32', 7, 12, 2);
INSERT INTO `lop` VALUES (33,'LOP33', 7, 13, 3);
INSERT INTO `lop` VALUES (34,'LOP34', 7, 14, 4);
INSERT INTO `lop` VALUES (35,'LOP35', 7, 15, 5);

INSERT INTO `lop` VALUES (36,'LOP36', 8, 16, 6);
INSERT INTO `lop` VALUES (37,'LOP37', 8, 17, 7);
INSERT INTO `lop` VALUES (38,'LOP38', 8, 18, 8);
INSERT INTO `lop` VALUES (39,'LOP39', 8, 19, 9);
INSERT INTO `lop` VALUES (40,'LOP40', 8, 20, 10);

INSERT INTO `lop` VALUES (41,'LOP41', 9, 1, 1);
INSERT INTO `lop` VALUES (42,'LOP42', 9, 2, 2);
INSERT INTO `lop` VALUES (43,'LOP43', 9, 3, 3);
INSERT INTO `lop` VALUES (44,'LOP44', 9, 4, 4);
INSERT INTO `lop` VALUES (45,'LOP45', 9, 5, 5);

INSERT INTO `lop` VALUES (46,'LOP46', 10, 6, 6);
INSERT INTO `lop` VALUES (47,'LOP47', 10, 7, 7);
INSERT INTO `lop` VALUES (48,'LOP48', 10, 8, 8);
INSERT INTO `lop` VALUES (49,'LOP49', 10, 9, 9);
INSERT INTO `lop` VALUES (50,'LOP50', 10, 10, 10);

INSERT INTO `lop` VALUES (51,'LOP51', 11, 11, 1);
INSERT INTO `lop` VALUES (52,'LOP52', 11, 12, 2);
INSERT INTO `lop` VALUES (53,'LOP53', 11, 13, 3);
INSERT INTO `lop` VALUES (54,'LOP54', 11, 14, 4);
INSERT INTO `lop` VALUES (55,'LOP55', 11, 15, 5);

INSERT INTO `lop` VALUES (56,'LOP56', 12, 16, 6);
INSERT INTO `lop` VALUES (57,'LOP57', 12, 17, 7);
INSERT INTO `lop` VALUES (58,'LOP58', 12, 18, 8);
INSERT INTO `lop` VALUES (59,'LOP59', 12, 19, 9);
INSERT INTO `lop` VALUES (60,'LOP60', 12, 20, 10);

INSERT INTO `lo_nganh` VALUES (1, 34, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (2, 35, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (3, 36, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (4, 37, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (5, 38, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (6, 39, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (7, 40, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (8, 41, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (9, 42, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (10, 43, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (11, 44, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (12, 45, 1,'2015-2019');
INSERT INTO `lo_nganh` VALUES (13, 46, 1,'2015-2019');

INSERT INTO `lo_nganh` VALUES (14, 1, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (15, 2, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (16, 3, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (17, 4, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (18, 5, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (19, 6, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (20, 7, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (21, 8, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (22, 9, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (23, 10, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (24, 11, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (25, 12, 1,'2016-2020');
INSERT INTO `lo_nganh` VALUES (26, 13, 1,'2016-2020');

INSERT INTO `lo_nganh` VALUES (27, 14, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (28, 15, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (29, 16, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (30, 17, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (31, 18, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (32, 19, 1,'2017-20210');
INSERT INTO `lo_nganh` VALUES (33, 20, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (34, 21, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (35, 22, 1,'2017-2021');
INSERT INTO `lo_nganh` VALUES (36, 23, 1,'2017-2021');

INSERT INTO `lo_nganh` VALUES (37, 47, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (38, 48, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (39, 49, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (40, 50, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (41, 51, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (42, 52, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (43, 53, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (44, 54, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (45, 55, 1,'2018-2022');
INSERT INTO `lo_nganh` VALUES (46, 56, 1,'2018-2022');

INSERT INTO `lo_nganh` VALUES (47, 24, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (48, 25, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (49, 26, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (50, 27, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (51, 28, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (52, 29, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (53, 30, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (54, 31, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (55, 32, 2,'2018-2022');
INSERT INTO `lo_nganh` VALUES (56, 33, 2,'2018-2022');


INSERT INTO `sinhvien_lop` VALUES (1, 1, 1,7.5);
INSERT INTO `sinhvien_lop` VALUES (2, 1, 2,7.0);
INSERT INTO `sinhvien_lop` VALUES (3, 1, 3,6.0);
INSERT INTO `sinhvien_lop` VALUES (4, 1, 4,9.0);
INSERT INTO `sinhvien_lop` VALUES (5, 1, 5,9.5);
INSERT INTO `sinhvien_lop` VALUES (6, 2, 6,4.5);
INSERT INTO `sinhvien_lop` VALUES (7, 2, 7,5.0);
INSERT INTO `sinhvien_lop` VALUES (8, 2, 8,10);
INSERT INTO `sinhvien_lop` VALUES (9, 2, 9,8.0);
INSERT INTO `sinhvien_lop` VALUES (10, 2, 10,7.0);
INSERT INTO `sinhvien_lop` VALUES (11, 3, 11,8.0);
INSERT INTO `sinhvien_lop` VALUES (12, 3, 12,10);
INSERT INTO `sinhvien_lop` VALUES (13, 3, 13,9.0);
INSERT INTO `sinhvien_lop` VALUES (14, 3, 14,6.5);
INSERT INTO `sinhvien_lop` VALUES (15, 3, 15,7.0);
INSERT INTO `sinhvien_lop` VALUES (16, 4, 16,8.0);
INSERT INTO `sinhvien_lop` VALUES (17, 4, 17,7.5);
INSERT INTO `sinhvien_lop` VALUES (18, 4, 18,10);
INSERT INTO `sinhvien_lop` VALUES (19, 4, 19,9.0);
INSERT INTO `sinhvien_lop` VALUES (20, 4, 20,4.0);
INSERT INTO `sinhvien_lop` VALUES (21, 5, 1,5.0);
INSERT INTO `sinhvien_lop` VALUES (22, 5, 2,7.0);
INSERT INTO `sinhvien_lop` VALUES (23, 5, 3,9.0);
INSERT INTO `sinhvien_lop` VALUES (24, 5, 4,6.5);
INSERT INTO `sinhvien_lop` VALUES (25, 5, 5,8.5);
INSERT INTO `sinhvien_lop` VALUES (26, 6, 6,9.0);
INSERT INTO `sinhvien_lop` VALUES (27, 6, 7,10);
INSERT INTO `sinhvien_lop` VALUES (28, 6, 8,9.5);
INSERT INTO `sinhvien_lop` VALUES (29, 6, 9,8.0);
INSERT INTO `sinhvien_lop` VALUES (30, 6, 10,7.0);
INSERT INTO `sinhvien_lop` VALUES (31, 7, 11,7.5);
INSERT INTO `sinhvien_lop` VALUES (32, 7, 12,8.5);
INSERT INTO `sinhvien_lop` VALUES (33, 7, 13,10);
INSERT INTO `sinhvien_lop` VALUES (34, 7, 14,9.0);
INSERT INTO `sinhvien_lop` VALUES (35, 7, 15,8.0);
INSERT INTO `sinhvien_lop` VALUES (36, 8, 16,9.0);
INSERT INTO `sinhvien_lop` VALUES (37, 8, 17,8.0);
INSERT INTO `sinhvien_lop` VALUES (38, 8, 18,5.0);
INSERT INTO `sinhvien_lop` VALUES (39, 8, 19,7.5);
INSERT INTO `sinhvien_lop` VALUES (40, 8, 20,9.5);
INSERT INTO `sinhvien_lop` VALUES (41, 9, 1,6.5);
INSERT INTO `sinhvien_lop` VALUES (42, 9, 2,7.5);
INSERT INTO `sinhvien_lop` VALUES (43, 9, 3,9.5);
INSERT INTO `sinhvien_lop` VALUES (44, 9, 4,5);
INSERT INTO `sinhvien_lop` VALUES (45, 9, 5,8.5);
INSERT INTO `sinhvien_lop` VALUES (46, 10, 6,8.0);
INSERT INTO `sinhvien_lop` VALUES (47, 10, 7,9.5);
INSERT INTO `sinhvien_lop` VALUES (48, 10, 8,6.5);
INSERT INTO `sinhvien_lop` VALUES (49, 10, 9,7.5);
INSERT INTO `sinhvien_lop` VALUES (50, 10, 10,8.5);
INSERT INTO `sinhvien_lop` VALUES (51, 11, 11,3.0);
INSERT INTO `sinhvien_lop` VALUES (52, 11, 12,5.5);
INSERT INTO `sinhvien_lop` VALUES (53, 11, 13,6.5);
INSERT INTO `sinhvien_lop` VALUES (54, 11, 14,9.0);
INSERT INTO `sinhvien_lop` VALUES (55, 11, 15,5.5);
INSERT INTO `sinhvien_lop` VALUES (56, 12, 16,8.0);
INSERT INTO `sinhvien_lop` VALUES (57, 12, 17,9.5);
INSERT INTO `sinhvien_lop` VALUES (58, 12, 18,10);
INSERT INTO `sinhvien_lop` VALUES (59, 12, 19,7.5);
INSERT INTO `sinhvien_lop` VALUES (60, 12, 20,6.5);
INSERT INTO `sinhvien_lop` VALUES (61, 13, 1,9.5);
INSERT INTO `sinhvien_lop` VALUES (62, 13, 2,4.0);
INSERT INTO `sinhvien_lop` VALUES (63, 13, 3,6.5);
INSERT INTO `sinhvien_lop` VALUES (64, 13, 4,8.0);
INSERT INTO `sinhvien_lop` VALUES (65, 13, 5,7.0);
INSERT INTO `sinhvien_lop` VALUES (66, 14, 6,9.5);
INSERT INTO `sinhvien_lop` VALUES (67, 14, 7,8.5);
INSERT INTO `sinhvien_lop` VALUES (68, 14, 8,6.5);
INSERT INTO `sinhvien_lop` VALUES (69, 14, 9,8.0);
INSERT INTO `sinhvien_lop` VALUES (70, 14, 10,5.0);
INSERT INTO `sinhvien_lop` VALUES (71, 15, 11,10);
INSERT INTO `sinhvien_lop` VALUES (72, 15, 12,10);
INSERT INTO `sinhvien_lop` VALUES (73, 15, 13,10);
INSERT INTO `sinhvien_lop` VALUES (74, 15, 14,6.5);
INSERT INTO `sinhvien_lop` VALUES (75, 15, 15,9.5);
INSERT INTO `sinhvien_lop` VALUES (76, 16, 16,7.5);
INSERT INTO `sinhvien_lop` VALUES (77, 16, 17,5.5);
INSERT INTO `sinhvien_lop` VALUES (78, 16, 18,7.5);
INSERT INTO `sinhvien_lop` VALUES (79, 16, 19,8.0);
INSERT INTO `sinhvien_lop` VALUES (80, 16, 20,8.5);
INSERT INTO `sinhvien_lop` VALUES (81, 17, 1,9.5);
INSERT INTO `sinhvien_lop` VALUES (82, 17, 2,6.5);
INSERT INTO `sinhvien_lop` VALUES (83, 17, 3,9.5);
INSERT INTO `sinhvien_lop` VALUES (84, 17, 4,7.5);
INSERT INTO `sinhvien_lop` VALUES (85, 17, 5,8.5);
INSERT INTO `sinhvien_lop` VALUES (86, 18, 6,6.5);
INSERT INTO `sinhvien_lop` VALUES (87, 18, 7,5.0);
INSERT INTO `sinhvien_lop` VALUES (88, 18, 8,6.5);
INSERT INTO `sinhvien_lop` VALUES (89, 18, 9,7.5);
INSERT INTO `sinhvien_lop` VALUES (90, 18, 10,8.5);
INSERT INTO `sinhvien_lop` VALUES (91, 19, 11,10);
INSERT INTO `sinhvien_lop` VALUES (92, 19, 12,5.5);
INSERT INTO `sinhvien_lop` VALUES (93, 19, 13,6.5);
INSERT INTO `sinhvien_lop` VALUES (94, 19, 14,8.5);
INSERT INTO `sinhvien_lop` VALUES (95, 19, 15,9.5);
INSERT INTO `sinhvien_lop` VALUES (96, 20, 16,5.5);
INSERT INTO `sinhvien_lop` VALUES (97, 20, 17,6.5);
INSERT INTO `sinhvien_lop` VALUES (98, 20, 18,7.5);
INSERT INTO `sinhvien_lop` VALUES (99, 20, 19,10);
INSERT INTO `sinhvien_lop` VALUES (100, 20, 20,8.5);

INSERT INTO `chuan_g` VALUES (1, 'G1','Hình thành và điều hành nhóm');
INSERT INTO `chuan_g` VALUES (2, 'G2','Tham gia thảo luận theo từng nhóm trên từng chủ đề của môn học.');
INSERT INTO `chuan_g` VALUES (3, 'G3','Hiểu được khái niệm khai thác dữ liệu là gì, sự cần thiết của việc khai thác dữ liệu.');
INSERT INTO `chuan_g` VALUES (4, 'G4','Biết được việc ứng dụng các kỹ thuật khai thác dữ liệu trong các lĩnh vực khác nhau của đời sống. ');
INSERT INTO `chuan_g` VALUES (5, 'G5','Hiểu và áp dụng được kỹ thuật tìm tập phổ biến, tìm tập phổ biến tối đại');
INSERT INTO `chuan_g` VALUES (6, 'G6','Hiểu và áp dụng thành thạo kỹ thuật tìm luật kết hợp dựa trên tập phổ biến tối đại, cách tính độ tin cậy và đề xuất giải pháp cho một bài toán cụ thể. Phân tích, đánh giá kết quả đạt được với kết quả khi dùng công cụ SQL Server');
INSERT INTO `chuan_g` VALUES (7, 'G7','Hiểu và áp dụng thành thạo kỹ thuật tìm luật kết hợp dựa trên dãy phổ biến và có khả năng đề xuất giải pháp cho một bài toán cụ thể. ');
INSERT INTO `chuan_g` VALUES (8, 'G8','Hiểu được và áp dụng các khái niệm: Tập thô là gì, quan hệ bất khả phân biệt, xấp xỉ trên, xấp xỉ dưới, quan hệ bất khả phân biệt ');
INSERT INTO `chuan_g` VALUES (9, 'G9','Kiến thức nền tảng Java cơ bản và hướng đối tượng.');
INSERT INTO `chuan_g` VALUES (10, 'G10','Hiểu, phân tích, thiết kế và vận dụng được công nghệ J2EE: lập trình Web với Servlet và JSP vào việc xây dựng một ứng dụng web.');
INSERT INTO `chuan_g` VALUES (11, 'G11','Có kỹ năng và tinh thần làm việc nhóm.');

INSERT INTO `mon_g` VALUES (1, 1, 1);
INSERT INTO `mon_g` VALUES (2, 1, 2);
INSERT INTO `mon_g` VALUES (3, 1, 3);
INSERT INTO `mon_g` VALUES (4, 2, 4);
INSERT INTO `mon_g` VALUES (5, 2, 5);
INSERT INTO `mon_g` VALUES (6, 2, 6);
INSERT INTO `mon_g` VALUES (7, 3, 7);
INSERT INTO `mon_g` VALUES (8, 3, 8);
INSERT INTO `mon_g` VALUES (9, 3, 9);
INSERT INTO `mon_g` VALUES (10, 4, 10);
INSERT INTO `mon_g` VALUES (11, 4, 11);
INSERT INTO `mon_g` VALUES (12, 4, 1);
INSERT INTO `mon_g` VALUES (13, 5, 2);
INSERT INTO `mon_g` VALUES (14, 5, 3);
INSERT INTO `mon_g` VALUES (15, 5, 4);
INSERT INTO `mon_g` VALUES (16, 6, 5);
INSERT INTO `mon_g` VALUES (17, 6, 6);
INSERT INTO `mon_g` VALUES (18, 6, 7);
INSERT INTO `mon_g` VALUES (19, 7, 8);
INSERT INTO `mon_g` VALUES (20, 7, 9);
INSERT INTO `mon_g` VALUES (21, 7, 10);
INSERT INTO `mon_g` VALUES (22, 8, 11);
INSERT INTO `mon_g` VALUES (23, 8, 1);
INSERT INTO `mon_g` VALUES (24, 8, 2);
INSERT INTO `mon_g` VALUES (25, 9, 3);
INSERT INTO `mon_g` VALUES (26, 9, 4);
INSERT INTO `mon_g` VALUES (27, 9, 5);
INSERT INTO `mon_g` VALUES (28, 10, 6);
INSERT INTO `mon_g` VALUES (29, 10, 7);
INSERT INTO `mon_g` VALUES (30, 10, 8);
INSERT INTO `mon_g` VALUES (31, 11, 9);
INSERT INTO `mon_g` VALUES (32, 11, 10);
INSERT INTO `mon_g` VALUES (33, 11, 11);
INSERT INTO `mon_g` VALUES (34, 12, 1);
INSERT INTO `mon_g` VALUES (35, 12, 2);
INSERT INTO `mon_g` VALUES (36, 12, 3);
INSERT INTO `mon_g` VALUES (37, 13, 4);
INSERT INTO `mon_g` VALUES (38, 13, 5);
INSERT INTO `mon_g` VALUES (39, 13, 6);
INSERT INTO `mon_g` VALUES (40, 14, 7);
INSERT INTO `mon_g` VALUES (41, 14, 8);
INSERT INTO `mon_g` VALUES (42, 14, 9);
INSERT INTO `mon_g` VALUES (43, 15, 10);
INSERT INTO `mon_g` VALUES (44, 15, 11);
INSERT INTO `mon_g` VALUES (45, 15, 1);
INSERT INTO `mon_g` VALUES (46, 16, 2);
INSERT INTO `mon_g` VALUES (47, 16, 3);
INSERT INTO `mon_g` VALUES (48, 16, 4);
INSERT INTO `mon_g` VALUES (49, 17, 5);
INSERT INTO `mon_g` VALUES (51, 17, 7);
INSERT INTO `mon_g` VALUES (52, 18, 8);
INSERT INTO `mon_g` VALUES (53, 18, 9);
INSERT INTO `mon_g` VALUES (54, 18, 10);
INSERT INTO `mon_g` VALUES (55, 19, 11);
INSERT INTO `mon_g` VALUES (56, 19, 1);
INSERT INTO `mon_g` VALUES (57, 19, 2);
INSERT INTO `mon_g` VALUES (58, 20, 3);
INSERT INTO `mon_g` VALUES (59, 20, 4);
INSERT INTO `mon_g` VALUES (60, 20, 5);
INSERT INTO `mon_g` VALUES (61, 1, 4);
INSERT INTO `mon_g` VALUES (62, 1, 5);
INSERT INTO `mon_g` VALUES (63, 1, 6);

INSERT INTO `g_lo` VALUES (1, 1, 1);
INSERT INTO `g_lo` VALUES (2, 2, 1);
INSERT INTO `g_lo` VALUES (3, 3, 2);
INSERT INTO `g_lo` VALUES (4, 4, 2);
INSERT INTO `g_lo` VALUES (5, 5, 3);
INSERT INTO `g_lo` VALUES (6, 6, 3);
INSERT INTO `g_lo` VALUES (7, 7, 4);
INSERT INTO `g_lo` VALUES (8, 8, 4);
INSERT INTO `g_lo` VALUES (9, 9, 5);
INSERT INTO `g_lo` VALUES (10, 10, 5);
INSERT INTO `g_lo` VALUES (11, 11, 6);
INSERT INTO `g_lo` VALUES (12, 1, 6);
INSERT INTO `g_lo` VALUES (13 ,2, 7);
INSERT INTO `g_lo` VALUES (14, 3, 7);
INSERT INTO `g_lo` VALUES (15, 4, 8);
INSERT INTO `g_lo` VALUES (16, 5, 8);
INSERT INTO `g_lo` VALUES (17, 6, 9);
INSERT INTO `g_lo` VALUES (18, 7, 9);
INSERT INTO `g_lo` VALUES (19, 8, 10);
INSERT INTO `g_lo` VALUES (20, 9, 10);
INSERT INTO `g_lo` VALUES (21, 10, 11);
INSERT INTO `g_lo` VALUES (22, 11, 11);
INSERT INTO `g_lo` VALUES (23, 1, 12);
INSERT INTO `g_lo` VALUES (24, 2, 12);
INSERT INTO `g_lo` VALUES (25, 3, 13);
INSERT INTO `g_lo` VALUES (26, 4, 13);
INSERT INTO `g_lo` VALUES (27, 5, 14);
INSERT INTO `g_lo` VALUES (28, 6, 14);
INSERT INTO `g_lo` VALUES (29, 7, 15);
INSERT INTO `g_lo` VALUES (30, 8, 15);
INSERT INTO `g_lo` VALUES (31, 9, 16);
INSERT INTO `g_lo` VALUES (32, 10, 16);
INSERT INTO `g_lo` VALUES (33, 11, 17);
INSERT INTO `g_lo` VALUES (34, 1, 17);
INSERT INTO `g_lo` VALUES (35, 2, 18);
INSERT INTO `g_lo` VALUES (36, 3, 18);
INSERT INTO `g_lo` VALUES (37, 4, 19);
INSERT INTO `g_lo` VALUES (38, 5, 19);
INSERT INTO `g_lo` VALUES (39, 6, 20);
INSERT INTO `g_lo` VALUES (40, 7, 20);
INSERT INTO `g_lo` VALUES (41, 8, 21);
INSERT INTO `g_lo` VALUES (42, 9, 21);
INSERT INTO `g_lo` VALUES (43, 10, 22);
INSERT INTO `g_lo` VALUES (44, 11, 22);
INSERT INTO `g_lo` VALUES (45, 1, 23);
INSERT INTO `g_lo` VALUES (46, 2, 23);
INSERT INTO `g_lo` VALUES (47, 3, 24);
INSERT INTO `g_lo` VALUES (48, 4, 24);
INSERT INTO `g_lo` VALUES (49, 5, 25);
INSERT INTO `g_lo` VALUES (50, 6, 25);
INSERT INTO `g_lo` VALUES (51, 7, 26);
INSERT INTO `g_lo` VALUES (52, 8, 26);
INSERT INTO `g_lo` VALUES (53, 9, 27);
INSERT INTO `g_lo` VALUES (54, 10, 27);
INSERT INTO `g_lo` VALUES (55, 11, 28);
INSERT INTO `g_lo` VALUES (56, 1, 28);
INSERT INTO `g_lo` VALUES (57, 2, 29);
INSERT INTO `g_lo` VALUES (58, 3, 29);
INSERT INTO `g_lo` VALUES (59, 4, 30);
INSERT INTO `g_lo` VALUES (60, 5, 30);
INSERT INTO `g_lo` VALUES (61, 6, 31);
INSERT INTO `g_lo` VALUES (62, 7, 31);
INSERT INTO `g_lo` VALUES (63, 8, 32);
INSERT INTO `g_lo` VALUES (64, 9, 32);
INSERT INTO `g_lo` VALUES (65, 10, 33);
INSERT INTO `g_lo` VALUES (66, 11, 33);
INSERT INTO `g_lo` VALUES (67, 1, 34);
INSERT INTO `g_lo` VALUES (68, 2, 34);
INSERT INTO `g_lo` VALUES (69, 3, 35);
INSERT INTO `g_lo` VALUES (70, 4, 35);
INSERT INTO `g_lo` VALUES (71, 5, 36);
INSERT INTO `g_lo` VALUES (72, 6, 36);
INSERT INTO `g_lo` VALUES (73, 7, 37);
INSERT INTO `g_lo` VALUES (74, 8, 37);
INSERT INTO `g_lo` VALUES (75, 9, 38);
INSERT INTO `g_lo` VALUES (76, 10,38);
INSERT INTO `g_lo` VALUES (77, 11, 39);
INSERT INTO `g_lo` VALUES (78, 1, 39);
INSERT INTO `g_lo` VALUES (79, 2, 40);
INSERT INTO `g_lo` VALUES (80, 3, 40);
INSERT INTO `g_lo` VALUES (81, 4, 41);
INSERT INTO `g_lo` VALUES (82, 5, 41);
INSERT INTO `g_lo` VALUES (83 ,6, 42);
INSERT INTO `g_lo` VALUES (84, 7, 42);
INSERT INTO `g_lo` VALUES (85, 8, 43);
INSERT INTO `g_lo` VALUES (86, 9, 43);
INSERT INTO `g_lo` VALUES (87, 10, 44);
INSERT INTO `g_lo` VALUES (88, 11, 44);
INSERT INTO `g_lo` VALUES (89, 1, 45);
INSERT INTO `g_lo` VALUES (90, 2, 45);
INSERT INTO `g_lo` VALUES (91, 3, 46);
INSERT INTO `g_lo` VALUES (92, 4, 46);
INSERT INTO `g_lo` VALUES (93, 5, 47);
INSERT INTO `g_lo` VALUES (94, 6, 47);
INSERT INTO `g_lo` VALUES (95, 7, 48);
INSERT INTO `g_lo` VALUES (96, 8, 48);
INSERT INTO `g_lo` VALUES (97, 9, 49);
INSERT INTO `g_lo` VALUES (98, 10, 50);
INSERT INTO `g_lo` VALUES (99, 11, 50);
INSERT INTO `g_lo` VALUES (100, 1, 51);
INSERT INTO `g_lo` VALUES (101, 2, 51);
INSERT INTO `g_lo` VALUES (102, 3, 52);
INSERT INTO `g_lo` VALUES (103, 4, 52);
INSERT INTO `g_lo` VALUES (104, 5, 53);
INSERT INTO `g_lo` VALUES (105, 6, 53);
INSERT INTO `g_lo` VALUES (106, 7, 54);
INSERT INTO `g_lo` VALUES (107, 8, 54);
INSERT INTO `g_lo` VALUES (108, 9, 55);
INSERT INTO `g_lo` VALUES (109, 10, 55);
INSERT INTO `g_lo` VALUES (110, 11, 56);
INSERT INTO `g_lo` VALUES (111, 1, 56);

INSERT INTO `dethi` VALUES (1, 0, 1);
INSERT INTO `dethi` VALUES (2, 1, 1);
INSERT INTO `dethi` VALUES (3, 0, 2);
INSERT INTO `dethi` VALUES (4, 1, 2);
INSERT INTO `dethi` VALUES (5, 0, 3);
INSERT INTO `dethi` VALUES (6, 1, 3);
INSERT INTO `dethi` VALUES (7, 0, 4);
INSERT INTO `dethi` VALUES (8, 1, 4);
INSERT INTO `dethi` VALUES (9, 0, 5);
INSERT INTO `dethi` VALUES (10, 1, 5);
INSERT INTO `dethi` VALUES (11, 0, 6);
INSERT INTO `dethi` VALUES (12, 1, 6);
INSERT INTO `dethi` VALUES (13, 0, 7);
INSERT INTO `dethi` VALUES (14, 1, 7);
INSERT INTO `dethi` VALUES (15, 0, 8);
INSERT INTO `dethi` VALUES (16, 1, 8);
INSERT INTO `dethi` VALUES (17, 0, 9);
INSERT INTO `dethi` VALUES (18, 1, 9);
INSERT INTO `dethi` VALUES (19, 0, 10);
INSERT INTO `dethi` VALUES (20, 1, 10);
INSERT INTO `dethi` VALUES (21, 0, 11);
INSERT INTO `dethi` VALUES (22, 1, 11);
INSERT INTO `dethi` VALUES (23, 0, 12);
INSERT INTO `dethi` VALUES (24, 1, 12);
INSERT INTO `dethi` VALUES (25, 0, 13);
INSERT INTO `dethi` VALUES (26, 1, 13);
INSERT INTO `dethi` VALUES (27, 0, 14);
INSERT INTO `dethi` VALUES (28, 1, 14);
INSERT INTO `dethi` VALUES (29, 0, 15);
INSERT INTO `dethi` VALUES (30, 1, 15);
INSERT INTO `dethi` VALUES (31, 0, 16);
INSERT INTO `dethi` VALUES (32, 1, 16);
INSERT INTO `dethi` VALUES (33, 0, 17);
INSERT INTO `dethi` VALUES (34, 1, 17);
INSERT INTO `dethi` VALUES (35, 0, 18);
INSERT INTO `dethi` VALUES (36, 1, 18);
INSERT INTO `dethi` VALUES (37, 0, 19);
INSERT INTO `dethi` VALUES (38, 1, 19);
INSERT INTO `dethi` VALUES (39, 0, 20);
INSERT INTO `dethi` VALUES (40, 1, 20);
INSERT INTO `dethi` VALUES (41, 0, 21);
INSERT INTO `dethi` VALUES (42, 1, 21);
INSERT INTO `dethi` VALUES (43, 0, 22);
INSERT INTO `dethi` VALUES (44, 1, 22);
INSERT INTO `dethi` VALUES (45, 0, 23);
INSERT INTO `dethi` VALUES (46, 1, 23);
INSERT INTO `dethi` VALUES (47, 0, 24);
INSERT INTO `dethi` VALUES (48, 1, 24);


INSERT INTO `cau_hoi` VALUES (1, 1, 4, 1);
INSERT INTO `cau_hoi` VALUES (2, 2, 6, 1);
INSERT INTO `cau_hoi` VALUES (3, 1, 3, 2);
INSERT INTO `cau_hoi` VALUES (4, 2, 7, 2);
INSERT INTO `cau_hoi` VALUES (5, 1, 4, 3);
INSERT INTO `cau_hoi` VALUES (6, 2, 6, 3);
INSERT INTO `cau_hoi` VALUES (7, 1, 3, 4);
INSERT INTO `cau_hoi` VALUES (8, 2, 7, 4);
INSERT INTO `cau_hoi` VALUES (9, 1, 4, 5);
INSERT INTO `cau_hoi` VALUES (10, 2, 6, 5);
INSERT INTO `cau_hoi` VALUES (11, 1, 3, 6);
INSERT INTO `cau_hoi` VALUES (12, 2, 7, 6);
INSERT INTO `cau_hoi` VALUES (13, 1, 4, 7);
INSERT INTO `cau_hoi` VALUES (14, 2, 6, 7);
INSERT INTO `cau_hoi` VALUES (15, 1, 3, 8);
INSERT INTO `cau_hoi` VALUES (16, 2, 7, 8);
INSERT INTO `cau_hoi` VALUES (17, 1, 4, 9);
INSERT INTO `cau_hoi` VALUES (18, 2, 6, 9);
INSERT INTO `cau_hoi` VALUES (19, 1, 3, 10);
INSERT INTO `cau_hoi` VALUES (20, 2, 7, 10);
INSERT INTO `cau_hoi` VALUES (21, 1, 4, 11);
INSERT INTO `cau_hoi` VALUES (22, 2, 6, 11);
INSERT INTO `cau_hoi` VALUES (23, 1, 3, 12);
INSERT INTO `cau_hoi` VALUES (24, 2, 7, 12);
INSERT INTO `cau_hoi` VALUES (25, 1, 4, 13);
INSERT INTO `cau_hoi` VALUES (26, 2, 6, 13);
INSERT INTO `cau_hoi` VALUES (27, 1, 3, 14);
INSERT INTO `cau_hoi` VALUES (28, 2, 7, 14);
INSERT INTO `cau_hoi` VALUES (29, 1, 4, 15);
INSERT INTO `cau_hoi` VALUES (30, 2, 6, 15);
INSERT INTO `cau_hoi` VALUES (31, 1, 3, 16);
INSERT INTO `cau_hoi` VALUES (32, 2, 7, 16);
INSERT INTO `cau_hoi` VALUES (33, 1, 4, 17);
INSERT INTO `cau_hoi` VALUES (34, 2, 6, 17);
INSERT INTO `cau_hoi` VALUES (35, 1, 3, 18);
INSERT INTO `cau_hoi` VALUES (36, 2, 7, 18);
INSERT INTO `cau_hoi` VALUES (37, 1, 4, 19);
INSERT INTO `cau_hoi` VALUES (38, 2, 6, 19);
INSERT INTO `cau_hoi` VALUES (39, 1, 3, 20);
INSERT INTO `cau_hoi` VALUES (40, 2, 7, 20);
INSERT INTO `cau_hoi` VALUES (41, 1, 4, 21);
INSERT INTO `cau_hoi` VALUES (42, 2, 6, 21);
INSERT INTO `cau_hoi` VALUES (43, 1, 3, 22);
INSERT INTO `cau_hoi` VALUES (44, 2, 7, 22);
INSERT INTO `cau_hoi` VALUES (45, 1, 4, 23);
INSERT INTO `cau_hoi` VALUES (46, 2, 6, 23);
INSERT INTO `cau_hoi` VALUES (47, 1, 3, 24);
INSERT INTO `cau_hoi` VALUES (48, 2, 7, 24);
INSERT INTO `cau_hoi` VALUES (49, 1, 4, 25);
INSERT INTO `cau_hoi` VALUES (50, 2, 6, 25);
INSERT INTO `cau_hoi` VALUES (51, 1, 3, 26);
INSERT INTO `cau_hoi` VALUES (52, 2, 7, 26);
INSERT INTO `cau_hoi` VALUES (53, 1, 4, 27);
INSERT INTO `cau_hoi` VALUES (54, 2, 6, 27);
INSERT INTO `cau_hoi` VALUES (55, 1, 3, 28);
INSERT INTO `cau_hoi` VALUES (56, 2, 7, 28);
INSERT INTO `cau_hoi` VALUES (57, 1, 4, 29);
INSERT INTO `cau_hoi` VALUES (58, 2, 6, 29);
INSERT INTO `cau_hoi` VALUES (59, 1, 3, 30);
INSERT INTO `cau_hoi` VALUES (60, 2, 7, 30);
INSERT INTO `cau_hoi` VALUES (61, 1, 4, 31);
INSERT INTO `cau_hoi` VALUES (62, 2, 6, 31);
INSERT INTO `cau_hoi` VALUES (63, 1, 3, 32);
INSERT INTO `cau_hoi` VALUES (64, 2, 7, 32);
INSERT INTO `cau_hoi` VALUES (65, 1, 4, 33);
INSERT INTO `cau_hoi` VALUES (66, 2, 6, 33);
INSERT INTO `cau_hoi` VALUES (67, 1, 3, 34);
INSERT INTO `cau_hoi` VALUES (68, 2, 7, 34);
INSERT INTO `cau_hoi` VALUES (69, 1, 4, 35);
INSERT INTO `cau_hoi` VALUES (70, 2, 6, 35);
INSERT INTO `cau_hoi` VALUES (71, 1, 3, 36);
INSERT INTO `cau_hoi` VALUES (72, 2, 7, 36);
INSERT INTO `cau_hoi` VALUES (73, 1, 4, 37);
INSERT INTO `cau_hoi` VALUES (74, 2, 6, 37);
INSERT INTO `cau_hoi` VALUES (75, 1, 3, 38);
INSERT INTO `cau_hoi` VALUES (76, 2, 7, 38);
INSERT INTO `cau_hoi` VALUES (77, 1, 4, 39);
INSERT INTO `cau_hoi` VALUES (78, 2, 6, 39);
INSERT INTO `cau_hoi` VALUES (79, 1, 3, 40);
INSERT INTO `cau_hoi` VALUES (80, 2, 7, 40);
INSERT INTO `cau_hoi` VALUES (81, 1, 4, 41);
INSERT INTO `cau_hoi` VALUES (82, 2, 6, 41);
INSERT INTO `cau_hoi` VALUES (83, 1, 3, 42);
INSERT INTO `cau_hoi` VALUES (84, 2, 7, 42);
INSERT INTO `cau_hoi` VALUES (85, 1, 4, 43);
INSERT INTO `cau_hoi` VALUES (86, 2, 6, 43);
INSERT INTO `cau_hoi` VALUES (87, 1, 3, 44);
INSERT INTO `cau_hoi` VALUES (88, 2, 7, 44);
INSERT INTO `cau_hoi` VALUES (89, 1, 4, 45);
INSERT INTO `cau_hoi` VALUES (90, 2, 6, 45);
INSERT INTO `cau_hoi` VALUES (91, 1, 3, 46);
INSERT INTO `cau_hoi` VALUES (92, 2, 7, 46);
INSERT INTO `cau_hoi` VALUES (93, 1, 4, 47);
INSERT INTO `cau_hoi` VALUES (94, 2, 6, 47);
INSERT INTO `cau_hoi` VALUES (95, 1, 3, 48);
INSERT INTO `cau_hoi` VALUES (96, 2, 7, 48);

INSERT INTO `cauhoi_g` VALUES (1, 1, 1);
INSERT INTO `cauhoi_g` VALUES (2, 2, 2);
INSERT INTO `cauhoi_g` VALUES (3, 2, 3);
INSERT INTO `cauhoi_g` VALUES (4, 3, 4);
INSERT INTO `cauhoi_g` VALUES (5, 4, 6);
INSERT INTO `cauhoi_g` VALUES (6, 4, 5);
INSERT INTO `cauhoi_g` VALUES (7, 5, 7);
INSERT INTO `cauhoi_g` VALUES (8, 6, 9);
INSERT INTO `cauhoi_g` VALUES (9, 6, 8);
INSERT INTO `cauhoi_g` VALUES (10, 7, 10);
INSERT INTO `cauhoi_g` VALUES (11, 8, 11);
INSERT INTO `cauhoi_g` VALUES (12, 8, 3);
INSERT INTO `cauhoi_g` VALUES (13, 9, 1);
INSERT INTO `cauhoi_g` VALUES (14, 10, 2);
INSERT INTO `cauhoi_g` VALUES (15, 10, 3);
INSERT INTO `cauhoi_g` VALUES (16, 11, 4);
INSERT INTO `cauhoi_g` VALUES (17, 12, 5);
INSERT INTO `cauhoi_g` VALUES (18, 12, 6);
INSERT INTO `cauhoi_g` VALUES (19, 13, 7);
INSERT INTO `cauhoi_g` VALUES (20, 14, 8);
INSERT INTO `cauhoi_g` VALUES (21, 14, 9);
INSERT INTO `cauhoi_g` VALUES (22, 15, 10);
INSERT INTO `cauhoi_g` VALUES (23, 16, 11);
INSERT INTO `cauhoi_g` VALUES (24, 16, 1);
INSERT INTO `cauhoi_g` VALUES (25, 17, 1);
INSERT INTO `cauhoi_g` VALUES (26, 18, 2);
INSERT INTO `cauhoi_g` VALUES (27, 18, 3);
INSERT INTO `cauhoi_g` VALUES (28, 19, 4);
INSERT INTO `cauhoi_g` VALUES (29, 20, 6);
INSERT INTO `cauhoi_g` VALUES (30, 20,5);

INSERT INTO `cauhoi_g` VALUES (31, 21, 1);
INSERT INTO `cauhoi_g` VALUES (32, 22, 2);
INSERT INTO `cauhoi_g` VALUES (33, 22, 3);
INSERT INTO `cauhoi_g` VALUES (34, 23, 4);
INSERT INTO `cauhoi_g` VALUES (35, 24, 6);
INSERT INTO `cauhoi_g` VALUES (36, 24, 5);
INSERT INTO `cauhoi_g` VALUES (37, 25, 7);
INSERT INTO `cauhoi_g` VALUES (38, 26, 9);
INSERT INTO `cauhoi_g` VALUES (39, 26, 8);
INSERT INTO `cauhoi_g` VALUES (40, 27, 10);
INSERT INTO `cauhoi_g` VALUES (41, 28, 11);
INSERT INTO `cauhoi_g` VALUES (42, 28, 3);
INSERT INTO `cauhoi_g` VALUES (43, 29, 1);
INSERT INTO `cauhoi_g` VALUES (44, 30, 2);
INSERT INTO `cauhoi_g` VALUES (45, 30, 3);
INSERT INTO `cauhoi_g` VALUES (46, 31, 4);
INSERT INTO `cauhoi_g` VALUES (47, 32, 5);
INSERT INTO `cauhoi_g` VALUES (48, 32, 6);
INSERT INTO `cauhoi_g` VALUES (49, 33, 7);
INSERT INTO `cauhoi_g` VALUES (50, 34, 8);
INSERT INTO `cauhoi_g` VALUES (51, 34, 9);
INSERT INTO `cauhoi_g` VALUES (52, 35, 10);
INSERT INTO `cauhoi_g` VALUES (53, 36, 11);
INSERT INTO `cauhoi_g` VALUES (54, 36, 1);
INSERT INTO `cauhoi_g` VALUES (55, 37, 1);
INSERT INTO `cauhoi_g` VALUES (56, 38, 2);
INSERT INTO `cauhoi_g` VALUES (57, 38, 3);
INSERT INTO `cauhoi_g` VALUES (58, 39, 4);
INSERT INTO `cauhoi_g` VALUES (59, 40, 6);
INSERT INTO `cauhoi_g` VALUES (60, 40, 3);

INSERT INTO `cauhoi_g` VALUES (61, 41, 1);
INSERT INTO `cauhoi_g` VALUES (62, 42, 2);
INSERT INTO `cauhoi_g` VALUES (63, 42, 3);
INSERT INTO `cauhoi_g` VALUES (64, 43, 4);
INSERT INTO `cauhoi_g` VALUES (65, 44, 6);
INSERT INTO `cauhoi_g` VALUES (66, 44, 5);
INSERT INTO `cauhoi_g` VALUES (67, 45, 7);
INSERT INTO `cauhoi_g` VALUES (68, 46, 9);
INSERT INTO `cauhoi_g` VALUES (69, 46, 8);
INSERT INTO `cauhoi_g` VALUES (70, 47, 10);
INSERT INTO `cauhoi_g` VALUES (71, 48, 11);
INSERT INTO `cauhoi_g` VALUES (72, 48, 3);
INSERT INTO `cauhoi_g` VALUES (73, 49, 1);
INSERT INTO `cauhoi_g` VALUES (74, 50, 2);
INSERT INTO `cauhoi_g` VALUES (75, 50, 3);
INSERT INTO `cauhoi_g` VALUES (76, 51, 4);
INSERT INTO `cauhoi_g` VALUES (77, 52, 5);
INSERT INTO `cauhoi_g` VALUES (78, 52, 6);
INSERT INTO `cauhoi_g` VALUES (79, 53, 7);
INSERT INTO `cauhoi_g` VALUES (80, 54, 8);
INSERT INTO `cauhoi_g` VALUES (81, 54, 9);
INSERT INTO `cauhoi_g` VALUES (82, 55, 10);
INSERT INTO `cauhoi_g` VALUES (83, 56, 11);
INSERT INTO `cauhoi_g` VALUES (84, 56, 1);
INSERT INTO `cauhoi_g` VALUES (85, 57, 1);
INSERT INTO `cauhoi_g` VALUES (86, 58, 2);
INSERT INTO `cauhoi_g` VALUES (87, 58, 3);
INSERT INTO `cauhoi_g` VALUES (88, 59, 4);
INSERT INTO `cauhoi_g` VALUES (89, 60, 6);
INSERT INTO `cauhoi_g` VALUES (90, 60, 5);

INSERT INTO `cauhoi_g` VALUES (91, 71, 1);
INSERT INTO `cauhoi_g` VALUES (92, 72, 2);
INSERT INTO `cauhoi_g` VALUES (93, 72, 3);
INSERT INTO `cauhoi_g` VALUES (94, 73, 4);
INSERT INTO `cauhoi_g` VALUES (95, 74, 6);
INSERT INTO `cauhoi_g` VALUES (96, 74, 5);
INSERT INTO `cauhoi_g` VALUES (97, 75, 7);
INSERT INTO `cauhoi_g` VALUES (98, 76, 9);
INSERT INTO `cauhoi_g` VALUES (99, 76, 8);
INSERT INTO `cauhoi_g` VALUES (100, 77, 5);
INSERT INTO `cauhoi_g` VALUES (101, 78, 11);
INSERT INTO `cauhoi_g` VALUES (102, 78, 3);
INSERT INTO `cauhoi_g` VALUES (103, 79, 1);
INSERT INTO `cauhoi_g` VALUES (104, 80, 2);
INSERT INTO `cauhoi_g` VALUES (105, 80, 3);
INSERT INTO `cauhoi_g` VALUES (106, 81, 4);
INSERT INTO `cauhoi_g` VALUES (107, 82, 5);
INSERT INTO `cauhoi_g` VALUES (108, 82, 6);
INSERT INTO `cauhoi_g` VALUES (109, 83, 7);
INSERT INTO `cauhoi_g` VALUES (110, 84, 8);
INSERT INTO `cauhoi_g` VALUES (111, 84, 9);
INSERT INTO `cauhoi_g` VALUES (112, 85, 10);
INSERT INTO `cauhoi_g` VALUES (113, 86, 11);
INSERT INTO `cauhoi_g` VALUES (114, 86, 1);
INSERT INTO `cauhoi_g` VALUES (115, 87, 1);
INSERT INTO `cauhoi_g` VALUES (116, 88, 2);
INSERT INTO `cauhoi_g` VALUES (117, 88, 3);
INSERT INTO `cauhoi_g` VALUES (118, 89, 4);
INSERT INTO `cauhoi_g` VALUES (119, 90, 6);
INSERT INTO `cauhoi_g` VALUES (120, 90, 5);

INSERT INTO `cauhoi_g` VALUES (121, 91, 1);
INSERT INTO `cauhoi_g` VALUES (122, 92, 2);
INSERT INTO `cauhoi_g` VALUES (123, 92, 3);
INSERT INTO `cauhoi_g` VALUES (124, 93, 4);
INSERT INTO `cauhoi_g` VALUES (125, 94, 6);
INSERT INTO `cauhoi_g` VALUES (126, 94, 5);
INSERT INTO `cauhoi_g` VALUES (127, 95, 7);
INSERT INTO `cauhoi_g` VALUES (128, 96, 9);


INSERT INTO `diem` VALUES (1, 1, 1, 3);
INSERT INTO `diem` VALUES (2, 2, 1, 5);
INSERT INTO `diem` VALUES (3, 3, 1, 2.5);
INSERT INTO `diem` VALUES (4, 4, 1, 4);
INSERT INTO `diem` VALUES (5, 1, 2, 1.5);
INSERT INTO `diem` VALUES (6, 2, 2, 5.5);
INSERT INTO `diem` VALUES (7, 3, 2, 3);
INSERT INTO `diem` VALUES (8, 4, 2, 3);
INSERT INTO `diem` VALUES (9, 1, 3, 2.5);
INSERT INTO `diem` VALUES (10, 2, 3, 4.5);
INSERT INTO `diem` VALUES (11, 3, 3, 3);
INSERT INTO `diem` VALUES (12, 4, 3, 6);
INSERT INTO `diem` VALUES (13, 1, 4, 1);
INSERT INTO `diem` VALUES (14, 2, 4, 5);
INSERT INTO `diem` VALUES (15, 3, 4, 3);
INSERT INTO `diem` VALUES (16, 4, 4, 4);
INSERT INTO `diem` VALUES (17, 1, 5, 2.5);
INSERT INTO `diem` VALUES (18, 2, 5, 3);
INSERT INTO `diem` VALUES (19, 3, 5, 3);
INSERT INTO `diem` VALUES (20, 4, 5, 5.5);

INSERT INTO `diem` VALUES (21, 5, 6, 0.5);
INSERT INTO `diem` VALUES (22, 6, 6, 5.5);
INSERT INTO `diem` VALUES (23, 7, 6, 2);
INSERT INTO `diem` VALUES (24, 8, 6, 4);
INSERT INTO `diem` VALUES (25, 5, 7, 1.5);
INSERT INTO `diem` VALUES (26, 6, 7, 4.5);
INSERT INTO `diem` VALUES (27, 7, 7, 1);
INSERT INTO `diem` VALUES (28, 8, 7, 4);
INSERT INTO `diem` VALUES (29, 5, 8, 2);
INSERT INTO `diem` VALUES (30, 6, 8, 4);
INSERT INTO `diem` VALUES (31, 7, 8, 3);
INSERT INTO `diem` VALUES (32, 8, 8, 4.5);
INSERT INTO `diem` VALUES (33, 5, 9, 2);
INSERT INTO `diem` VALUES (34, 6, 9, 5.5);
INSERT INTO `diem` VALUES (35, 7, 9, 0);
INSERT INTO `diem` VALUES (36, 8, 9, 4.5);
INSERT INTO `diem` VALUES (37, 5, 10, 2);
INSERT INTO `diem` VALUES (38, 6, 10, 3.5);
INSERT INTO `diem` VALUES (39, 7, 10, 2);
INSERT INTO `diem` VALUES (40, 8, 10, 4);

INSERT INTO `diem` VALUES (41, 9, 11, 1.5);
INSERT INTO `diem` VALUES (42, 10, 11, 5);
INSERT INTO `diem` VALUES (43, 11, 11, 2.5);
INSERT INTO `diem` VALUES (44, 12, 11, 3.5);
INSERT INTO `diem` VALUES (45, 9, 12, 2);
INSERT INTO `diem` VALUES (46, 10, 12, 5);
INSERT INTO `diem` VALUES (47, 11, 12, 2.5);
INSERT INTO `diem` VALUES (48, 12, 12, 4);
INSERT INTO `diem` VALUES (49, 9, 13, 3);
INSERT INTO `diem` VALUES (50, 10, 13, 4);
INSERT INTO `diem` VALUES (51, 11, 13, 2.5);
INSERT INTO `diem` VALUES (52, 12, 13, 4.5);
INSERT INTO `diem` VALUES (53, 9, 14, 2.5);
INSERT INTO `diem` VALUES (54, 10, 14, 3.5);
INSERT INTO `diem` VALUES (55, 11, 14, 1);
INSERT INTO `diem` VALUES (56, 12, 14, 4);
INSERT INTO `diem` VALUES (57, 9, 15, 2.5);
INSERT INTO `diem` VALUES (58, 10, 15, 5.5);
INSERT INTO `diem` VALUES (59, 11, 15, 1);
INSERT INTO `diem` VALUES (60, 12, 15, 6);

INSERT INTO `diem` VALUES (61, 13, 16, 1.5);
INSERT INTO `diem` VALUES (62, 14, 16, 5.5);
INSERT INTO `diem` VALUES (63, 15, 16, 2.5);
INSERT INTO `diem` VALUES (64, 16, 16, 4.5);
INSERT INTO `diem` VALUES (65, 13, 17, 3);
INSERT INTO `diem` VALUES (66, 14, 17, 5);
INSERT INTO `diem` VALUES (67, 15, 17, 2);
INSERT INTO `diem` VALUES (68, 16, 17, 3.5);
INSERT INTO `diem` VALUES (69, 13, 18, 2);
INSERT INTO `diem` VALUES (70, 14, 18, 5);
INSERT INTO `diem` VALUES (71, 15, 18, 3);
INSERT INTO `diem` VALUES (72, 16, 18, 4);
INSERT INTO `diem` VALUES (73, 13, 19, 2.5);
INSERT INTO `diem` VALUES (74, 14, 19, 6);
INSERT INTO `diem` VALUES (75, 15, 19, 1);
INSERT INTO `diem` VALUES (76, 16, 19, 4);
INSERT INTO `diem` VALUES (77, 13, 20, 2.5);
INSERT INTO `diem` VALUES (78, 14, 20, 5);
INSERT INTO `diem` VALUES (79, 15, 20, 3);
INSERT INTO `diem` VALUES (80, 16, 20, 4.5);

INSERT INTO `diem` VALUES (81, 17, 21, 3);
INSERT INTO `diem` VALUES (82, 18, 21, 5.5);
INSERT INTO `diem` VALUES (83, 19, 21, 2);
INSERT INTO `diem` VALUES (84, 20, 21, 34);
INSERT INTO `diem` VALUES (85, 17, 22, 2);
INSERT INTO `diem` VALUES (86, 18, 22, 4.5);
INSERT INTO `diem` VALUES (87, 19, 22, 2);
INSERT INTO `diem` VALUES (88, 20, 22, 4.5);
INSERT INTO `diem` VALUES (89, 17, 23, 3.5);
INSERT INTO `diem` VALUES (90, 18, 23, 3);
INSERT INTO `diem` VALUES (91, 19, 23, 1.5);
INSERT INTO `diem` VALUES (92, 20, 23, 3);
INSERT INTO `diem` VALUES (93, 17, 24, 2.5);
INSERT INTO `diem` VALUES (94, 18, 24, 5);
INSERT INTO `diem` VALUES (95, 19, 24, 1.5);
INSERT INTO `diem` VALUES (96, 20, 24, 5.5);
INSERT INTO `diem` VALUES (97, 17, 25, 2);
INSERT INTO `diem` VALUES (98, 18, 25, 5);
INSERT INTO `diem` VALUES (99, 19, 25, 2);
INSERT INTO `diem` VALUES (100, 20,25, 5.5);

INSERT INTO `diem` VALUES (101, 21, 26, 1);
INSERT INTO `diem` VALUES (102, 22, 26, 2);
INSERT INTO `diem` VALUES (103, 23, 26, 2);
INSERT INTO `diem` VALUES (104, 24, 26, 6);
INSERT INTO `diem` VALUES (105, 21, 27, 2.5);
INSERT INTO `diem` VALUES (106, 22, 27, 5.5);
INSERT INTO `diem` VALUES (107, 23, 27, 2.5);
INSERT INTO `diem` VALUES (108, 24, 27, 3);
INSERT INTO `diem` VALUES (109, 21, 28, 2);
INSERT INTO `diem` VALUES (110, 22, 28, 5.5);
INSERT INTO `diem` VALUES (111, 23, 28, 1);
INSERT INTO `diem` VALUES (112, 24, 28, 6);
INSERT INTO `diem` VALUES (113, 21, 29, 2);
INSERT INTO `diem` VALUES (114, 22, 29, 4);
INSERT INTO `diem` VALUES (115, 23, 29, 2);
INSERT INTO `diem` VALUES (116, 24, 29, 5.5);
INSERT INTO `diem` VALUES (117, 21, 30, 2);
INSERT INTO `diem` VALUES (118, 22, 30, 3.5);
INSERT INTO `diem` VALUES (119, 23, 30, 2.5);
INSERT INTO `diem` VALUES (120, 24, 30, 6);
  