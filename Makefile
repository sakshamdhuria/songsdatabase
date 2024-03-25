runBDTests:
	javac -cp .:../junit5.jar BackendDeveloperTests.java
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests
clean:
	rm -f *.class
runFDTests: FrontendImplementations.java App.java BackendPlaceholder.java ISCPlaceholder.java IterableSortedCollection.java SongInterface.java SortedCollectionInterface.java FrontendDeveloperTests.java
	javac FrontendImplementations.java
	javac App.java
	javac BackendPlaceholder.java
	javac ISCPlaceholder.java
	javac IterableSortedCollection.java
	javac SongInterface.java
	javac SortedCollectionInterface.java
	javac -cp ../junit5.jar:. FrontendDeveloperTests.java
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests
runApp:
	javac -cp ../junit5.jar:. App.java
	javac App.java
	java App
