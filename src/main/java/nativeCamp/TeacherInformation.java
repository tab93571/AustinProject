package nativeCamp;

import java.util.HashMap;
import java.util.Map;

public enum TeacherInformation {


    JELENA(39784,"Jelena","dentist nice to talk to" ),
    ANDREA(34005,"Andrea","nice to talk to" ),
    ARIEL(17662,"Ariel","young girl from Albania");

    private static Map<Integer,String> teacherMap = new HashMap();

    TeacherInformation(Integer teacherID,String teacherName,String teacherInformation){
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.teacherInformation = teacherInformation;
    }

    static {
        createTeacherMap();
    }

    public static void createTeacherMap(){

        for(TeacherInformation i:TeacherInformation.values()){
            teacherMap.put(i.getTeacherID(),i.getTeacherName());
        }

    }


    private Integer teacherID;

    private String teacherName;

    private String teacherInformation;


    public Integer getTeacherID() {
        return teacherID;
    }

    public String getTeacherName() { return teacherName; }

    public String getTeacherInformation() { return teacherInformation; }

    public static Map<Integer,String> getTeacherMap(){
        return teacherMap;
    }

}

