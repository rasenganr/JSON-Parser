import org.json.*;
import java.util.*;

public class Office {
private Faculty occupant;
private int number;
private String building;


public Faculty getOccupant() {
	return occupant;
}

public void setOccupant(Faculty value) {
	occupant = value;
}

public int getNumber() {
	return number;
}

public void setNumber(int value) {
	number = value;
}

public String getBuilding() {
	return building;
}

public void setBuilding(String value) {
	building = value;
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
		inner += "{\"occupant\" : " + occupant.toJSONHelper(mp) + "}, ";
		inner += "{\"number\" : " + number + "}, ";
		inner += "{\"building\" : \"" + building + "\"}, ";
		inner += "{\"building\" : " + building + "}, ";
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
