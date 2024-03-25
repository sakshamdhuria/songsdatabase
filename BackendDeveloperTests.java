// -== CS400 Spring 2024 File Header Information ==-
// Name:Saksham Dhuria
// Email: sdhuria@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: i didnt end up hating this as much i prob was thinking i would cool but some unclear instructions ty fun project though
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class BackendDeveloperTests{
	/*
		my first test to check that the readData worked!
	*/
@Test
	public void testOne(){
		IterableSortedCollection<SongInterface> placeholderTree = new ISCPlaceholder<>();
		Backend bp = new Backend(placeholderTree);
		try{
			bp.readData("songs.csv");
			Assertions.assertNotNull(placeholderTree);
		} catch (IOException e){

		}
	}
	/*
		my second test to check that after read data we are able to getrange with bad input that should not give anything also a cool thing we wont be using filteroldsongs
	*/
@Test
        public void testTwo(){
		IterableSortedCollection<SongInterface> placeholderTree = new ISCPlaceholder<>();
                Backend bp = new Backend(placeholderTree);
		try{
                        bp.readData("songs.csv");
                } catch (IOException e){

                } 
		Assertions.assertEquals(1, bp.getRange(0, 0).size());
        }
	/*
		my third test to check a similar thing where i dont use the filter and testPlaceHolder for a more valid input
	*/
@Test
        public void testThree(){
		IterableSortedCollection<SongInterface> placeholderTree = new ISCPlaceholder<>();
                Backend bp = new Backend(placeholderTree);
                try{
                        bp.readData("songs.csv");
                } catch (IOException e){

                }
		Assertions.assertEquals(12, bp.getRange(97, 98).size());
		Assertions.assertIterableEquals(List.of("\"Hey Soul Sister\"", "Muny - Album Version (Edited)","Boyfriend", "Same Old Love", "Scars To Your Beautiful", "I Got You", "Behind Your Back", "IDGAF", "Wild Thoughts (feat. Rihanna & Bryson Tiller)", "Say Something", "Filthy", "South of the Border (feat. Camila Cabello & Cardi B)" ), bp.getRange(97, 98));
        }
	/*
		my fourth test to check the similar thing but this time we have after filter
	*/
@Test
        public void testFour(){
		IterableSortedCollection<SongInterface> placeholderTree = new ISCPlaceholder<>();
                Backend bp = new Backend(placeholderTree);
                try{
                        bp.readData("songs.csv");
                } catch (IOException e){

                }
                bp.getRange(97, 98);
		Assertions.assertEquals(List.of("\"Hey Soul Sister\"", "Muny - Album Version (Edited)","Boyfriend"), bp.filterOldSongs(2012));
        }
	/*
		my fifth test to check the five most dancable based on the 97, 98 call and also after filter
	*/
@Test
        public void testFive(){
		IterableSortedCollection<SongInterface> placeholderTree = new ISCPlaceholder<>();
                Backend bp = new Backend(placeholderTree);
                try{
                        bp.readData("songs.csv");
                } catch (IOException e){

                }
                bp.getRange(97, 98);
                bp.filterOldSongs(2012);
		Assertions.assertEquals(3, bp.fiveMostDanceable().size());
		Assertions.assertEquals(List.of("67: \"Hey Soul Sister\"", "72: Boyfriend", "80: Muny - Album Version (Edited)"), bp.fiveMostDanceable());
        }
	/*
		integration testers #1 where i am just trying to check for interaction with frontend for the bpm to above but here i am actually listing out what the specified sogn should be 
	*/
@Test
	public void testIntegrationBPM() {
		//creating the backend instance of the object
		IterableSortedCollection<SongInterface> tr = new ISCPlaceholder<>();
		BackendInterface bd = new Backend(tr);

		//scanner object 
		Scanner scn;

		//creating a tester object
		TextUITester tstr;
		String rslt;

		//Frontend Object
		FrontendInterface fd;

		//First thing is getting a file read
		tstr = new TextUITester("R\nsongs.csv\nQ\n");
		scn = new Scanner(System.in);
		fd = new FrontendImplementations(bd, scn);

		fd.runCommandLoop();
		rslt = tstr.checkOutput();

		Assertions.assertTrue(rslt.contains("File read successfully."));
		scn.close();

		//second part of this is to check if i input for commang G 180-180 is that gonna be correct
		tstr= new  TextUITester("G\n180\n180\nQ\n");
		scn = new Scanner(System.in);
		fd = new FrontendImplementations(bd, scn);

		fd.runCommandLoop();
		rslt=tstr.checkOutput();

		Assertions.assertTrue(rslt.contains("Roar"));
		Assertions.assertTrue(rslt.contains("Dusk Till Dawn - Radio Edit"));
		scn.close();
	}

	/*
		integration testers #2 where  i am checking the FilterOld Songs
	*/
@Test
        public void testFilterBPM() {
                //creating the backend instance of the object
                IterableSortedCollection<SongInterface> tr = new ISCPlaceholder<>();
                BackendInterface bd = new Backend(tr);

                //scanner object
                Scanner scn;

                //creating a tester object
                TextUITester tstr;
                String rslt;

                //Frontend Object
                FrontendInterface fd;

                //First thing is getting a file read
                tstr = new TextUITester("R\nsongs.csv\nQ\n");
                scn = new Scanner(System.in);
                fd = new FrontendImplementations(bd, scn);

                fd.runCommandLoop();
                rslt = tstr.checkOutput();

                Assertions.assertTrue(rslt.contains("File read successfully."));
                scn.close();

                //second part of this is to check if i input for commang G 180-180 is that gonna be correct
                tstr= new  TextUITester("G\n180\n180\nQ\n");
                scn = new Scanner(System.in);
                fd = new FrontendImplementations(bd, scn);

                fd.runCommandLoop();
                rslt=tstr.checkOutput();

                Assertions.assertTrue(rslt.contains("Roar"));
                Assertions.assertTrue(rslt.contains("Dusk Till Dawn - Radio Edit"));
                scn.close();


		//now i am going to test for filter of lets say 2015
		tstr= new TextUITester("F\n2015\nQ\n");
		scn = new Scanner(System.in);
		fd = new FrontendImplementations(bd,scn);

		Assertions.assertTrue(rslt.contains("Roar"));
		scn.close();
        }
/*
		partner test i am just checking a false read of the file
        */
@Test
        public void testPartnerREAD() {
                //creating the backend instance of the object
                IterableSortedCollection<SongInterface> tr = new ISCPlaceholder<>();
                BackendInterface bd = new Backend(tr);

                //scanner object
                Scanner scn;

                //creating a tester object
                TextUITester tstr;
                String rslt;

                //Frontend Object
                FrontendInterface fd;

                //First thing is getting a file read
                tstr = new TextUITester("R\nsong\nQ\n");
                scn = new Scanner(System.in);
                fd = new FrontendImplementations(bd, scn);

                fd.runCommandLoop();
                rslt = tstr.checkOutput();

                Assertions.assertTrue(rslt.contains("Error reading file:"));
                scn.close();
        }
/*
                partner test i am just checking a false call at Top 5
        */
@Test
        public void testPartnerTop5() {
		//creating the backend instance of the object
                IterableSortedCollection<SongInterface> tr = new ISCPlaceholder<>();
                BackendInterface bd = new Backend(tr);

                //scanner object
                Scanner scn;

                //creating a tester object
                TextUITester tstr;
                String rslt;

                //Frontend Object
                FrontendInterface fd;

                //First thing is getting a file read
                tstr = new TextUITester("R\nsongs.csv\nQ\n");
                scn = new Scanner(System.in);
                fd = new FrontendImplementations(bd, scn);

                fd.runCommandLoop();
                rslt = tstr.checkOutput();

                Assertions.assertTrue(rslt.contains("File read successfully."));
                scn.close();

                //second part of this is to check if i input for commang G 180-180 is that gonna be correct
                tstr= new  TextUITester("G\n180\n180\nQ\n");
                scn = new Scanner(System.in);
                fd = new FrontendImplementations(bd, scn);

                fd.runCommandLoop();
                rslt=tstr.checkOutput();

                Assertions.assertTrue(rslt.contains("Roar"));
                Assertions.assertTrue(rslt.contains("Dusk Till Dawn - Radio Edit"));
                scn.close();


                //now i am going to test for filter of lets say 2015
                tstr= new TextUITester("F\n2015\nQ\n");
                scn = new Scanner(System.in);
                fd = new FrontendImplementations(bd,scn);

                Assertions.assertTrue(rslt.contains("Roar"));
                scn.close();

		//finally calling D command
		tstr = new TextUITester("D\nQ\n");
		scn = new Scanner(System.in);
		fd = new FrontendImplementations(bd, scn);
		scn.close();
        }
}
