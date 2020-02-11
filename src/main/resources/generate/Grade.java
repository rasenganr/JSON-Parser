import org.json.*;
import java.util.*;

public class Grade {
private Course course;
private String student;
private double grade;


public Course getCourse() {
	return course;
}

public void setCourse(Course value) {
	course = value;
}

public String getStudent() {
	return student;
}

public void setStudent(String value) {
	student = value;
}

public double getGrade() {
	return grade;
}

public void setGrade(double value) {
	grade = value;
}


public String toJSONHelper(HashMap<Object, Integer> mp) throws JSONException {
	String className = this.getClass().getSimpleName();
	if (mp.containsKey(this)) {
		return "{\"ref\" : " + mp.get(this) + "}";
	}
	else {
		int id = mp.size() + 1;
		String id_str = "\"id\" : " + Integer.toString(id) + ", ";
		String type_str = "\"type\" : " + className + ", ";
		mp.put(this, id);
		String inner = "";
		inner += "{\"course\" : " + course.toJSONHelper(mp) + "}, ";
		inner += "{\"student\" : \"" + student + "\"}, ";
		inner += "{\"student\" : " + student + "}, ";
		inner += "{\"grade\" : " + grade + "}, ";
		String values_str = "\"values\" : [" + inner + "]";
		String res = "{" + id_str + type_str + values_str + "}";
		return res;
	}
}

public JSONObject toJSON() throws JSONException {
	HashMap<Object, Integer> mp = new HashMap<Object, Integer>();
	String str = toJSONHelper(mp);
	JSONObject res = new JSONObject(str);
	return res;
}
}
