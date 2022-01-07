/**
 * TriState class
 * A subclass of NonResident class
 * Including all tristate student
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
class TriState extends NonResident {
    private States state; // enum class; NY, CT

    /**
     * Set the profile of this tristate student.
     *
     * @param profile a profile to set
     */
    public TriState(Profile profile) {
        super(profile);
    }

    /**
     * Set the state of this tristate student.
     *
     * @param state a state to set
     */
    public void setState(States state){
        this.state = state;
    }

    /**
     * Calculate the tuition of all tristate students.
     */
    @Override
    public void tuitionDue(){
        super.tuitionDue();
        if(this.getCredit()>12) {
            if (this.state.equals(States.NY)) {
                this.setTuition(this.getTuition() - 4000);
            }
            if (this.state.equals(States.CT)) {
                this.setTuition(this.getTuition() - 5000);
            }
        }
    }

    /**
     * Print the information of this tristate student.
     *
     * @return the information of this tristate student
     */
    @Override
    public String toString(){
        return super.toString() + "(tri-state):" + this.state;
    }
}
