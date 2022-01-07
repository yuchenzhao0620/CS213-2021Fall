import java.util.Calendar;
/**
 * Date class
 * An object class including the year, month and day by integer
 * Check the date if is valid
 * Check the year if is leap year
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Take "mm/dd/yyyy" and create a Date object.
     *
     * @param date String type date
     */
    public Date(String date){
        String[] dateValue = date.split("/");
        this.month = Integer.parseInt(dateValue[0]);
        this.day = Integer.parseInt(dateValue[1]);
        this.year = Integer.parseInt(dateValue[2]);
    }

    /**
     * Create an object with today's date.
     */
    private Date(){
        Calendar today = Calendar.getInstance();
        this.month = today.get(Calendar.MONTH) + 1; // +1 for the current month
        this.day = today.get(Calendar.DATE);
        this.year = today.get(Calendar.YEAR);
    }

    /**
     * Get year from this date.
     *
     * @return year of this date
     */
    public int getYear(){
        return year;
    }

    /**
     * Get month from this date.
     *
     * @return month of this date
     */
    public int getMonth(){
        return month;
    }
    /**
     * Get day from this date.
     *
     * @return day of this date
     */
    public int getDay(){
        return day;
    }

    /**
     * Determines whether a year is leap year.
     *
     * @param year a year to determine
     * @return if this year is leap year or not
     */
    private boolean isLeap(int year){
        boolean isLeap;
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    isLeap = true;
                }else{
                    isLeap = false;
                }
            }else{
                isLeap = true;
            }
        }else{
            isLeap = false;
        }
        return isLeap;
    }

    /**
     * Determines whether the date format is valid.
     *
     * @return if the date is valid or not
     */
    public boolean isValid(){
        Date today = new Date();
        int year = getYear();
        int month = getMonth();
        int day = getDay();

        if(year > today.year || year < 2021)
            return false;
        if(month < 1 || month > 12 || (year == today.year && month > today.month))
            return false;
        if(day < 1 || day > 31 || (year == today.year && month == today.month && day > today.day))
            return false;
        if(month == 2){ // check February with leap year
            if(isLeap(year))
                return (day <= 29);
            else
                return (day <= 28);
        }

        //check Apr, Jun, Sep, Nov for 30 days
        if(month == 4 || month == 6 || month == 9 || month == 11)
            return (day <= 30);

        return true;
    }

    /**
     * Compare two dates. Determines whether they are identical.
     *
     * @param date a date to compare to this date
     * @return result of comparison. return 1 if this date is later, otherwise -1. 0 stands for equal.
     */
    @Override
    public int compareTo(Date date){
        int year = this.getYear();
        int month = this.getMonth();
        int day = this.getDay();

        if(date.year > year){
            return 1;
        }else if(date.year == year){
            if(date.month > month){
                return 1;
            }else if(date.month == month){
                if(date.day > day){
                    return 1;
                }else if(date.day == day){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }
}