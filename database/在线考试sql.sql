create database OnLineTest;

use OnLineTest;


-- 教师基本信息
CREATE TABLE tb_teacher(
	tid VARCHAR(8) PRIMARY KEY,	-- 主键，教师id
	tname NVARCHAR(20) NOT NULL, -- 教师名
	pwd VARCHAR(64) NOT NULL	-- 密码
	
);

-- 学生基本信息
CREATE TABLE tb_student(
	sid VARCHAR(8) PRIMARY KEY,	-- 主键，学号(登录用户名)
	sname NVARCHAR(20) NOT NULL,	-- 学生名
	pwd VARCHAR(64) NOT NULL,	-- 密码
	lockState INT (2) NOT NULL DEFAULT 0

);

-- 管理员基本信息
CREATE TABLE tb_admin(
	
	aid INT(3) PRIMARY KEY,
	username nvarchar(20) BINARY UNIQUE NOT NULL,
	pwd VARCHAR(64) NOT NULL
	
);

-- 课程基本信息
CREATE TABLE tb_course(

	courseId INT(11) PRIMARY KEY,
	courseName VARCHAR(64) UNIQUE NOT NULL
);

-- 试卷的基本信息
CREATE TABLE tb_subject(
	subjectId INT(11) PRIMARY KEY,
	subjectName VARCHAR(64) UNIQUE NOT NULL,	-- 试卷名
	courseId INT(11) NOT NULL,	-- 所属的课程
	subjectTime INT(60) NOT NULL DEFAULT 60, -- 考试时间
	choiceNum INT(5) NOT NULL DEFAULT 0,	-- 选择题个数
	judgeNum INT(5) NOT NULL DEFAULT 0,	-- 判断题个数
	choiceScore INT(5) NOT NULL DEFAULT 0,	-- 选择题分数
	judgeScore INT(5) NOT NULL DEFAULT 0,	-- 判断题分数
	allScore INT(5) NOT NULL DEFAULT 0,	-- 总分
	CONSTRAINT  FOREIGN KEY (`courseId`) REFERENCES `tb_course` (`courseId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 选择题基本信息
CREATE TABLE tb_choice(
	choiceId INT(11) PRIMARY KEY,	-- 选择题编号，自增长
	subjectId INT(11)NOT NULL,	-- 所属试卷
	question VARCHAR(200) NOT NULL,	-- 问题
	optionA VARCHAR(100) NOT NULL,
	optionB VARCHAR(100) NOT NULL,
	optionC VARCHAR(100) NOT NULL,
	optionD VARCHAR(100) NOT NULL,
	answer VARCHAR(20) NOT NULL,	-- 答案
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`subjectId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 判断题基本信息
CREATE TABLE tb_judge(
	judgeId INT(11) PRIMARY KEY,	-- 判断题编号，自增长
	subjectId INT(11) NOT NULL,	-- 所属试卷
	question VARCHAR(200) NOT NULL,	-- 问题
	answer VARCHAR(20) NOT NULL,	-- 答案
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`subjectId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 考试答案
CREATE TABLE tb_answer(
	answerId INT(11) PRIMARY KEY,	-- 答案编号,自增长
	sid VARCHAR(8) NOT NULL,	-- 学号
	subjectId INT(11) NOT NULL,	-- 考试试卷
	question INT(5) NOT NULL,	-- 试题号
	questionType INT(5) NOT NULL,	-- 类型，选择判断(1:选择题，2:判断题)
	answer VARCHAR(20) NOT NULL DEFAULT '',	-- 答案
	goodAnswer VARCHAR(20) NOT NULL,	-- 正确答案
	score INT(5) NOT NULL DEFAULT 0,	-- 得分
	CONSTRAINT  FOREIGN KEY (`sid`) REFERENCES `tb_student` (`sid`) ON DELETE CASCADE ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`subjectId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 考试成绩
CREATE TABLE tb_score(
	scoreId INT(11) PRIMARY KEY,	-- 考试成绩编号，自增长
	sid VARCHAR(8) NOT NULL,	-- 学号
	subjectId INT(11) NOT NULL,	-- 考试试卷
	choiceScore INT(5) NOT NULL DEFAULT 0,	-- 选择题分数
	judgeScore INT(5) NOT NULL DEFAULT 0,	-- 判断题分数
	allScore INT(5) NOT NULL DEFAULT 0,	-- 总分
	CONSTRAINT  FOREIGN KEY (`sid`) REFERENCES `tb_student` (`sid`) ON DELETE CASCADE ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`subjectId`) ON DELETE CASCADE ON  UPDATE CASCADE
);






