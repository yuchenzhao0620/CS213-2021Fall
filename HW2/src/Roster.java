/**
 * Roster class
 * To add a student, remove a student
 * Save the students in Roster Student[] roster
 * Print out the students in the roster, or sort by name and payment date
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Roster {
    private Student[] roster;
    /**
     * Number of students currently in the roster.
     */
    private int size; //keep track of the number of students in the roster

    /**
     * Constructor. Create an array which size is 4.
     */
    public Roster() {
        this.roster = new Student[4];
    }

    /**
     * Search a specific student in the roster.
     *
     * @param student the student to find in the roster
     * @return the index if found the student, -1 standard for not found
     */
    public int find(Student student){
        for(int i = 0; i < size; i++){
            if(this.roster[i].getProfile().equals(student.getProfile())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * When the array is full, create a 4 size larger array.
     */
    private void grow(){
        Student[] newRoster = new Student[this.roster.length + 4];
        for(int i = 0; i < size; i++){
            newRoster[i] = roster[i];
        }
        this.roster = newRoster;
    }

    /**
     * Get the size of the roster.
     *
     * @return the size of the roster
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Get the student in the roster.
     *
     * @param i is the index of student
     * @return the student in index i
     */
    public Student getRoster(int i){
        return this.roster[i];
    }

    /**
     * Add the student to the roster.
     *
     * @param student a new student to add
     * @return if successfully added or not
     */
    public boolean add(Student student){
        if(this.size == this.roster.length){ //check the length if it needs more space
            grow();
        }
        if(this.find(student) != -1){
            return false;
        }else {
            roster[this.size] = student;
            this.size++;
            return true;
        }
    }

    /**
     * Remove a student in the roster.
     *
     * @param student a student to remove
     * @return if successfully removed or not
     */
    public boolean remove(Student student){
        if(this.find(student) == -1){
            return false; //"is not in the roster"
        }
        for( int i = this.find(student); i < size; i++){
            this.roster[i] = this.roster[i+1];
        }
        this.size--;
        return true;
    }



    /**
     * Display the list without specifying the order.
     */
    public void print() {
        if(!(size == 0)){
            System.out.println("* list of students in the roster **");
            for(int i = 0; i < size; i++){
                System.out.println(this.roster[i].toString());
            }
            System.out.println("* end of roster **");
        }else{
            System.out.println("Student roster is empty!");
        }
    }

    /**
     * Display the list with order by name.
     */
    public void orderByName(){
        if(!(size == 0)){
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    if(this.roster[i].getProfile().getName().compareTo(this.roster[j].getProfile().getName()) < 0){
                        Student[] temp = new Student[1];
                        temp[0] = this.roster[j];
                        this.roster[j] = this.roster[i];
                        this.roster[i] = temp[0];
                    }
                }
            }
            System.out.println("* list of students ordered by name **");
            for(int i = 0; i < size; i++){
                System.out.println(this.roster[i].toString());
            }
            System.out.println("* end of roster **");
        }else{
            System.out.println("Student roster is empty!");
        }
    }

    /**
     * Display the list which has paid with order by date.
     */
    public void orderByDate(){
        if(!(size == 0)){
            int size = this.size;
            int t = 0;
            Student[] sortByDate = new Student[size];
            for(int i = 0; i < size; i++){
                if(this.roster[i].getPayment() != 0) {
                    sortByDate[t] = this.roster[i];
                    t++;
                }
            }
            for(int i = 0; i < t; i++){
                for(int j = 0; j < t; j++){
                    if(sortByDate[i].getPaymentDate().compareTo(sortByDate[j].getPaymentDate()) == 1){
                        Student[] temp = new Student[1];
                        temp[0] = sortByDate[j];
                        sortByDate[j] = sortByDate[i];
                        sortByDate[i] = temp[0];
                    }
                }
            }
            System.out.println("* list of students made payments ordered by payment date **");
            for(int i = 0; i < t; i++){
                System.out.println(sortByDate[i].toString());
            }
            System.out.println("* end of roster **");
        }else{
            System.out.println("Student roster is empty!");
        }
    }

}