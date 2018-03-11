# EntityLinker
1. This repository contains code that has different entity linking tools and performance of those tools against wikipedia ground truth.
2. Implementation of different algorithms for Entity Linking and ranking from the paper "Entity Linking with a Knowledge Base:
Issues, Techniques, and Solutions by Wei Shen, Jianyong Wang, Jiawei Han"

# Required
1. Java 1.8
2. Maven

# Instructions-to-reproduce
1. Download the zip or clone the entire repository.
2. Go to the project folder. [Note:-] You have to cd inside EntityLinkerTools 
3. #type mvn package [Note:-] You have to cd inside EntityLinkerTools, where you can see pom.xml 
4. Mavel will take care of everything, you should see Build success.
5. Now go to the target folder. You wil find a jar.
6. Run the jar by typing
7. java -jar [jarname] corpus (Note: without square[] bracket)
8. Here corpus is the directory for the LeadParagraphcorpus.

# jar
Always run the jar which has "with dependencies". In my case it is  EntityLinkerTools-0.0.1-SNAPSHOT-jar-with-dependencies.jar

# corpus
Here corpus is the leadParagraph.cbor

# Result
1. You will see the F1 Measure for DBpedia Spotlight against TREC-CAR dataset in the command window

# jar issue
If you are facing issues with Maven build, I have placed the jar in the repository. You can directly execute the jar by typing
java -jar EntityLinkerTools-0.0.1-SNAPSHOT-jar-with-dependencies.jar "path/to/corpus"

# Update
will modify Tagme tool soon.
and more results set comming.
