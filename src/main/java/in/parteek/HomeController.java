/**
 * 
 */
package in.parteek;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.stereotype.*;
import in.parteek.Dao.Dao;
import in.parteek.beans.Student;

/**
 * Created on : 2019-03-14, 9:26:26 a.m.
 *
 * @author Parteek Dheri
 */
@RestController
public class HomeController {
	Dao dao = new Dao();

	@RequestMapping("/")
	public String goHome(Model model) {
		return "home";
	}

	@RequestMapping(value = "/StudentList/{id}/{name}", method = RequestMethod.POST)
	public int addStudent(Model model, @PathVariable int id, @PathVariable String name) {

		Student s = new Student(id, name);
		return dao.addStudent(s);
	}

	@RequestMapping(value = "/StudentList", 
			method = RequestMethod.PUT, 
			headers = { "content-type=application/json" })
	public int putStudentList(Model model,
			@RequestBody List<Student> students) {

		return dao.replaceStudents(students);
	}

	@RequestMapping(value = "/StudentList", method = RequestMethod.GET)
	public List<Student> getStudentList(Model model) {

		return dao.getStudents();
	}
}
