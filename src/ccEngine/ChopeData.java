package ccEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Chope les liens des rotations de la page mensuelle 
 */
public class ChopeData {

	public HashSet<String> links = new HashSet<String>();
	
	public ChopeData(String source) {
		extractLinks(source);	}
	
	private void extractLinks(String source){
		
		String motif = "(/crew/main\\?event=navigationActivite&type=R&m=.*&planning=[0-9]{1})";
		Pattern regex = Pattern.compile(motif);
		Matcher result = regex.matcher(source);
		
		while(result.find()){
			links.add(result.group(0));
		}
	
	}
}
