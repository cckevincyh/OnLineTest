package com.cc.onlinetest.service.impl;

import java.util.HashMap;
import java.util.Set;

import com.cc.onlinetest.dao.OnLineTestDao;
import com.cc.onlinetest.dao.QuestionDao;
import com.cc.onlinetest.dao.SubjectDao;
import com.cc.onlinetest.domain.Answer;
import com.cc.onlinetest.domain.Choice;
import com.cc.onlinetest.domain.Judge;
import com.cc.onlinetest.domain.Student;
import com.cc.onlinetest.domain.Subject;
import com.cc.onlinetest.service.OnLineTestService;

public class OnLineTestServiceImpl implements OnLineTestService{

	private OnLineTestDao onLineTestDao;
	private QuestionDao questionDao;
	private SubjectDao subjectDao;
	
	
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
		Subject subjectById = subjectDao.getSubjectById(subject);
		
		HashMap<String, Answer> splitAnswer = splitAnswer(student, subject, answer);
		
		
		//得到该套题的所有选择题
		Set<Choice> choices = subjectById.getChoices();
		//得到该套题的所有判断题
		Set<Judge> judges = subjectById.getJudges();
		//遍历所有的选择题
		for(Choice choice : choices){
			if(splitAnswer.containsKey(choice.getChoiceId()+"-1")){
				Answer answer2 = splitAnswer.get(choice.getChoiceId()+"-1");
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
		
		return false;
	}
	
	
	
	public HashMap<String, Answer> splitAnswer(Student student,Subject subject,String answer){
		HashMap<String, Answer> map = new HashMap<String, Answer>();
		Subject subjectById = subjectDao.getSubjectById(subject);	//得到试卷的详细信息
		//分割解析答案字符串
		//得到每一个答案组
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
				if(answer2.getAnswer().equals(answer2.getGoodAnswer())){
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
				if(answer2.getAnswer().equals(answer2.getGoodAnswer())){
					answer2.setScore(subjectById.getJudgeScore());
				}else{
					answer2.setScore(0);
				}
			}
			
			map.put(answer2.getQuestion()+"-"+answer2.getQuestionType(), answer2);
		}
		return map;
	}
	
	
	
	
} 
