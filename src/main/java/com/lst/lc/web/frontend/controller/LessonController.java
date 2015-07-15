package com.lst.lc.web.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.Direction;
import com.lst.lc.page.Page;
import com.lst.lc.web.bean.CourseMenu;
import com.lst.lc.web.handler.CourseMenuHandler;

@Controller
@RequestMapping("/course")
public class LessonController {

	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;

	@Autowired
	@Qualifier("directionDao")
	private DirectionDao directionDao;

	@Autowired
	@Qualifier("courseDao")
	private CourseDao courseDao;
	
	@Autowired
	private CourseMenuHandler courseMenuHandler;

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model, String directionId,
			String categoryId, String difficulty, String type, String pageNum,
			String pageSize) {
		CourseMenu courseMenu = courseMenuHandler.getCourseMenu(directionId, categoryId, difficulty, type, pageNum, pageSize);
		model.addAttribute("courseMenu", courseMenu);
		return "frontend/course/list";
	}
}
