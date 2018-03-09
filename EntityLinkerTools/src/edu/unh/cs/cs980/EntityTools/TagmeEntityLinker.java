package edu.unh.cs.cs980.EntityTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONObject;

public class TagmeEntityLinker {

	ArrayList<String> results = new ArrayList<>();

	public ArrayList<String> getEntities(String input) {
		try {

			String text = "Prior to the German invasion of Norway on 9 April 1940, the Deuxième Bureau (French military intelligence) removed  of heavy water from the plant in Vemork in then-neutral Norway. The plant's managing director, Aubert, agreed to lend the heavy water to France for the duration of the war. The French transported it secretly to Oslo, to Perth, Scotland, and then to France. The plant remained capable of producing heavy water.";

			String uri = "https://tagme.d4science.org/tagme/tag" + URLEncoder.encode(text, "UTF-8");

			URL url = new URL(uri);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			int responseCode = conn.getResponseCode();

			System.out.println("\nSending 'GET' request to URL : " + uri);
			System.out.println("Response Code : " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;

			System.out.println("Hello:" + conn.getInputStream().toString());

			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			conn.disconnect();

			JSONObject response = new JSONObject(sb.toString());
			for (int i = 0; i < response.getJSONArray("Resources").length(); i++) {
				JSONObject entry = response.getJSONArray("Resources").getJSONObject(i);
				System.out.println(entry.optString("@URI"));
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
