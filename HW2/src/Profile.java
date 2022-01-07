/**
 * This class defines the name and major for the student
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Profile {
    private String name; // name of this profile
    private Major major; //5 majors and 2-charter each: CS, IT, BA, EE, ME

    /**
     * Set the name of this profile.
     *
     * @param name the name to set
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * Get the name of  this profile.
     *
     * @return the name of this profile
     */

    public String getName(){
        return this.name;
    }

    /**
     * Set the major of this profile.
     *
     * @param major the major to set
     */

    public void setMajor(Major major){
        this.major = major;
    }

    /**
     * Determines whether o profile is the same as this one.
     *
     * @param obj a profile to compare with this one
     * @return if these two profiles are identical or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)  //Check that the parameter is of a reference to this object
            return true;
        if (!(obj instanceof Profile))//Check that the parameter is of the correct type
            return false;
        Profile profile = (Profile) obj;
        return name.equals(profile.name) && major.equals(profile.major);
    }

    /**
     * Print the name and major of this profile.
     *
     * @return the name and major of this profile
     */
    @Override
    public String toString() {
        return this.name + ":" + this.major;
    }

}