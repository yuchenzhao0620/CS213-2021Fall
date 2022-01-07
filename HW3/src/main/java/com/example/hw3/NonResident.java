package com.example.hw3;

/**
 * NonResident class
 * A subclass for Student class
 * Including all nonresident student
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
class NonResident extends Student {
    /**
     * Set a profile of this nonresident student.
     *
     * @param profile a profile to set
     */
    public NonResident(Profile profile) {
        super(profile);
    }

    /**
     * Calculate the tuition for all nonresident student.
     */
    @Override
    public void tuitionDue(){
        if(this.getCredit() > 16){
            this.setTuition(29737 + 3268 + (this.getCredit() - 16) * 966 - this.getPayment());
        }else if(this.getCredit() < 12){
            this.setTuition(966 * this.getCredit() + 3268 * 0.8 - this.getPayment());
        }else{
            this.setTuition(29737 + 3268 - this.getPayment());
        }
    }

    /**
     * Print the information for this nonresident student.
     *
     * @return the information for this nonresident student
     */
    @Override
    public String toString() {
        return super.toString() + ":non-resident";
    }

}
