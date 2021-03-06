package com.lst.lc.test.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.junit.Test;

import com.lst.lc.entities.Question;
import com.lst.lc.utils.DateUtils;


public class QuestionDaoTest extends BaseTestDao {
	
	@Test
	public void test(){
		init();
		addReadNums(1);
		destroy();
	}

	public List<Question> getTopFiveRecently() {
		Date end = DateUtils.getDateBefore(new Date(), 7);
		String hql = "from Question as question where question.time > ? order by question.readNums*0.2+question.answerNums*0.8 desc";
		Query query = query(hql);
		query.setDate(0, end);
		List<Question> lists = query.list();
		return lists;
	}
	
	public void addReadNums(int questionId) {
		String hql = "update Question as question set question.readNums = 1 where question.questionId = ?";
		Query query = query(hql).setInteger(0, questionId);
		query.executeUpdate();
	}
}
