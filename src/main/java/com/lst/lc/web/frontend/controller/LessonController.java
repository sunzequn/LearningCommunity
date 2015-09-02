package com.lst.lc.web.frontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lst.lc.dao.CategoryDao;
import com.lst.lc.dao.CourseDao;
import com.lst.lc.dao.DirectionDao;
import com.lst.lc.dao.LessonCommentDao;
import com.lst.lc.dao.LessonDao;
import com.lst.lc.dao.UserDao;
import com.lst.lc.entities.Category;
import com.lst.lc.entities.Course;
import com.lst.lc.entities.CourseLesson;
import com.lst.lc.entities.Direction;
import com.lst.lc.entities.LessonComment;
import com.lst.lc.entities.User;
import com.lst.lc.page.Page;
import com.lst.lc.page.PageHandler;
import com.lst.lc.web.bean.CourseMenu;
import com.lst.lc.web.bean.LearnStatus;
import com.lst.lc.web.service.CourseMenuHandler;

@Controller
@RequestMapping("/course")
public class LessonController {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
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
	@Qualifier("lessonDao")
	private LessonDao lessonDao;
	
	@Autowired
	@Qualifier("lessonCommentDao")
	private LessonCommentDao lessonCommentDao;
	@Autowired
	private CourseMenuHandler courseMenuHandler;
	
	@Autowired
	private PageHandler<LessonComment> pageHandler;

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String listCourse(Model model, String directionId,
			String categoryId, String difficulty, String type, String pageNum,
			String pageSize) {
		CourseMenu courseMenu = courseMenuHandler.getCourseMenu(directionId,
				categoryId, difficulty, type, pageNum, pageSize);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("module", "course");
		return "frontend/course/list";
	}

	@RequestMapping(value = "/view/{courseId}", method = RequestMethod.GET)
	public String viewCourse(HttpSession session, Model model, @PathVariable int courseId) {
		Course course = courseDao.getCourse(courseId);
		List <CourseLesson> lessons = lessonDao.getLessonsOfCourse(courseId);
		
		User user = (User) session.getAttribute("loginUser");
		LearnStatus status;
		if(user == null){
			status = new LearnStatus(false, courseId);
		} else {
			status = lessonDao.learnStatus(user.getUserId(), courseId);
		}
		List<Course> courses = courseDao.getOtherCourses(course.getCourseId(), course.getDirection().getDirectionId());
		List<User> users = courseDao.getUsers(courseId);
		model.addAttribute("users", users);
		model.addAttribute("courses", courses);
		model.addAttribute("status", status);
		model.addAttribute("course", course);
		model.addAttribute("lessons",lessons);
		model.addAttribute("module", "course");
		return "frontend/course/view";
	}

	@RequestMapping(value = "/view/lesson/{lessonId}", method = RequestMethod.GET)
	public String viewLesson(Model model, @PathVariable int lessonId,
			String pageNum, String pageSize) {
		
		CourseLesson lesson = lessonDao.getLesson(lessonId);
		Course course = lesson.getCourse();
		model.addAttribute("course", course);
		model.addAttribute("lesson", lesson);
		model.addAttribute("module", "course");
		if(lesson.getType().equals("text")){
		        String category = course.getCategory().getCategoryName();
			if(category.equals("HTML"))
				return "frontend/course/html-editor"; 
			else if(category.equals("Java")){
				return "frontend/course/java-editor";
			}else if(category.equals("C++")){
			        return "frontend/course/cpp-editor";
			}else{
			        return "frontend/course/python-editor";
			}
		}else{
			int pageNow = 1;
			int pagesize = 10;
			if (pageSize != null) {
				pagesize = Integer.valueOf(pageSize);
			}
			if (pageSize != null) {
				pageNow = Integer.valueOf(pageNum);
			}
			Page<LessonComment> page = pageHandler
					.getPage(pageNow, pagesize, LessonComment.class);
			model.addAttribute("page", page);
			List<User> users = courseDao.getUsers(lesson.getCourse().getCourseId());
			model.addAttribute("users", users);
			
			return "frontend/course/lesson";			
		}
	}

	@RequestMapping(value = "/comment/{lessonId}", method = RequestMethod.POST)
	public String comment(HttpSession session, Model model,@PathVariable int lessonId,
			String head, String content, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("loginUser");
		User u = userDao.getById(user.getUserId());
		CourseLesson lesson = lessonDao.getLesson(lessonId);
		LessonComment comment = new LessonComment(lesson, u, content,
				new Date(), head);
		lessonCommentDao.addLessonComment(comment);
		model.addAttribute("module", "course");
		return "redirect:/course/view/lesson/" + lessonId;
	}
	
	@RequestMapping(value = "/learn/{courseId}", method = RequestMethod.GET)
	public String learn(HttpSession session, Model model, @PathVariable int courseId) {
		User user = (User) session.getAttribute("loginUser");
		userDao.learn(user.getUserId(), courseId);
		courseDao.addNums(courseId);
		return "redirect:/course/view/" + courseId;
	}
	
}
