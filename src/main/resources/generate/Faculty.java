import org.json.*;
import java.util.*;

public class Faculty {
private String name;
private ArrayList<Course> taught;

public Faculty() {
	taught = new ArrayList<Course>();
}


public String getName() {
	return name;
}

public void setName(String value) {
	name = value;
}

public Course getTaught(int index) {
	return taught.get(index);
}

public void setTaught(int index, Course value) {
	taught.set(index, value);
}

public void addTaught(Course toAdd) {
	taught.add(toAdd);
}

public int numTaught() {
	return taught.size();
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
		inner += "{\"name\" : \"" + name + "\"}, ";
		inner += "{\"name\" : " + name + "}, ";
		inner += "{\"taught\" : [";
		for (int i = 0; i < taught.size(); ++i) {
			inner += taught.get(i).toJSONHelper(mp) + ", ";
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
