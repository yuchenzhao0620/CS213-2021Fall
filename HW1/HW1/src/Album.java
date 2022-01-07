/**
 * This class defines every album.
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
public class Album {
    private String title; //title of this album
    private String artist;//artist of this album
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate; //release date of this album
    private boolean isAvailable;//availability

    /**
     * Set the title of this album.
     *
     * @param title the title to set
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Get the title of this album.
     *
     * @return the title of this album
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Set the artist of this album.
     *
     * @param artist the artist to set
     */
    public void setArtist(String artist){
        this.artist = artist;
    }

    /**
     * Get the artist of this album.
     *
     * @return the artist of this album
     */
    public String getArtist(){
        return this.artist;
    }

    /**
     * Set the genre of this album.
     *
     * @param genre the genre to set
     */
    public void setGenre(Genre genre){
        this.genre = genre;
    }

    /**
     * Get the genre of this album.
     *
     * @return the genre of this album
     */
    public Genre getGenre(){
        return this.genre;
    }

    /**
     * Set the release date of this album.
     *
     * @param releaseDate the release date to set
     */
    public void setReleaseDate(Date releaseDate){
        this.releaseDate = releaseDate;
    }

    /**
     * Get the release date of this album.
     *
     * @return the release date of this album
     */
    public Date getReleaseDate(){
        return this.releaseDate;
    }

    /**
     * Set the availability of this album.
     *
     * @param isAvailable the availability to set
     */
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    /**
     * Get the availability of this album.
     *
     * @return the availability of this album
     */
    public boolean getAvailable(){
        return this.isAvailable;
    }

    /**
     * Determines whether an album is the same as this one.
     *
     * @param obj an album to compare with this one
     * @return  if these two albums are identical or not
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this)  //Check that the parameter is of a reference to this object
            return true;
        if(!(obj instanceof Album))//Check that the parameter is of the correct type
            return false;
        Album album = (Album) obj;
        return title == album.title && artist == album.artist;
    }

    /**
     * Print the information of this album to the console.
     *
     * @return the information of this album
     */
    @Override
    public String toString(){
        if(this.isAvailable){
            return this.title + "::" + this.artist + "::" + this.genre + "::" + this.releaseDate.getMonth() + "/"
                    + this.releaseDate.getDay() + "/" +this.releaseDate.getYear() + "::is available";
        }else{
            return this.title + "::" + this.artist + "::" + this.genre + "::" + this.releaseDate.getMonth() + "/"
                    + this.releaseDate.getDay() + "/" +this.releaseDate.getYear() + "::is not available";
        }
    }
}