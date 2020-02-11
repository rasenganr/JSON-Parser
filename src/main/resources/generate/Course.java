import org.json.*;
import java.util.*;

public class Course {
private Faculty instructor;
private int numStudents;
private ArrayList<Grade> grades;

public Course() {
	grades = new ArrayList<Grade>();
}


public Faculty getInstructor() {
	return instructor;
}

public void setInstructor(Faculty value) {
	instructor = value;
}

public int getNumStudents() {
	return numStudents;
}

public void setNumStudents(int value) {
	numStudents = value;
}

public Grade getGrades(int index) {
	return grades.get(index);
}

public void setGrades(int index, Grade value) {
	grades.set(index, value);
}

public void addGrades(Grade toAdd) {
	grades.add(toAdd);
}

public int numGrades() {
	return grades.size();
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
		inner += "{\"instructor\" : " + instructor.toJSONHelper(mp) + "}, ";
		inner += "{\"numStudents\" : " + numStudents + "}, ";
		inner += "{\"grades\" : [";
		for (int i = 0; i < grades.size(); ++i) {
			inner += grades.get(i).toJSONHelper(mp) + ", ";
		}
		inner += "]}, ";
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
