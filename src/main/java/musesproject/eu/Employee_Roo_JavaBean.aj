// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package musesproject.eu;

import java.util.List;
import musesproject.eu.Employee;
import musesproject.eu.Quizz;
import musesproject.eu.QuizzResult;

privileged aspect Employee_Roo_JavaBean {
    
    public String Employee.getFirstname() {
        return this.firstname;
    }
    
    public void Employee.setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String Employee.getLastname() {
        return this.lastname;
    }
    
    public void Employee.setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String Employee.getEmail() {
        return this.email;
    }
    
    public void Employee.setEmail(String email) {
        this.email = email;
    }
    
    public String Employee.getPassword() {
        return this.password;
    }
    
    public void Employee.setPassword(String password) {
        this.password = password;
    }
    
    public String Employee.getJob() {
        return this.job;
    }
    
    public void Employee.setJob(String job) {
        this.job = job;
    }
    
    public int Employee.getExperience() {
        return this.experience;
    }
    
    public void Employee.setExperience(int experience) {
        this.experience = experience;
    }
    
    public int Employee.getAge() {
        return this.age;
    }
    
    public void Employee.setAge(int age) {
        this.age = age;
    }
    
    public String Employee.getSex() {
        return this.sex;
    }
    
    public void Employee.setSex(String sex) {
        this.sex = sex;
    }
    
    public List<Quizz> Employee.getMyQuizzes() {
        return this.myQuizzes;
    }
    
    public void Employee.setMyQuizzes(List<Quizz> myQuizzes) {
        this.myQuizzes = myQuizzes;
    }
    
    public List<QuizzResult> Employee.getMyQuizResults() {
        return this.myQuizResults;
    }
    
    public void Employee.setMyQuizResults(List<QuizzResult> myQuizResults) {
        this.myQuizResults = myQuizResults;
    }
    
}
