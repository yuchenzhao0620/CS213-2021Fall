/**
 * International class
 * A subclass of the NonResident class
 * Including all international student
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
class International extends NonResident {
    private boolean isStudyAboard;

    /**
     * Set the profile of this international student.
     *
     * @param profile a profile to set
     */
    public International(Profile profile) {
        super(profile);
    }

    /**
     * Set if an internation student study aboard.
     *
     * @param isStudyAboard if is study aboard or not
     */
    public void setStudyAboard(boolean isStudyAboard){
        this.isStudyAboard = isStudyAboard;
    }

    /**
     * Calculate the tuition for all international student.
     */
    @Override
    public void tuitionDue(){
        if (isStudyAboard){
            this.setTuition(3268 + 2650 - this.getPayment());
        }else{
            super.tuitionDue();
            this.setTuition(2650 + this.getTuition());
        }
    }

    /**
     * Print the information of this international student.
     *
     * @return the information of this international student
     */
    @Override
    public String toString(){
        if (isStudyAboard){
            return super.toString() + ":international:study abroad";
        }else{
            return super.toString() + ":international";
        }
    }
}
