package nativeCamp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static nativeCamp.Constants.*;

public class GetMyPageTeacher implements Runnable {
    public volatile static boolean GOT_TEACHER;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new GetMyPageTeacher();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            Thread.sleep(200);
        }
    }

    private void getTeachers() throws InterruptedException, IOException {
        HttpSendUtil httpSendUtil = new HttpSendUtil();

        while (true) {

            long startTime = System.currentTimeMillis();

            String response = httpSendUtil.sendPOST(URL, COOKIE, CONDITION, USER_AGENT);
            long gotResponseTime = System.currentTimeMillis();

            if (response == null) {
                continue;
            }

            System.out.println("Response time: " + (gotResponseTime - startTime) + "ms");

            Set<Integer> teacherIDSet = getTeacherIDSet(response);

            if (!teacherIDSet.isEmpty()) {
                Integer teacherID = teacherSort(teacherIDSet, USE_CERTAIN_ID);
                if (teacherID != null) {

                    synchronized (String.class) {
                        if (GOT_TEACHER == true) {
                            break;
                        }
                        GOT_TEACHER = true;
                    }
                    TestTurnOnBrowser.turnOnTeacherPage(teacherID);
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    System.out.println("teacherID: " + teacherID + "   time:" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    break;

                }
            }

        }
    }


    private static Integer teacherSort(Set<Integer> teacherIDSet, Boolean useCertainID) {
        if (teacherIDSet.contains(CERTAIN_ID)) {
            return CERTAIN_ID;
        }

        if (useCertainID) {
            return null;
        }

        return teacherIDSet.stream().findFirst().orElse(null);
    }

    private static Set<Integer> getTeacherIDSet(String response) {
        Set<Integer> teacherIDSet = new HashSet<>();
        String[] array = response.split("https://nativecamp.net/zh-tw/waiting/detail/");

        if (array == null || array.length == 0) {
            return teacherIDSet;
        }

        for (int i = 1; i < array.length; i += 2) {
            String id;
            Integer idInt = null;

            for(int j = 5 ; j > 1 ; j --) {
                try {
                    id = array[i].substring(0, j);
                    idInt = Integer.parseInt(id);
                    break;
                } catch (Exception e) {;

                }
            }
            teacherIDSet.add(idInt);
        }

        return teacherIDSet;
    }

    @Override
    public void run() {
        try {
            getTeachers();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
