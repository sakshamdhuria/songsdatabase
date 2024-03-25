// -== CS400 Spring 2024 File Header Information ==-
// Name:Saksham Dhuria
// Email: sdhuria@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: annoying but fun time uh nothing much be nice to comment code it was fialed attempts 
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Backend implements BackendInterface {
	IterableSortedCollection<SongInterface> tree;
	public Backend(IterableSortedCollection<SongInterface> tr){
		this.tree=tr;
	}

	//to keep track of whether the isGetRange, isFiltered  has been called yet
	//also keeps track of the recent input for maxYr, recentLow, recentHigh
	boolean isGetRangeCalled = false;
	boolean isFilterCalled=false;
	int recentLow, recentHigh;
	int maxYr;

	/**
	*Loads data from the .csv file referenced by filename.
	* @param filename is the name of the csv file to load data from
	* @throws IOException when there is trouble finding/reading file
	*/
	public void readData(String fileName) throws IOException{
			Scanner scn = new Scanner(new File(fileName));
			if(scn.hasNextLine()){
				scn.nextLine();
			}
			while(scn.hasNextLine()){
                        	String line= scn.nextLine();

                        //quick explanation of how i am getting the stuff
                        // i am iterating from the back using a two pointer idea
                        // and between any two comma's i am storing that value into the prs array
                        // then i am storing the most confusing part the name by just a normal from the 0 index to last comma we discussed
                        	//int lastComma=line.length();
                        	//int indx=13;
                       		 String[] prts = line.split(",");
				
/*                       		 for(int i=line.length()-1;indx!=0 &&  i>=0; i--){
                                	if(line.charAt(i)== ','){
                                        	prts[indx]=line.substring(i+1, lastComma);
                                        	lastComma=i;
                                	        indx--;
                                	}
                        	}
                        	prts[0]=line.substring(0, lastComma);
*/
				
                        	//now just gotta go store every element into its respective thing
                        	String title="";
				for(int i =0; i<prts.length-13; i++){
					title+=prts[i];
				}
                       		String artist=prts[prts.length-13];
                        	String genre=prts[prts.length-12];
                        	int year=Integer.parseInt(prts[prts.length-11]);
                        	int bpm=Integer.parseInt(prts[prts.length-10]);
                        	int nrgy=Integer.parseInt(prts[prts.length-9]);
                        	int dnce=Integer.parseInt(prts[prts.length-8]);
                        	int dB=Integer.parseInt(prts[prts.length-7]);
                       		int live=Integer.parseInt(prts[prts.length-6]);
                        	SongInterface sng = new Song(title, artist, genre, year, bpm, nrgy, dnce, dB, live);
				if (sng != null){
				 	tree.insert(sng);
					//masterList.add(sng);
				}
                	}
	}

	/**
        * Retrieves a list of song titles for songs that have a Speed (BPM)
	* within the specified range (sorted by BPM in ascending order).  If
	* a maxYear filter has been set using filterOldSongs(), then only songs
	* on Billboard durring or before that maxYear should be included in the
	* list that is returned by this method.
	*
	* Note that either this bpm range, or the resulting unfiltered list
	* of songs should be saved for later use by the other methods defined in
	* this class.
	*
	* @param low is the minimum Speed (BPM) of songs in the returned list
     	* @param hight is the maximum Speed (BPM) of songs in the returned list
     	* @return List of titles for all songs in specified range
     	*/
    public List<String> getRange(int low, int high) {
		isGetRangeCalled=true;
		recentLow=low;
		recentHigh=high;
		List<String> ans = new ArrayList<>();
		Iterator<SongInterface> it = tree.iterator();
		//it.setIterationStartPoint(low);
		while(it.hasNext()){
			SongInterface sng = it.next();
//			System.out.println("here in getRange "+  sng.getTitle());
			if(sng == null) continue;
			int bpm = sng.getBPM();
			if(bpm<=high && bpm>=low){
				//is filter has this functional that if we call it then after that every
				//call to getRange the year of the song must be less than the maxYear set
				if(!isFilterCalled || (isFilterCalled && sng.getYear()<=maxYr)){
					ans.add(sng.getTitle());
				}
			}
		}
	return ans;
    }

    /**
     * Filters the list of songs returned by future calls of getRange() and
     * fiveMostDanceable() to only include older songs.  If getRange() was
     * previously called, then this method will return a list of song titles
     * (sorted in ascending order by Speed BPM) that only includes songs on
     * Billboard on or before the specified maxYear.  If getRange() was not
     * previously called, then this method should return an empty list.
     *
     * Note that this maxYear threshold should be saved for later use by the
     * other methods defined in this class.
     *
     * @param maxYear is the maximum year that a returned song was on Billboard
     * @return List of song titles, empty if getRange was not previously called
     */
    public List<String> filterOldSongs(int maxYear) {
	List<String> ans = new ArrayList<>();
	if(isGetRangeCalled){
		Iterator<SongInterface> it = tree.iterator();
		while(it.hasNext()){
			SongInterface sng = it.next();
			if(sng.getBPM()<=recentHigh && sng.getBPM()>=recentLow && sng.getYear()<=maxYear){
				ans.add(sng.getTitle());
			}
		}
	}
	isFilterCalled=true;
	maxYr=maxYear;
	return ans;
    }

    /**
     * This method makes use of the attribute range specified by the most
     * recent call to getRange().  If a maxYear threshold has been set by
     * filterOldSongs() then that will also be utilized by this method.
     * Of those songs that match these criteria, the five most danceable will
     * be returned by this method as a List of Strings in increasing order of
     * speed (bpm).  Each string contains the danceability followed by a
     * colon, a space, and then the song's title.
     * If fewer than five such songs exist, display all of them.
     *
     * @return List of five most danceable song titles and their danceabilities
     * @throws IllegalStateException when getRange() was not previously called.
     */
    public List<String> fiveMostDanceable() {
	if(isGetRangeCalled){
		List<String> ans = new ArrayList<>();
		Iterator<SongInterface> it = tree.iterator();
                while(it.hasNext()){
                        SongInterface sng = it.next();
                        int danceable  = sng.getDanceability();
			if(sng.getBPM()<= recentHigh && sng.getBPM()>=recentLow && (!isFilterCalled || (isFilterCalled && sng.getYear()<=maxYr))){
				ans.add(Integer.toString(danceable) + ": "+ sng.getTitle());
			}
		}
		Collections.sort(ans);
			//sorting will sort by danceability as thats the first part of the string and numbers in ascii should be sorted correctly
			//the following  should remove it so only the last 5 are remaining
		int size=ans.size();
		int startIndex=Math.max(0, size-5);
		return ans.subList(startIndex, size);
	}
	else{
		System.out.println("No call yet to range");
		return null;
    	}
    }
}
