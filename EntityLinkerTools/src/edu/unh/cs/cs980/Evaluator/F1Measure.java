package edu.unh.cs.cs980.Evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class F1Measure {

	public List<Double> evaluateMeasure(Map<String, List<String>> groundTruth,
			Map<String, List<String>> dBpediaentries) {
		// TODO Auto-generated method stub
		double f1Score = 0.0;
		int entityRetireved = 0;
		int entityRelevant = 0;
		int relevantEntityRetrieved = 0;
		// int noOfCorrectEntities = 0;
		List<Double> F1ScoreList = new ArrayList<Double>();
		for (Entry<String, List<String>> iter : groundTruth.entrySet()) {
			List<String> trueEntities = new ArrayList<String>();
			List<String> retrievedEntities = new ArrayList<String>();

			// Get the values of truth and retrieved to commpare the reuslt
			trueEntities = iter.getValue();
			retrievedEntities = dBpediaentries.get(iter.getKey());

			entityRetireved += trueEntities.size();
			entityRelevant += retrievedEntities.size();
			for (int i = 0; i < retrievedEntities.size(); i++) {
				if (trueEntities.contains(retrievedEntities.get(i))) {
					relevantEntityRetrieved++;
				}

			}

		}

		double retrievedEntities = (double) entityRelevant / groundTruth.size();
		double relevantEntities = (double) entityRetireved / groundTruth.size();
		double entitiesRetrievdRelevant = (double) (relevantEntityRetrieved / groundTruth.size());

		double r = entitiesRetrievdRelevant /retrievedEntities;
		double p = entitiesRetrievdRelevant / relevantEntities;

		f1Score = (2 * p * r) / (r + p);

		F1ScoreList.add(f1Score);

		return F1ScoreList;
	}

}
