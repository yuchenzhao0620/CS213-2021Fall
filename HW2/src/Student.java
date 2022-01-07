import java.text.DecimalFormat;

/**
 * Student class
 * This class defines every student.
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Student {
    private Profile profile; // profile of this student
    private int credit; // credit of this student
    private Date paymentDate; // payment date of this student
    private double tuition; // tuition of this student
    private double payment; // total payment of this student

    public void tuitionDue(){
    }

    /**
     * Set the profile for a student include name and major.
     *
     * @param profile the profile to set
     */
    public Student(Profile profile){
        this.profile = profile;
    }

    /**
     * Get the profile of this student.
     *
     * @return the profile of this student
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Set credit of this student.
     *
     * @param credit the credit to set
     */
    public void setCredit(int credit){
        this.credit = credit;
    }

    /**
     * Get the credit of this student.
     *
     * @return the credit of this student
     */
    public int getCredit(){
        return  this.credit;
   }

    /**
     * Set payment date of this student.
     *
     * @param date the date to set
     */
    public void setPaymentDate(Date date) {
        this.paymentDate = date;
    }

    /**
     * Get the payment date of this student.
     *
     * @return the date of this student
     */
    public Date getPaymentDate(){
        return this.paymentDate;
    }

    /**
     * Set the tuition of this student.
     *
     * @param tuition the tuition to set
     */
    public void setTuition(double tuition){
        this.tuition = tuition;
    }

    /**
     * Set the total payment of this student.
     *
     * @param payment the payment to set
     */
    public void setPayment(Double payment){
        this.payment = this.payment + payment;
        this.tuition = this.tuition - payment;
    }

    /**
     * Get the total payment of this student.
     *
     * @return the total payment of this student
     */
    public double getPayment(){
        return this.payment;
    }

    /**
     * Get the tuition of this student.
     *
     * @return the tuition of this student
     */
    public double getTuition(){
        return this.tuition;
    }

    /**
     * Print the information of this student.
     *
     * @return the information of this student
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        try{
            String year = String.valueOf(this.paymentDate.getYear());
            String month = String.valueOf(this.paymentDate.getMonth());
            String day = String.valueOf(this.paymentDate.getDay());
            return this.profile.toString() + ":" + this.getCredit() + " credit hours:tuition due:" +
                    df.format(this.getTuition()) + ":total payment:" + df.format(this.getPayment()) +
                    ":payment date: " + month + "/" + day + "/" + year;
        }catch(Exception o){
            String year = "--";
            String month = "--";
            String day = "--";
            return this.profile.toString() + ":" + this.getCredit() + " credit hours:tuition due:" +
                    df.format(this.getTuition()) + ":total payment:" + df.format(this.getPayment()) +
                    ":payment date: " + month + "/" + day + "/" + year;

        }
    }
}