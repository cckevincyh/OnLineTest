package com.cc.onlinetest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.cc.onlinetest.dao.OnLineTestDao;
import com.cc.onlinetest.dao.QuestionDao;
import com.cc.onlinetest.dao.ScoreDao;
import com.cc.onlinetest.dao.StudentDao;
import com.cc.onlinetest.dao.SubjectDao;
import com.cc.onlinetest.domain.Answer;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.ChoiceAnswer;
import com.cc.onlinetest.domain.Judge;
import com.cc.onlinetest.domain.JudgeAnswer;
import com.cc.onlinetest.domain.ResultScore;
import com.cc.onlinetest.domain.Score;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.OnLineTestService;

public class OnLineTestServiceImpl implements OnLineTestService{

	private OnLineTestDao onLineTestDao;
	private QuestionDao questionDao;
	private SubjectDao subjectDao;
	private ScoreDao scoreDao;
	private StudentDao studentDao;
	
	
	
	/**
	 * @param studentDao the studentDao to set
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	/**
	 * @param scoreDao the scoreDao to set
	 */
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	/**
	 * @param questionDao the questionDao to set
	 */
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	/**
	 * @param subjectDao the subjectDao to set
	 */
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	/**
	 * @param onLineTestDao the onLineTestDao to set
	 */
	public void setOnLineTestDao(OnLineTestDao onLineTestDao) {
		this.onLineTestDao = onLineTestDao;
	}

	@Override
	public boolean onLineTest(Student student, Subject subject, String answer) {
		boolean b = true;
		Score testScore = scoreDao.getScore(student, subject);
		if(testScore!=null){
			//该试卷已经做过了 ，允许重复提交
			return false;
		}
		Subject subjectById = subjectDao.getSubjectById(subject);
		try{
			HashMap<String, Answer> splitAnswer = splitAnswer(student, subject, answer);
			//同时需要统计考试做题的成绩
			//创建一个学生成绩实例
			Score score = new Score();
			score.setSubject(subjectById);
			score.setStudent(student);
			score.setChoiceScore(0);
			score.setJudgeScore(0);
			score.setAllScore(0);
			
			  
			//得到该套题的所有选择题
			Set<Choice> choices = subjectById.getChoices();
			//得到该套题的所有判断题
			Set<Judge> judges = subjectById.getJudges();
			//遍历所有的选择题
			for(Choice choice : choices){
				if(splitAnswer.containsKey(choice.getChoiceId()+"-1")){
					Answer answer2 = splitAnswer.get(choice.getChoiceId()+"-1");
					//设置选择题成绩得分
					score.setChoiceScore(score.getChoiceScore()+answer2.getScore());
					//添加答案
					onLineTestDao.addAnswer(answer2);
				}else{
					//添加答案
					Answer an = new Answer();
					an.setStudent(student);
					an.setSubject(subject);
					an.setQuestionType(1);//设置为选择题类型
					an.setQuestion(choice.getChoiceId());//设置题目id
					an.setAnswer("");
					an.setGoodAnswer(choice.getAnswer());
					an.setScore(0);
					//添加答案
					onLineTestDao.addAnswer(an);
					
				}
			}
			//遍历所有的判断题
			for(Judge judge : judges){
				if(splitAnswer.containsKey(judge.getJudgeId()+"-2")){
					Answer answer2 = splitAnswer.get(judge.getJudgeId()+"-2");
					//设置判断题成绩得分
					score.setJudgeScore(score.getJudgeScore()+answer2.getScore());
					//添加答案
					onLineTestDao.addAnswer(answer2);
				}else{
					//添加答案
					Answer an = new Answer();
					an.setSubject(subject);
					an.setStudent(student);
					an.setQuestionType(2);//设置为选择题类型
					an.setQuestion(judge.getJudgeId());//设置题目id
					an.setAnswer("");
					an.setGoodAnswer(judge.getAnswer());
					an.setScore(0);
					//添加答案
					onLineTestDao.addAnswer(an);
				}
				
			}
			//设置总得分，选择加上判断
			score.setAllScore(score.getChoiceScore()+score.getJudgeScore());
			//保存总成绩
			scoreDao.addScore(score);
			
			//关闭学生的锁
			Student studentById = studentDao.getStudentById(student);
			studentById.setLockState(0);
			studentDao.updateStudent(studentById);
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		
		return b;
	}
	
	
	
	public HashMap<String, Answer> splitAnswer(Student student,Subject subject,String answer){
		HashMap<String, Answer> map = new HashMap<String, Answer>();
		Subject subjectById = subjectDao.getSubjectById(subject);	//得到试卷的详细信息
		//分割解析答案字符串
		//得到每一个答案组
		//需要判断一下答案字符串是否为空，为空则不处理
		if(answer==null){
			return map;
		}else if(answer.equals("")){
			return map;
		}
		String[] str = answer.split("-");
		for(String s : str){
			//得到每个答案组的字符串,继续分割
			String ss[] = s.split("_");
			Answer answer2 = new Answer();
			answer2.setQuestion(Integer.parseInt(ss[0]));
			answer2.setQuestionType(Integer.parseInt(ss[1]));
			answer2.setAnswer(ss[2]);
			answer2.setStudent(student);
			answer2.setSubject(subject);
			if(answer2.getQuestionType()==1){
				//选择题
				answer2.setScore(subjectById.getChoiceScore());
				Choice choice = new Choice();
				choice.setChoiceId(answer2.getQuestion());
				Choice choiceById = questionDao.getChoiceById(choice);//得到答案
				answer2.setGoodAnswer(choiceById.getAnswer());
				//查看当前的答案与正确答案是否匹配
				if(answer2.getAnswer().equals(answer2.getGoodAnswer())){//判题
					answer2.setScore(subjectById.getChoiceScore());
				}else{
					answer2.setScore(0);
				}
			}else{
				//判断题
				answer2.setScore(subjectById.getJudgeScore());
				Judge judge = new Judge();
				judge.setJudgeId(answer2.getQuestion());
				Judge judgeById = questionDao.getJudgeById(judge);
				answer2.setGoodAnswer(judgeById.getAnswer());//得到答案
				//查看当前的答案与正确答案是否匹配
				if(answer2.getAnswer().equals(answer2.getGoodAnswer())){//判题
					answer2.setScore(subjectById.getJudgeScore());
				}else{
					answer2.setScore(0);
				}
			}
			map.put(answer2.getQuestion()+"-"+answer2.getQuestionType(), answer2);
		}
		return map;
	}

	@Override
	public ResultScore getResultScore(Student student, Subject subject) {
		List<ChoiceAnswer> choiceAnswers = new ArrayList<ChoiceAnswer>();
		List<JudgeAnswer> judgeAnswers = new ArrayList<JudgeAnswer>();
		// TODO Auto-generated method stub
		Subject subjectById = subjectDao.getSubjectById(subject);
		//得到所有的选择题
		Set<Choice> choices = subjectById.getChoices();
		//得到所有的判断题
		Set<Judge> judges = subjectById.getJudges();
		
		for(Choice choice : choices){
			//遍历所有的选择题,得到对应的答案
			Answer answer = onLineTestDao.getChoiceAnswer(student,subject,choice);
			ChoiceAnswer choiceAnswer = new ChoiceAnswer();
			choiceAnswer.setAnswer(answer);
			choiceAnswer.setChoice(choice);
			choiceAnswers.add(choiceAnswer);//加入集合
		}
		for(Judge judge : judges){
			//遍历所有的判断题,得到对应的答案
			Answer answer = onLineTestDao.getJudgeAnswer(student, subject, judge);
			JudgeAnswer judgeAnswer = new JudgeAnswer();
			judgeAnswer.setAnswer(answer);
			judgeAnswer.setJudge(judge);
			judgeAnswers.add(judgeAnswer);//加入集合
		}
		ResultScore resultScore = new ResultScore();
		resultScore.setChoiceAnswers(choiceAnswers);
		resultScore.setJudgeAnswers(judgeAnswers);
		return resultScore;
	}
	
	
	
	
} 
