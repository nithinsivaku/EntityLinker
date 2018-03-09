package edu.unh.cs.cs980.EntityLinker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.unh.cs.cs980.EntityTools.DbpediaSpotlightLinker;
import edu.unh.cs.cs980.Evaluator.F1Measure;
import edu.unh.cs.treccar_v2.Data;
import edu.unh.cs.treccar_v2.Data.PageSkeleton;
import edu.unh.cs.treccar_v2.Data.Section;
import edu.unh.cs.treccar_v2.read_data.DeserializeData;

public class EntityLinker {

	private static void usage() {
		System.out.println("Command line parameters: paragraphID+contents_file");
		System.exit(-1);
	}

	/*
	 * Retrive the paragraph id and entites associated with the paraId
	 */
	private static Map<String, List<String>> retriveGroundTruth(String paraFile) throws FileNotFoundException {

		final FileInputStream fileInputStream2 = new FileInputStream(new File(paraFile));

		Map<String, List<String>> paraDetails = new HashMap<String, List<String>>();

		for (Data.Paragraph p : DeserializeData.iterableParagraphs(fileInputStream2)) {
			final String paraId = p.getParaId();
			final String paraText = p.getTextOnly();
			final List<String> paraEnities = p.getEntitiesOnly();
			paraDetails.put(paraId, paraEnities);

		}
		return paraDetails;
	}

	/*
	 * Retrive the DBpedia entities for the paragraphText
	 */
	private static Map<String, List<String>> retriveDBpediaEntries(String paraFile) throws FileNotFoundException {
		final FileInputStream fileInputStream2 = new FileInputStream(new File(paraFile));

		Map<String, List<String>> paraDB = new HashMap<String, List<String>>();

		DbpediaSpotlightLinker c = new DbpediaSpotlightLinker();

		for (Data.Paragraph p : DeserializeData.iterableParagraphs(fileInputStream2)) {
			final String paraId = p.getParaId();
			final String paraText = p.getTextOnly();
			
			List<String> entities = c.getEntities(paraText);
			List<String> lastWord = new ArrayList<String>();

			for (String url : entities) {
				String entityName = url.substring(url.lastIndexOf('/') + 1);
				lastWord.add(entityName);
			}

			paraDB.put(paraId, lastWord);

		}

		return paraDB;

	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setProperty("file.encoding", "UTF-8");

		if (args.length < 1)
			usage();

		final String paragraphsFile = args[0];

		Map<String, List<String>> groundTruth = new HashMap<String, List<String>>();
		Map<String, List<String>> DBpediaentries = new HashMap<String, List<String>>();

		groundTruth = retriveGroundTruth(paragraphsFile);
		
		
		DBpediaentries = retriveDBpediaEntries(paragraphsFile);
		System.out.println("DBdone");
		
		F1Measure f1 = new F1Measure();
		double DBpediaToolEvaluation = f1.evaluateMeasure(groundTruth,DBpediaentries); 

	}
}
