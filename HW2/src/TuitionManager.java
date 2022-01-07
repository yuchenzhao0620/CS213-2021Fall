import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * TuitionManager class
 * Manage the command by using switch
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class TuitionManager {

    /**
     * Creat the roster.
     * Passing the roster and the input to the command method.
     */
    public void run(){
        Roster roster = new Roster();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(input.nextLine());
            if(st.hasMoreElements()) {
                command(roster, st);
            }else{
                System.out.println("\r");
            }
        }
    }

    /**
     * Read the command lines from the console.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from run()
     */
    private void command(Roster roster, StringTokenizer st){
        String c = st.nextToken(",");
        switch (c) {
            case "AR":
                ARCommand(roster, st);
                break;
            case "AN":
                ANCommand(roster, st);
                break;
            case "AT":
                ATCommand(roster, st);
                break;
            case "AI":
                AICommand(roster, st);
                break;
            case "R":
                RCommand(roster, st);
                break;
            case "C":
                CCommand(roster);
                System.out.println("Calculation completed.");
                break;
            case "T":
                TCommand(roster, st);
                break;
            case "S":
                SCommand(roster, st);
                break;
            case "F":
                FCommand(roster, st);
                break;
            case "P":
                roster.print();
                break;
            case "PN":
                roster.orderByName();
                break;
            case "PT":
                roster.orderByDate();
                break;
            case "Q":
                System.out.println("Tuition Manager terminated.");
                System.exit(0);
                break;
            default:
                System.out.println("Command '"+ c + "' not supported!");
                break;
        }
    }

    /**
     * Determine the condition and execute the Add resident student command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void ARCommand(Roster roster, StringTokenizer st){
        try {
            Student tempStudent = new Student(CreateProfile(st));
            try {
                Resident rs = new Resident(tempStudent.getProfile());
                String credit = st.nextToken(",");
                if (isValidCredit(credit)) {
                    int h = Integer.parseInt(credit);
                    rs.setCredit(h);
                    if (!roster.add(rs)) {
                        System.out.println("Student is already in the roster.");
                    } else {
                        roster.add(rs);
                        System.out.println("Student added.");
                    }
                }
            }catch (NoSuchElementException ex){
                System.out.println("Credit hours missing.");
            }
        }catch (NoSuchElementException ex){
            System.out.println("Missing data in command line.");
        }catch (Exception e) {
            System.out.println("'" + e.getMessage() + "' is not a valid major.");
        }
    }

    /**
     * Determine the condition and execute the Add nonresident student command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void ANCommand(Roster roster, StringTokenizer st){
        try {
            Student tempStudent = new Student(CreateProfile(st));
            try {
                NonResident ns = new NonResident(tempStudent.getProfile());
                String credit = st.nextToken(",");
                if (isValidCredit(credit)) {
                    int h = Integer.parseInt(credit);
                    ns.setCredit(h);
                    if (!roster.add(ns)) {
                        System.out.println("Student is already in the roster.");
                    } else {
                        roster.add(ns);
                        System.out.println("Student added.");
                    }
                }
            }catch (NoSuchElementException ex){
                System.out.println("Credit hours missing.");
            }
        }catch (NoSuchElementException ex){
            System.out.println("Missing data in command line.");
        }catch (Exception e) {
            System.out.println("'" + e.getMessage() + "' is not a valid major.");
        }
    }

    /**
     * Determine the condition and execute the Add tristate student command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void ATCommand(Roster roster, StringTokenizer st){
        try {
            Student tempStudent = new Student(CreateProfile(st));
            try {
                TriState ts = new TriState(tempStudent.getProfile());
                String credit = st.nextToken(",");
                try {
                    String state = st.nextToken(",");
                    setStates(ts, state);
                    if (isValidCredit(credit)) {
                        int h = Integer.parseInt(credit);
                        ts.setCredit(h);
                        if (!roster.add(ts)) {
                            System.out.println("Student is already in the roster.");
                        } else {
                            roster.add(ts);
                            System.out.println("Student added.");
                        }
                    }
                }catch (NoSuchElementException ex){
                    System.out.println("Missing data in command line.");
                }
            }catch (NoSuchElementException ex){
                System.out.println("Credit hours missing.");
            }
        }catch (NoSuchElementException ex){
            System.out.println("Missing data in command line.");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determine the condition and execute the Add international student command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void AICommand(Roster roster, StringTokenizer st){
        try {
            Student tempStudent = new Student(CreateProfile(st));
            try {
                International is = new International(tempStudent.getProfile());
                String credit = st.nextToken(",");
                is.setStudyAboard(Boolean.parseBoolean(st.nextToken(",")));
                if (isValidCredit(credit)) {
                    int h = Integer.parseInt(credit);
                    if(h < 12){
                        System.out.println("International students must enroll at least 12 credits.");
                    }else {
                        is.setCredit(h);
                        if (!roster.add(is)) {
                            System.out.println("Student is already in the roster.");
                        } else {
                            roster.add(is);
                            System.out.println("Student added.");
                        }
                    }
                }
            }catch (NoSuchElementException ex){
                System.out.println("Credit hours missing.");
            }
        }catch (NoSuchElementException ex){
            System.out.println("Missing data in command line.");
        }catch (Exception e) {
            System.out.println("'" + e.getMessage() + "' is not a valid major.");
        }
    }

    /**
     * Determine the condition and execute the Remove student command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void RCommand(Roster roster, StringTokenizer st){
        try {
            Student tempStudent = new Student(CreateProfile(st));
            if (!roster.remove(tempStudent)) {
                System.out.println("Student is not in the roster.");
            } else {
                System.out.println("Student removed from the roster.");
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    /**
     * Determine the condition and execute to Calculate tuition command.
     *
     * @param roster the roster created by run()
     */
    private void CCommand(Roster roster){
        for(int i = 0; i < roster.getSize(); i++){
            roster.getRoster(i).tuitionDue();
        }
    }

    /**
     * Determine the condition and execute the Pay tuition command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void TCommand(Roster roster, StringTokenizer st){
        try {
            Student tempStudent = new Student(CreateProfile(st));
            if (roster.find(tempStudent) != -1) {
                String payment = st.nextToken(",");
                Student temp = roster.getRoster(roster.find(tempStudent));
                if(isValidPayment(payment, temp)){
                    Date date = new Date(st.nextToken(","));
                    if(date.isValid()){
                        double p = Double.parseDouble(payment);
                        roster.getRoster(roster.find(tempStudent)).setPayment(p);
                        roster.getRoster(roster.find(tempStudent)).setPaymentDate(date);
                        System.out.println("Payment applied.");
                    }else{
                        System.out.println("Payment date invalid.");
                    }
                }
            }else{
                System.out.println("Student is not in the roster.");
            }
        }catch (Exception ex){
            System.out.println("Payment amount missing.");
        }
    }

    /**
     * Determine the condition and execute the Set study aboard command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void SCommand(Roster roster, StringTokenizer st){
        try{
            International tempStudent = new International(CreateProfile(st));
            International temp = (International)roster.getRoster(roster.find(tempStudent));
            temp.setStudyAboard(Boolean.parseBoolean(st.nextToken(",")));
            temp.tuitionDue();
            temp.setPayment(-temp.getPayment());
            temp.setPaymentDate(null);
            temp.setCredit(12);
            System.out.println("Tuition updated.");
        }catch(Exception ex){
            System.out.println("Couldn't find the international student.");
        }
    }

    /**
     * Determine the condition and execute the Set financial aid command.
     *
     * @param roster the roster created by run()
     * @param st StringTokenizer from command()
     */
    private void FCommand(Roster roster, StringTokenizer st){
        try{
            Resident tempStudent = new Resident(CreateProfile(st));
            double finacAid = Double.parseDouble(st.nextToken(","));
            Resident temp = (Resident)roster.getRoster(roster.find(tempStudent));
            if(temp.getCredit() >= 12) {
                if (finacAid < 0 || finacAid > 10000) {
                    System.out.println("Invalid amount.");
                } else if (temp.getFinancialAid() > 0) {
                    System.out.println("Awarded once already.");
                } else {
                    temp.setFinancialAid(finacAid);
                    System.out.println("Tuition updated.");
                }
            }else{
                System.out.println("Parttime student doesn't qualify for the award.");
            }
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Student not in the roster.");
        }catch(NoSuchElementException ex){
            System.out.println("Missing the amount.");
        }catch(ClassCastException ex){
            System.out.println("Not a resident student.");
        }catch(Exception ex){
            System.out.println(ex);
        }

    }

    /**
     * Create a profile for the student.
     *
     * @param st StringTokenizer from the command methods
     */
    private Profile CreateProfile(StringTokenizer st) throws Exception {
        Profile p = new Profile();
        p.setName(st.nextToken(","));
        String major = st.nextToken(",");
        if(!Major.contains(major.toUpperCase())){
            throw new Exception(major);
        }else {
            switch (major.toUpperCase()) {
                case "CS":
                    p.setMajor(Major.CS);
                    break;
                case "IT":
                    p.setMajor(Major.IT);
                    break;
                case "BA":
                    p.setMajor(Major.BA);
                    break;
                case "EE":
                    p.setMajor(Major.EE);
                    break;
                case "ME":
                    p.setMajor(Major.ME);
                    break;
                default:
                    System.out.println("'" + major + "'" + " is not a valid major.");
                    break;
            }
        }
        return p;
    }

    /**
     * Set a string state value to an enum State value.
     *
     * @param ts Tristate object from ATCommand()
     * @param state a state of this tristate student by string type
     * @throws Exception an exception when state not in enum States class
     */
    private void setStates(TriState ts, String state) throws Exception {
        if (!States.contains(state.toUpperCase())) {
            throw new Exception("Not part of the tri-state area.");
        } else {
            switch (state.toUpperCase()) {
                case "NY":
                    ts.setState(States.NY);
                    break;
                case "CT":
                    ts.setState(States.CT);
                    break;
            }
        }
    }

    /**
     * Check a credit amount if is valid.
     *
     * @param credit the credit need to check
     * @return if is valid or not
     */
    private boolean isValidCredit(String credit){
        try {
            int check = Integer.parseInt(credit);
            if(check > 24){
                System.out.println("Credit hours exceed the maximum 24.");
            }else if(check >= 0 && check < 3){
                System.out.println("Minimum credit hours is 3.");
            }else if(check < 0){
                System.out.println("Credit hours cannot be negative.");
            }else{
                return true;
            }
            return false;
        }catch (Exception o){
            System.out.println("Invalid credit hours.");
        }
        return false;
    }

    /**
     * Check a payment amount if is valid.
     *
     * @param payment the payment need to check
     * @param student the student who paid the payment
     * @return if is valid or not
     */
    private boolean isValidPayment(String payment, Student student){
        try{
            double p = Double.parseDouble(payment);
            if(p <= 0){
                System.out.println("Invalid amount.");
            }else if(p > student.getTuition()){
                System.out.println("Amount is greater than amount due.");
            }else{
                return true;
            }
        }catch(Exception o){
            System.out.println("Invalid payment.");
        }
        return false;
    }

}
