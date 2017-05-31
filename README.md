## SSH在线考试系统


在线考试系统提高了考试的可靠性、有效性和工作效率，降低了考试成本,顺应了社会的网络化趋势,必将成为一种不可或缺的考试方式。本文研究的目的是设计一个易于管理和维护的面向教学的考试系统，具有一定的通用性，能够满足多门课程的测试与考核要求。为教师开展平时考核及期末考核提供一个考核平台；为学生利用网络自主学习提供条件；为不同课程的网上考试提供平台。

### 业务流程

1．考生在线考试模块
考生通过主界面输入学号、姓名，选择需要考试的科目等。当系统判断考生的身份合法且未参加过考试时，则允许考生进入到考试系统中去。考试结束采取自主交卷和到了规定的时间自动提示有机结合的方式予以实现。在考试途中刷新考试页面或者离开考试页面视为交卷，考试过程中设有时钟，用以提醒考生。

2．教师管理模块
教师根据登录账号和密码进行登录，如果登录账号和秘密正确，则通过系统的登录识别，否则系统会弹出对话框，提示教师用户错误的登录信息。教师登录管理系统后，可以选择添加试卷，选择对应的试卷课程，同一个课程下可以有多套试卷，填入选择题，判断题的分数，考试时间，试卷名称等生成试卷，然后在试卷下面可以对试卷进行添加单选题或者判断题。教师可以通过试卷管理和考生成绩查询，对考生进行出卷和查看考生的详细卷面情况。

 3．管理员模块
管理员进入管理员界面，可以对整个学校年级信息进行操作，包括年级信息的录入、每个年级课程的录入、还可以对每条年级信息进行修改。管理员可以对学生信息进行录入和操作。此外，管理员可以对教师信息进行录入和操作。还有管理员可以对课程进行录入和操作。


### 系统的功能设计

在网上考试系统的设计和实现过程中，为了使系统便于管理和安全，在网上考试系统中编辑设计了管理员登录，管理员可以向网上考试系统增加和删除教师和学生。教师和学生要想使用网上考试系统必须先登录。教师登录后，可以修改自己的登录密码；为学生的考试出题，并且形成题库；对试题进行修改；对试题进行按要求的查询。学生登录后可以选择自己考试的课程；按照考试课程的名称，试卷名称考试；查看自己的分数；查询自已考过课程的试卷。
网上考试系统的设计思想把整个系统分成考生模块、教师模块、管理员模块。具体如图所示。

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/0.png)



### 自动评阅设计

试卷的自动评阅是网上在线考试系统中必备的功能，可以快速客观地给出考试成绩。将学生答案和服务器上存放的该题标准答案根据题的编号来对比，一致认为该题作答正确，不一致说明作答错误，某题型做正确的题数与每小题相乘后总和就是该小题的得分。



### 界面设计



- 管理员登录界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/2.png)

- 教师登录界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/1.png)

- 学生登录界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/3.png)

- 管理员主界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/4.png)

- 管理员学生管理界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/6.png)

- 管理员教师管理界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/5.png)


- 管理员课程管理界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/7.png)

- 教师主界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/8.png)

- 教师试卷管理界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/9.png)

- 教师试卷管理界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/9.png)

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/10.png)

- 教师成绩查询界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/11.png)

- 学生主界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/12.png)


- 学生考试界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/13.png)


![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/14.png)

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/15.png)


- 学生个人成绩界面

![image](https://github.com/cckevincyh/OnLineTest/blob/master/img/16.png)