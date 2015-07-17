package com.lst.lc.dao;

import com.lst.lc.entities.Question;

/**
 * @author innerac
 *
 */
public interface QuestionDao {

	public void addQuestion(Question question);
	
	public void updateQuestion(Question question);
	
	public Question getQuestion(int questionId);
	
	public void update(int questionId, String title, String tag, String content);
	
}
