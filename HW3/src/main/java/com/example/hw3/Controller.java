package com.example.hw3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Controller class
 * Handel the event and command.
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class Controller {

    @FXML
    private CheckBox checkButtonStudyaboard;

    @FXML
    private ToggleGroup city;

    @FXML
    private RadioButton cityCT;

    @FXML
    private RadioButton cityNY;

    @FXML
    private TextField creditHours;

    @FXML
    private ToggleGroup major;

    @FXML
    private ToggleGroup minPay;

    @FXML
    private TextField nameInPayment;

    @FXML
    private TextField nameInProfile;

    @FXML
    private ToggleGroup nonresident;

    @FXML
    private TextArea ouputBoard;

    @FXML
    private TextField outputTuition;

    @FXML
    private TextField paymentAmount;

    @FXML
    private DatePicker date;

    @FXML
    private TextField fAA;

    @FXML
    private RadioButton radioButtonIntl;

    @FXML
    private RadioButton radioButtonNrs;

    @FXML
    private RadioButton radioButtonRs;

    @FXML
    private RadioButton radioButtonTri;

    @FXML
    private ToggleGroup status;

    private Roster roster;

    /**
     * Connect the roster class.
     */
    public Controller(){
        this.roster = new Roster();
    }

    /**
     * This method will control the radio button
     */
    @FXML
    void select(ActionEvent event) {
        if(status.getSelectedToggle() == radioButtonNrs){
            radioButtonTri.setDisable(false);
            radioButtonIntl.setDisable(false);
        }else{
            radioButtonTri.setDisable(true);
            radioButtonTri.setSelected(false);
            radioButtonIntl.setDisable(true);
            radioButtonIntl.setSelected(false);
            cityNY.setDisable(true);
            cityNY.setSelected(false);
            cityCT.setDisable(true);
            cityCT.setSelected(false);
            checkButtonStudyaboard.setDisable(true);
            checkButtonStudyaboard.setSelected(false);
        }
        if(nonresident.getSelectedToggle() == radioButtonTri){
            cityNY.setDisable(false);
            cityCT.setDisable(false);
        }else{
            cityNY.setDisable(true);
            cityNY.setSelected(false);
            cityCT.setDisable(true);
            cityCT.setSelected(false);
            checkButtonStudyaboard.setSelected(false);
        }
        if(nonresident.getSelectedToggle() == radioButtonIntl){
            checkButtonStudyaboard.setDisable(false);
        }else{
            checkButtonStudyaboard.setDisable(true);
            checkButtonStudyaboard.setSelected(false);
        }
    }

    /**
     * Check a credit amount if is valid.
     *
     * @param credit the credit need to check
     * @return if is valid or not
     */
    private boolean isValidCredit(String credit) {
        try {
            int check = Integer.parseInt(credit);
            if(check > 24){
                ouputBoard.appendText("Credit hours exceed the maximum 24.\n");
            }else if(check >= 0 && check < 3){
                ouputBoard.appendText("Minimum credit hours is 3.\n");
            }else if(check < 0){
                ouputBoard.appendText("Credit hours cannot be negative.\n");
            }else{
                return true;
            }
            return false;
        }catch (Exception o){
            ouputBoard.appendText("Invalid credit hours.\n");
        }
        return false;
    }

    /**
     * Determine the condition and execute the Add resident or non-resident student.
     *
     * @param isResident a boolean parameter if is a resident or not.
     */
    void addResident(boolean isResident){
        Student student;
        try {
            if(isResident){
                student = new Resident(CreateProfile(this.nameInProfile, this.major.getSelectedToggle()));
            }else{
                student = new NonResident(CreateProfile(this.nameInProfile, this.major.getSelectedToggle()));
            }
            if(!(status.getSelectedToggle() == radioButtonRs | status.getSelectedToggle() ==radioButtonNrs)){
                throw new Exception("You have to select a status.\n");
            }
            String credit = this.creditHours.getText();
            if(Objects.equals(credit, "")){
                throw new Exception("Credit hours missing.\n");
            }
            if (isValidCredit(credit)) {
                int h = Integer.parseInt(credit);
                student.setCredit(h);
                if (!roster.add(student)) {
                    ouputBoard.appendText("Student is already in the roster.\n");
                } else {
                    roster.add(student);
                    ouputBoard.appendText("Student added.\n");
//                    clearGUI();
                }
            }
        }catch (Exception e) {
            ouputBoard.appendText(e.getMessage());
        }
    }

    /**
     * Create a profile for the student.
     *
     * @param name a name string from a text field.
     * @param major a radio button shows that major
     * @return a profile for a student
     * @throws Exception throw an exception if it misses data.
     */
    private Profile CreateProfile(TextField name, Toggle major) throws Exception{
        Profile p = new Profile();
        p.setName(name.getText());
        if(Objects.equals(name.getText(), "")){
            throw new Exception("You have to input a name.\n");
        }
        try{
            String tempMajor = ((RadioButton) major).getText();
            switch (tempMajor) {
                case "CS" -> p.setMajor(Major.CS);
                case "IT" -> p.setMajor(Major.IT);
                case "BA" -> p.setMajor(Major.BA);
                case "EE" -> p.setMajor(Major.EE);
                case "ME" -> p.setMajor(Major.ME);
            }
        }catch (Exception e){
            throw new Exception("You have to select a major.\n");
        }
        return p;
    }

    /**
     * Determine the condition and execute the Add tristate student.
     */
    void addTriState(){
        try {
            TriState ts = new TriState(CreateProfile(this.nameInProfile, this.major.getSelectedToggle()));

            if(city.getSelectedToggle() ==  cityNY){
                setStates(ts, "NY");
            }else if(city.getSelectedToggle() ==  cityCT){
                setStates(ts, "CT");
            }else{
                throw new Exception("You have to select a specific State.\n");
            }

            String credit = this.creditHours.getText();
            if(Objects.equals(credit, "")){
                throw new Exception("Credit hours missing.\n");
            }

            if (isValidCredit(credit)) {
                int h = Integer.parseInt(credit);
                ts.setCredit(h);
                if (!roster.add(ts)) {
                    ouputBoard.appendText("Student is already in the roster.\n");
                } else {
                    roster.add(ts);
                    ouputBoard.appendText("Student added.\n");
//                    clearGUI();
                }
            }
        }catch (Exception e) {
            ouputBoard.appendText(e.getMessage());
        }
    }

    /**
     * Determine the condition and execute the Add international student.
     */
    void addIntl(){
        try {
            International is = new International(CreateProfile(this.nameInProfile, this.major.getSelectedToggle()));
            String credit = this.creditHours.getText();
            is.setStudyAboard(checkButtonStudyaboard.isSelected());
            if(Objects.equals(credit, "")){
                throw new Exception("Credit hours missing.\n");
            }
            if (isValidCredit(credit)) {
                int h = Integer.parseInt(credit);
                is.setCredit(h);
                if (!roster.add(is)) {
                    ouputBoard.appendText("Student is already in the roster.\n");
                } else {
                    roster.add(is);
                    ouputBoard.appendText("Student added.\n");
//                    clearGUI();
                }
            }
        }catch (Exception e) {
            ouputBoard.appendText(e.getMessage());
        }
    }

    /**
     * Determine the condition and execute which type of student will add.
     */
    @FXML
    void addStudent(ActionEvent event){
        if(status.getSelectedToggle() == radioButtonRs){
            addResident(true);
        }else if(status.getSelectedToggle() == radioButtonNrs){
            if(nonresident.getSelectedToggle() == radioButtonTri){
                addTriState();
            }else if(nonresident.getSelectedToggle() == radioButtonIntl){
                addIntl();
            }else{
                addResident(false);
            }
        }else{
            addResident(true);
        }
    }

    /**
     * An action event for remove student
     * It happens when user click the Remove Student button
     */
    @FXML
    void removeStudent(ActionEvent event){
        try {
            Student tempStudent = new Student(CreateProfile(this.nameInProfile, this.major.getSelectedToggle()));
            if (!roster.remove(tempStudent)) {
                ouputBoard.appendText("Student is not in the roster.\n");
            } else {
                ouputBoard.appendText("Student removed from the roster.\n");

            }
        }catch (Exception e){
            ouputBoard.appendText(e.getMessage());
        }
    }

    /**
     * An action event for calculate the tuition.
     * It happens when user click the Tuition Due button
     */
    @FXML
    void calculateTuition(ActionEvent event) {
        for(int i = 0; i < roster.getSize(); i++){
            roster.getRoster(i).tuitionDue();
        }
        try {
            Student tempStudent = new Student(CreateProfile(this.nameInProfile, this.major.getSelectedToggle()));
            if(roster.find(tempStudent) == -1){
                ouputBoard.appendText("The student is not in the roster.\n");
            }else{
                roster.getRoster(roster.find(tempStudent)).tuitionDue();
                outputTuition.setText(String.valueOf(roster.getRoster(roster.find(tempStudent)).getTuition()));
            }
        }catch (Exception e){
            ouputBoard.appendText(e.getMessage());
        }
    }

    /**
     * An action event for set payment.
     * It happens when user click the Pay button.
     */
    public void setPayment(ActionEvent actionEvent) {
        try {
            Student tempStudent = new Student(CreateProfile(this.nameInPayment, this.minPay.getSelectedToggle()));
            if (roster.find(tempStudent) != -1) {
                String payment = paymentAmount.getText();
                Student temp = roster.getRoster(roster.find(tempStudent));
                if(isValidPayment(payment, temp)){
                    Date date = new Date(this.date.getValue().toString().replace("-", "/"));
                    if(date.isValid()){
                        double p = Double.parseDouble(payment);
                        roster.getRoster(roster.find(tempStudent)).setPayment(p);
                        roster.getRoster(roster.find(tempStudent)).setPaymentDate(date);
                        ouputBoard.appendText("Payment applied.\n");
                    }else{
                        ouputBoard.appendText("Payment date invalid.\n");
                    }
                }
            }else{
                ouputBoard.appendText("Student is not in the roster.\n");
            }
        }catch (Exception e){
            ouputBoard.appendText("Payment amount missing.\n");
        }
    }

    /**
     * An action event for set student financial aid.
     * It happens when user click the Set button.
     */
    public void setFinancial(ActionEvent actionEvent) {
        try{
            Resident tempStudent = new Resident(CreateProfile(this.nameInPayment, this.minPay.getSelectedToggle()));
            double finacAid = Double.parseDouble(fAA.getText());
            Resident temp = (Resident)roster.getRoster(roster.find(tempStudent));
            if(temp.getCredit() >= 12) {
                if (finacAid < 0 || finacAid > 10000) {
                    ouputBoard.appendText("Invalid amount.\n");
                } else if (temp.getFinancialAid() > 0) {
                    ouputBoard.appendText("Awarded once already.\n");
                } else {
                    temp.setFinancialAid(finacAid);
                    ouputBoard.appendText("Tuition updated.\n");
                }
            }else{
                ouputBoard.appendText("Part-time student doesn't qualify for the award.\n");
            }
        }catch (ArrayIndexOutOfBoundsException ex){
            ouputBoard.appendText("Student not in the roster.\n");
        }catch(NoSuchElementException ex){
            ouputBoard.appendText("Missing the amount.\n");
        }catch(ClassCastException ex){
            ouputBoard.appendText("Not a resident student.\n");
        }catch(Exception ex){
            ouputBoard.appendText(ex.getMessage());
        }
    }

    /**
     * Determine the condition and execute set states for a tristate student.
     *
     * @param ts A tristate type student
     * @param state student state
     * @throws Exception
     */
    private void setStates(TriState ts, String state) throws Exception {
        if (!States.contains(state.toUpperCase())) {
            throw new Exception("Not part of the tri-state area.");
        } else {
            switch (state.toUpperCase()) {
                case "NY" -> ts.setState(States.NY);
                case "CT" -> ts.setState(States.CT);
            }
        }
    }

    /**
     * Clear the GUI when an event happened.
     */
    @FXML
    void clearGUI(ActionEvent actionEvent){
        nameInProfile.clear();
        if(major.getSelectedToggle() != null){
            major.getSelectedToggle().setSelected(false);
        }
        if(status.getSelectedToggle() != null){
            status.getSelectedToggle().setSelected(false);
        }
        if(nonresident.getSelectedToggle() != null){
            nonresident.getSelectedToggle().setSelected(false);
        }
        if(city.getSelectedToggle() != null){
            city.getSelectedToggle().setSelected(false);
        }
        checkButtonStudyaboard.setSelected(false);
        creditHours.clear();
        nameInPayment.clear();
        paymentAmount.clear();
        date.getEditor().clear();
        fAA.clear();
    }

    /**
     * Determine the condition and execute if it is a valid payment.
     *
     * @param payment Student payment
     * @param student a student in the roster
     * @return if it is valid.
     */
    private boolean isValidPayment(String payment, Student student){
        try{
            double p = Double.parseDouble(payment);
            if(p <= 0){
                ouputBoard.appendText("Invalid amount.\n");
            }else if(p > student.getTuition()){
                ouputBoard.appendText("Amount is greater than amount due.\n");
            }else{
                return true;
            }
        }catch(Exception o){
            ouputBoard.appendText("Invalid payment.\n");
        }
        return false;
    }

    /**
     * Determine the condition and execute calculate all students' tuition.
     */
    @FXML
    void calculateAll(ActionEvent event) {
        if(!(roster.getSize() == 0)) {
            for (int i = 0; i < roster.getSize(); i++) {
                roster.getRoster(i).tuitionDue();
            }
            ouputBoard.appendText("Calculation completed.\n");
        }else{
            ouputBoard.appendText("Student roster is empty!\n");
        }
    }

    /**
     * Display the list without specifying the order.
     */
    @FXML
    void print(ActionEvent event){
        if(!(roster.getSize() == 0)){
            ouputBoard.appendText("* list of students in the roster **\n");
            for(int i = 0; i < roster.getSize(); i++){
                ouputBoard.appendText(roster.print(i));
            }
            ouputBoard.appendText("* end of roster **\n");
        }else{
            ouputBoard.appendText("Student roster is empty!\n");
        }
    }

    /**
     * Display the list which has paid with order by date.
     */
    @FXML
    void printByDate(ActionEvent event) {
        try {
            Student[] temp = roster.orderByDate();
            System.out.println(temp.length);
            if (!(temp.length == 0) && temp[0] != null) {
                ouputBoard.appendText("* list of students in the roster **\n");
                for (int i = 0; i < temp.length; i++) {
                    if(temp[i] != null) {
                        ouputBoard.appendText(temp[i].toString() + "\n");
                    }
                }
                ouputBoard.appendText("* end of roster **\n");
            } else {
                ouputBoard.appendText("No Student paid!\n");
            }
        }catch(Exception e){
            ouputBoard.appendText("No Student paid!\n");
        }
    }

    /**
     * Display the list with order by name.
     */
    @FXML
    void printByName(ActionEvent event) {
        if(!(roster.getSize() == 0)){
            ouputBoard.appendText("* list of students in the roster **\n");
            roster.orderByName();
            for(int i = 0; i < roster.getSize(); i++){
                ouputBoard.appendText(roster.print(i));
            }
            ouputBoard.appendText("* end of roster **\n");
        }else{
            ouputBoard.appendText("Student roster is empty!\n");
        }
    }

}
