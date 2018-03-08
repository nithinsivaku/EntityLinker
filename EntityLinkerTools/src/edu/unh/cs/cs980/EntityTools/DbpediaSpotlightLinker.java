package edu.unh.cs.cs980.EntityTools;

import org.apache.lucene.document.Document;
import org.json.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import org.dbpedia.spotlight.exceptions.AnnotationException;
import org.dbpedia.spotlight.model.DBpediaResource;
import org.dbpedia.spotlight.model.Text;

public class DbpediaSpotlightLinker extends AnnotationClient{
	
	private final static String API_URL = "http://localhost:2222/rest";
	private static final double CONFIDENCE = 0.2;
	private static final int SUPPORT = 0;
	
	String para = "";


	
	


	@Override
	public List<DBpediaResource> extract(Text text) throws AnnotationException {

		LOG.info("Querying url");
		
		String spotlightResponse;
		try {
			GetMethod getMethod = new GetMethod(API_URL + "rest/annotate/?" +
					"confidence=" + CONFIDENCE
					+ "&support=" + SUPPORT
					+ "&text=" + URLEncoder.encode(text.text(), "utf-8"));
			getMethod.addRequestHeader(new Header("Accept", "application/json"));

			spotlightResponse = request(getMethod);
		} catch (UnsupportedEncodingException e) {
			throw new AnnotationException("Could not encode text.", e);
		}

		assert spotlightResponse != null;

		JSONObject resultJSON = null;
		JSONArray entities = null;
		
		try {
			resultJSON = new JSONObject(spotlightResponse);
			entities = resultJSON.getJSONArray("Resources");
		} catch (JSONException e) {
			throw new AnnotationException("Received invalid response from DBpedia Spotlight API.");
		}
		
		
		LinkedList<DBpediaResource> resources = new LinkedList<DBpediaResource>();
		for(int i = 0; i < entities.length(); i++) {
			try {
				JSONObject entity = entities.getJSONObject(i);
				resources.add(
						new DBpediaResource(entity.getString("@URI"),
								Integer.parseInt(entity.getString("@support"))));

			} catch (JSONException e) {
                LOG.error("JSON exception "+e);
            }

		}


		return resources;
	}
	
		

	
	
	
	

}
