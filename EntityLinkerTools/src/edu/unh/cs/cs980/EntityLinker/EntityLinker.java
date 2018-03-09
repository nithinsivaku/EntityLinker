package edu.unh.cs.cs980.EntityLinker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.unh.cs.cs980.EntityTools.DbpediaSpotlightLinker;
import edu.unh.cs.treccar_v2.Data;
import edu.unh.cs.treccar_v2.Data.PageSkeleton;
import edu.unh.cs.treccar_v2.Data.Section;
import edu.unh.cs.treccar_v2.read_data.DeserializeData;

public class EntityLinker {
	
    private static void usage() {
        System.out.println("Command line parameters: paragraphID+contents_file");
        System.exit(-1);
    }
	
	private static void readLeadParagraphID(String paraFile) throws FileNotFoundException
	{
		
        final FileInputStream fileInputStream2 = new FileInputStream(new File(paraFile));
//        final Iterator<Data.Paragraph> paragraphIterator = DeserializeData.iterParagraphs(fileInputStream2);
//        for (int i=1; paragraphIterator.hasNext(); i++)
//        {
//        	final String paraId = paragraphIterator.next().getParaId();
//        	final String paraText = paragraphIterator.next().getTextOnly();
//        	//final List<String> paraEnities = paragraphIterator.next().getEntitiesOnly();
//        	System.out.println(i + " " + paraId + " - " + paraText );
//        }
        
        
        for(Data.Paragraph p : DeserializeData.iterableParagraphs(fileInputStream2))
        {
        	final String paraId = p.getParaId();
        	final String paraText = p.getTextOnly();
        	final List<String> paraEnities = p.getEntitiesOnly();
        	System.out.println( paraId + " - " + paraText + " - " + paraEnities.toString());
        }
	}
	
	 public static void main(String[] args) throws FileNotFoundException{
		System.setProperty("file.encoding", "UTF-8");

//        if (args.length < 1)
//            usage();
		
		final String paragraphsFile = args[0];
		//readLeadParagraphID(paragraphsFile);
		
		
		String text = "Prior to the German invasion of Norway on 9 April 1940, the Deuxième Bureau (French military intelligence) removed  of heavy water from the plant in Vemork in then-neutral Norway. The plant's managing director, Aubert, agreed to lend the heavy water to France for the duration of the war. The French transported it secretly to Oslo, to Perth, Scotland, and then to France. The plant remained capable of producing heavy water.";
		DbpediaSpotlightLinker c = new DbpediaSpotlightLinker ();
		
		ArrayList<String> entities = c.getEntities(text);
		
		
		// Take the last path segment of the uri.
		for(String url : entities)
		{
			String entityName = url.substring(url.lastIndexOf('/') + 1);
			System.out.println(entityName);
		}
		
	}
}
