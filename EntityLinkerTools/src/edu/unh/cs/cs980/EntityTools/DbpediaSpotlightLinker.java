package edu.unh.cs.cs980.EntityTools;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import org.json.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.List;

//import org.dbpedia.spotlight.exceptions.AnnotationException;
//import org.dbpedia.spotlight.model.DBpediaResource;
//import org.dbpedia.spotlight.model.Text;

public class DbpediaSpotlightLinker {

	List<String> results = new ArrayList<>();

	public List<String> getEntities(String input) {
		try {

			// String text = "Prior to the German invasion of Norway on 9 April
			// 1940, the Deuxième Bureau (French military intelligence) removed
			// of heavy water from the plant in Vemork in then-neutral Norway.
			// The plant's managing director, Aubert, agreed to lend the heavy
			// water to France for the duration of the war. The French
			// transported it secretly to Oslo, to Perth, Scotland, and then to
			// France. The plant remained capable of producing heavy water.";

			String uri = "http://model.dbpedia-spotlight.org/en/annotate?text=" + URLEncoder.encode(input, "UTF-8");

			URL url = new URL(uri);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;



			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			conn.disconnect();

			JSONObject response = new JSONObject(sb.toString());

			// for ( Object obj : response.getJSONArray("Resources") ) {
			// JSONObject entry = (JSONObject) obj;
			// results.add( new DBpediaentries( entry.optString("@URI") ) );
			// }

			for (int i = 0; i < response.getJSONArray("Resources").length(); i++) {
				JSONObject entry = response.getJSONArray("Resources").getJSONObject(i);
				// System.out.println(entry.optString("@URI"));
				results.add(entry.optString("@URI"));
			}

		}

		catch (MalformedURLException e) {
			e.getMessage();

		} catch (IOException e) {
			e.getMessage();

		} catch (Exception e) {
			e.getMessage();
		}

		return results;

	}

}
