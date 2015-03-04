package ch.unige;
import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Employee {

    /**
     */
    @NotNull
    private String firstname;

    /**
     */
    @NotNull
    private String lastname;

    /**
     */
    @NotNull
    private String email;

    /**
     */
    @NotNull
    private String password;

    /**
     */
    @NotNull
    private String job;

    /**
     */
    @NotNull
    private int experience;

    /**
     */
    @NotNull
    private int age;
    
    /**
     */
    @NotNull
    private String sex;


    /**
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Quizz> myQuizzes = new ArrayList<Quizz>();
    
    @OneToMany
    private List<QuizzResult> myQuizResults = new ArrayList<QuizzResult>();
}
