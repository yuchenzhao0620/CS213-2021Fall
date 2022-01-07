/**
 * Collection class
 * To add an album, remove an album, lending out and return
 * Save the albums in Collection Album[] albums
 * Print out the albums in the collection, or sort by date and genre
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
public class Collection {
    private Album[] albums;
    /**
     * number of albums currently in the collection
     */
    private int numAlbums;
    /**
     * Constructor. Create an array which size is 4.
     */
    public Collection(){
        this.albums = new Album[4];
    }
    /**
     * Add an album to the collection.
     *
     * @param album a new album to add
     * @return if successfully added or not
     */
    public boolean add(Album album){
        if(this.find(album) != -1){
            return false;
        }
        if(this.numAlbums == this.albums.length){ //check the length if it needs more space
            grow();
        }
        albums[this.numAlbums] = album;
        this.numAlbums++;
        return true;
    }
    /**
     * When the array is full, create a 4 size larger array.
     */
    private void grow(){
        Album[] newAlbums = new Album[this.albums.length + 4];
        for(int i = 0; i < numAlbums; i++){
            newAlbums[i] = albums[i];
        }
        this.albums = newAlbums;
    }
    /**
     * Search a specific album in the collection
     *
     * @param album the album to find in the collection
     * @return the index of found album. -1 stands for not found
     */
    private int find(Album album){
        for(int i = 0; i < numAlbums; i++){
            if(this.albums[i].getArtist().equals(album.getArtist())
                    && this.albums[i].getTitle().equals(album.getTitle())){
                return i;
            }
        }
        return -1;
    }
    /**
     * Remove an album in the collection.
     *
     * @param album the album to remove
     * @return if successfully removed or not
     */
    public boolean remove(Album album){
        if(this.find(album) == -1){
            return false; //"is not in the collection"
        }
        for( int i = this.find(album); i < numAlbums; i++){
            this.albums[i] = this.albums[i+1];
        }
        this.numAlbums--;
        return true;
    }
    /**
     * Lend out an album in the collection.
     *
     * @param album the album to lend out
     * @return if successfully lent out or not
     */
    public boolean lendingOut(Album album){ //set to not available
        int index = this.find(album);
        if(index == -1){
            return false;
        }else if(!this.albums[index].getAvailable()){
            return false;
        }
        this.albums[index].setAvailable(false);
        return true;
    }
    /**
     * Return an album to the collection.
     *
     * @param album the album to return
     * @return if successfully returned or not
     */
    public boolean returnAlbum(Album album){ // set to available
        if(this.find(album) != -1){
            if(!this.albums[this.find(album)].getAvailable()){
                this.albums[this.find(album)].setAvailable(true);
                return true;
            }
        }
        return false;
    }
    /**
     * Display the list without specifying the order.
     */
    public void print() {
        if(!(numAlbums == 0)){
            System.out.println("*List of albums in the collection.");
            for(int i = 0; i < numAlbums; i++){
                System.out.println(this.albums[i].toString());
            }
            System.out.println("*End of list");
        }else{
            System.out.println("The collection is empty.");
        }
    }
    /**
     * Display the list with the order of release date.
     */
    public void printByReleaseDate() {
        int length = this.numAlbums;
        Album[] sortByDate = new Album[length];
        for(int i = 0; i < length; i++){
            sortByDate[i] = this.albums[i];
        }
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(sortByDate[i].getReleaseDate().compareTo(sortByDate[j].getReleaseDate()) == 1){
                    Album[] temp = new Album[1];
                    temp[0] = sortByDate[j];
                    sortByDate[j] = sortByDate[i];
                    sortByDate[i] = temp[0];
                }
            }
        }
        if(!(numAlbums == 0)){
            System.out.println("*Album collection by the release dates.");
            for(int i = 0; i < numAlbums; i++){
                System.out.println(sortByDate[i].toString());
            }
            System.out.println("*End of list");
        }else{
            System.out.println("The collection is empty.");
        }

    }
    /**
     * Display the list with the order of genre.
     */
    public void printByGenre() {
        if(numAlbums != 0){
            System.out.println("*Album collection by genre.");
            for(int i = 0; i < numAlbums; i++){
                if(this.albums[i].getGenre() == Genre.Classical){
                    System.out.println(this.albums[i].toString());
                }
            }
            for(int i = 0; i < numAlbums; i++){
                if(this.albums[i].getGenre() == Genre.Country){
                    System.out.println(this.albums[i].toString());
                }
            }
            for(int i = 0; i < numAlbums; i++){
                if(this.albums[i].getGenre() == Genre.Jazz){
                    System.out.println(this.albums[i].toString());
                }
            }
            for(int i = 0; i < numAlbums; i++){
                if(this.albums[i].getGenre() == Genre.Pop){
                    System.out.println(this.albums[i].toString());
                }
            }
            for(int i = 0; i < numAlbums; i++){
                if(this.albums[i].getGenre() == Genre.Unknown){
                    System.out.println(this.albums[i].toString());
                }
            }
            System.out.println("*End of list");
        }else{
            System.out.println("The collection is empty.");
        }

    }
}