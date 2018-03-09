# EntityLinker
This repository has different entity linking tools and performance of those tools against wikipedia ground truth.


#Instructions-to-reproduce
1. Download the zip or clone the entire repository.
2. Go to the project folder.
3. Anywhere in the project folder #Type mvn package
4. Mavel will take care of everything, you should see Build success.
5. Now go to the target folder. You wil find a jar.
6. Run the jar by typing
7. java -jar [jarname] corpus (Note: without square[] bracket)
8. Here corpus is the directory for the LeadParagraphcorpus.

#Result
1. You will see the F1 score for DBpedia Spotlight in the command window
