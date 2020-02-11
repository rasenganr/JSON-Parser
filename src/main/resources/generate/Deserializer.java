import org.json.*;
import java.util.*;

public class Deserializer {
public static Course readCourseHelper(HashMap<Integer, Object> mp, JSONObject js) throws JSONException {
	if (!js.optString("ref").equals("")) {
		return (Course)(mp.get(js.getInt("ref")));
	}
	else {
		Course res = new Course();
		mp.put(js.getInt("id"), res);
		JSONArray fields = js.getJSONArray("values");
		JSONObject oneField = new JSONObject();
		oneField = fields.getJSONObject(0);
		res.setInstructor(readFacultyHelper(mp, oneField.getJSONObject("instructor")));
		oneField = fields.getJSONObject(1);
		res.setNumStudents(oneField.getInt("numStudents"));
		oneField = fields.getJSONObject(2);
		for (int i = 0; i < oneField.getJSONArray("grades").length(); ++i) {
			res.addGrades(readGradeHelper(mp, oneField.getJSONArray("grades").getJSONObject(i)));
		}
		return res;
	}
}

public static Course readCourse(JSONObject js) throws JSONException {
	HashMap<Integer, Object> mp = new HashMap<Integer, Object>();
	return readCourseHelper(mp, js);
}

public static Office readOfficeHelper(HashMap<Integer, Object> mp, JSONObject js) throws JSONException {
	if (!js.optString("ref").equals("")) {
		return (Office)(mp.get(js.getInt("ref")));
	}
	else {
		Office res = new Office();
		mp.put(js.getInt("id"), res);
		JSONArray fields = js.getJSONArray("values");
		JSONObject oneField = new JSONObject();
		oneField = fields.getJSONObject(0);
		res.setOccupant(readFacultyHelper(mp, oneField.getJSONObject("occupant")));
		oneField = fields.getJSONObject(1);
		res.setNumber(oneField.getInt("number"));
		oneField = fields.getJSONObject(2);
		res.setBuilding(oneField.getString("building"));
		return res;
	}
}

public static Office readOffice(JSONObject js) throws JSONException {
	HashMap<Integer, Object> mp = new HashMap<Integer, Object>();
	return readOfficeHelper(mp, js);
}

public static Faculty readFacultyHelper(HashMap<Integer, Object> mp, JSONObject js) throws JSONException {
	if (!js.optString("ref").equals("")) {
		return (Faculty)(mp.get(js.getInt("ref")));
	}
	else {
		Faculty res = new Faculty();
		mp.put(js.getInt("id"), res);
		JSONArray fields = js.getJSONArray("values");
		JSONObject oneField = new JSONObject();
		oneField = fields.getJSONObject(0);
		res.setName(oneField.getString("name"));
		oneField = fields.getJSONObject(1);
		for (int i = 0; i < oneField.getJSONArray("taught").length(); ++i) {
			res.addTaught(readCourseHelper(mp, oneField.getJSONArray("taught").getJSONObject(i)));
		}
		return res;
	}
}

public static Faculty readFaculty(JSONObject js) throws JSONException {
	HashMap<Integer, Object> mp = new HashMap<Integer, Object>();
	return readFacultyHelper(mp, js);
}

public static Grade readGradeHelper(HashMap<Integer, Object> mp, JSONObject js) throws JSONException {
	if (!js.optString("ref").equals("")) {
		return (Grade)(mp.get(js.getInt("ref")));
	}
	else {
		Grade res = new Grade();
		mp.put(js.getInt("id"), res);
		JSONArray fields = js.getJSONArray("values");
		JSONObject oneField = new JSONObject();
		oneField = fields.getJSONObject(0);
		res.setCourse(readCourseHelper(mp, oneField.getJSONObject("course")));
		oneField = fields.getJSONObject(1);
		res.setStudent(oneField.getString("student"));
		oneField = fields.getJSONObject(2);
		res.setGrade(oneField.getDouble("grade"));
		return res;
	}
}

public static Grade readGrade(JSONObject js) throws JSONException {
	HashMap<Integer, Object> mp = new HashMap<Integer, Object>();
	return readGradeHelper(mp, js);
}

}
