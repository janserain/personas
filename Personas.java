// playing with lambda expressions 

import java.util.*;
import java.util.function.*;

class Person {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    Sex gender; 
    int age;

    public Person(String name, Sex gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Person() {
        this("nobody", Sex.MALE, 32);
    }

    public int getAge() {
        return age;
    }

    public Sex getGender() {
        return gender;
    }

    public void printPerson() {
        System.out.println(name + (gender == Sex.MALE ? ": male : " : ": female : ") + getAge());
    }
}

interface CheckPerson {
    boolean test(Person p);
}

public class Personas {
    public static void main(String[] args) {
        Person p1 = new Person("Jack London", Person.Sex.MALE, 22);
        Person p2 = new Person();

        ArrayList<Person> t = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            t.add(new Person("some " + i, (i % 2 == 0) ? Person.Sex.FEMALE : Person.Sex.MALE, i * 2 + 10));
        }
        t.add(p1); 
        t.add(p2); 

        for (Person os : t) {
            os.printPerson();
        }

        prt("------------------");
        printThem(
                t,
                new CheckPerson() {
                    public boolean test(Person p) {
                        return p.getGender() == Person.Sex.MALE
                            && p.getAge() >= 18
                            && p.getAge() <= 25;
                    }
                }
        );

        prt("--- Lambda Expression -----------");
        printThem(t,
                 p -> p.getGender() == Person.Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25
        );
        prt("--- Lambda Expression with Predicate -----------");
        printThemAll(t,
                a -> a.getGender() == Person.Sex.MALE
                    && a.getAge() >= 18
                    && a.getAge() <= 25
        );
    }

    public static void printThem(ArrayList<Person> list, CheckPerson tester) {
        for (Person p : list) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printThemAll(ArrayList<Person> list, Predicate<Person> tester) {
        for (Person p : list) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void prt(String s) {
        System.out.println(s);
    }
}
