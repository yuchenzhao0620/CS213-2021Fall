package com.example.hw3;

/**
 * Resident class
 * A subclass for Student class
 * Including all resident student
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
class Resident extends Student {
    private double financialAid;

    /**
     * Set the profile of this resident student.
     *
     * @param profile a profile to set
     */
    public Resident(Profile profile) {
       super(profile);
    }

    /**
     * Set financial aid for a resident student.
     *
     * @param financialAid the financial aid amount
     */
    public void setFinancialAid(double financialAid){
        this.financialAid = financialAid;
    }

    /**
     * Get financial aid for a resident student.
     *
     * @return the financial aid amount
     */
    public double getFinancialAid(){
        return this.financialAid;
    }

    /**
     * Calculate the tuition for all resident students.
     */
    @Override
    public void tuitionDue(){
        if(this.getCredit() > 16){
            this.setTuition(12536 + 3268 + (this.getCredit() - 16) * 404 - this.getPayment());
        }else if(this.getCredit() < 12){
            this.setTuition(404 * this.getCredit() + 3268 * 0.8 - this.getPayment());
        }else{
            this.setTuition(12536 + 3268 - this.getPayment());
        }
        this.setTuition(this.getTuition() - financialAid);
    }

    /**
     * Print the information of this resident student.
     *
     * @return the information of this resident student
     */
    @Override
    public String toString() {
        if (this.financialAid != 0) {
            return super.toString() + ":resident" + ":financial aid " + this.financialAid;
        } else {
            return super.toString() + ":resident";
        }
    }
}
