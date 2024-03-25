// -== CS400 Spring 2024 File Header Information ==-
// Name:Saksham Dhuria
// Email: sdhuria@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: boring class 
public class Song implements SongInterface{
	private String title;
	private String artist;
	private String genres;
	private int year;
	private int bpm;
	private int energy;
	private int danceability;
	private int loudness;
	private int liveness;

	/*
	main method for constructor 
	*/
	public Song(String title, String artist, String genres, int year, int bpm, int energy, int danceability, int loudness, int liveness){
		this.title=title;
		this.artist=artist;
		this.genres=genres;
		this.year=year;
		this.bpm=bpm;
		this.energy=energy;
		this.danceability=danceability;
		this.loudness=loudness;
		this.liveness=liveness;
	}
	/*
        getter method for title
        */
	@Override
	public String getTitle(){
		return title;
	}
	/*
	getter method for artist
	*/
	@Override
        public String getArtist(){
                return artist;
        }

	/*
	getter method for genre
	*/

	@Override
        public String getGenres(){
                return genres;
        }
        /*
        getter method for year
        */
	@Override
        public int getYear(){
                return year;
        }
        /*
        getter method for bpm
        */
	@Override
        public int getBPM(){
                return bpm;
        }

        /*
        getter method for energy
        */
	@Override
	public int getEnergy(){
		return energy;
	}
        /*
        getter method for danceability
        */
	@Override
	public int getDanceability(){
		return danceability;
	}
        /*
        getter method for loudness
        */
	@Override
	public int getLoudness(){
		return loudness;
	}
        /*
        getter method for liveliness
        */
	@Override
	public int getLiveness(){
		return liveness;
	}

        /*
        compare to based on getBPM
        */
	@Override
	public int compareTo(SongInterface other){
		if(this.getBPM()<other.getBPM()){
			return -1;
		}
		if(this.getBPM()==other.getBPM()){
			return 0;
		}

		return 1;
	}
}
