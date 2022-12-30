package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherstudentMapping;



    //Initialization is very important :

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherstudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student)&&teacherMap.containsKey(teacher)){

            List<String> currentstudentsofteacher = new ArrayList<>();

            if(teacherstudentMapping.containsKey(teacher))
                currentstudentsofteacher = teacherstudentMapping.get(teacher);

            currentstudentsofteacher.add(student);

            teacherstudentMapping.put(teacher,currentstudentsofteacher);

        }
    }
    public Student findStudent(String student){
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        return teacherMap.get(teacher);
    }

    public List<String> findStudentFromTeacher(String teacher){
        List<String> studentsList = new ArrayList<String>();
        if(teacherstudentMapping.containsKey(teacher)) studentsList = teacherstudentMapping.get(teacher);
        return studentsList;
    }

    public List<String> findAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){

        List<String> students = new ArrayList<String>();
        if(teacherstudentMapping.containsKey(teacher)){

            students = teacherstudentMapping.get(teacher);


            for(String student: students){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }


            teacherstudentMapping.remove(teacher);
        }


        if(teacherMap.containsKey(teacher)){
            teacherMap.remove(teacher);
        }
    }

    public void deleteAllTeacher(){

        HashSet<String> StudentSet = new HashSet<String>();


        teacherMap = new HashMap<>();


        for(String teacher: teacherstudentMapping.keySet()){


            for(String student: teacherstudentMapping.get(teacher)){
                StudentSet.add(student);
            }
        }


        for(String student: StudentSet){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }

        teacherstudentMapping = new HashMap<>();
    }
}
