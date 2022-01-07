import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * CollectionManager class
 * Manage the command by using switch
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
public class CollectionManager {
    /**
     * Create the collection
     * Passing the collection and the input to the command method.
     */
    public void run() {
        Collection collection = new Collection();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(input.nextLine());
            command(collection,st);
        }
    }

    /**
     * Read the command lines from the console.
     *
     * @param collection a collection created by run().
     * @param st StringTokenizer from run().
     */
    private void command(Collection collection,StringTokenizer st) {
        Album tempAlbum = new Album(); //Create the new album to temp the data
        String c = st.nextToken(","); //Get the command
        if(c.compareTo("A") == 0){
            tempAlbum.setTitle(st.nextToken(","));
            tempAlbum.setArtist(st.nextToken(","));
            String genre = st.nextToken(",");
            genre = genre.substring(0,1).toUpperCase() + genre.substring(1);
            switch (genre){
                case "Classical" :
                    tempAlbum.setGenre(Genre.Classical);
                    break;
                case "Country" :
                    tempAlbum.setGenre(Genre.Country);
                    break;
                case "Jazz" :
                    tempAlbum.setGenre(Genre.Jazz);
                    break;
                case "Pop" :
                    tempAlbum.setGenre(Genre.Pop);
                    break;
                default:
                    tempAlbum.setGenre(Genre.Unknown);
                    break;
            }
            Date tempDate = new Date(st.nextToken(","));
            if(tempDate.isValid()){
                tempAlbum.setReleaseDate(tempDate);
                tempAlbum.setAvailable(true);
                if(!collection.add(tempAlbum)){
                    System.out.println(tempAlbum + " >> " + "is already in the collection.");
                }else{
                    System.out.println(tempAlbum + " >> " + "added.");
                }
            }else {
                System.out.println("Invalid Date");
            }
        }else if(c.compareTo("D") == 0){
            tempAlbum.setTitle(st.nextToken(","));
            tempAlbum.setArtist(st.nextToken(","));
            if(!collection.remove(tempAlbum)){
                System.out.println(tempAlbum.getTitle() +"::"+ tempAlbum.getArtist() + " >> " +
                        "is not in the collection.");
            }else{
                System.out.println(tempAlbum.getTitle() +"::"+ tempAlbum.getArtist() + " >> " + "deleted.");
            }
        }else if(c.compareTo("L") == 0){
            tempAlbum.setTitle(st.nextToken(","));
            tempAlbum.setArtist(st.nextToken(","));
            if(!collection.lendingOut(tempAlbum)){
                System.out.println(tempAlbum.getTitle() +"::"+ tempAlbum.getArtist() + " >> " +
                        "is not available");
            }else{
                System.out.println(tempAlbum.getTitle() +"::"+ tempAlbum.getArtist() + " >> " +
                        "lending out and set to not available.");
            }
        }else if(c.compareTo("R") == 0){
            tempAlbum.setTitle(st.nextToken(","));
            tempAlbum.setArtist(st.nextToken(","));
            if(!collection.returnAlbum(tempAlbum)){
                System.out.println(tempAlbum.getTitle() +"::"+ tempAlbum.getArtist() + " >> " +
                        "return cannot be completed.");
            }else{
                System.out.println(tempAlbum.getTitle() +"::"+ tempAlbum.getArtist() + " >> " +
                        "returning and set to available.");
            }
        }else if(c.compareTo("P") == 0){
            collection.print();
        }else if(c.compareTo("PD") == 0){
            collection.printByReleaseDate();
        }else if(c.compareTo("PG") == 0){
            collection.printByGenre();
        }else if(c.compareTo("Q") == 0){
            System.out.println("Collection Manager terminated.");
            System.exit(0);
        }else{
            System.out.println("Invalid Command!");
        }
    }
}