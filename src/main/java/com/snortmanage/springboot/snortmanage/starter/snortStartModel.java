package com.snortmanage.springboot.snortmanage.starter;

import java.util.Objects;
import java.io.*;
import java.lang.*;

// snort compile code: snort -A console -i wlp3s0 -q -c /etc/snort/test_snort.conf
// snort -A full -l /home/hariprasaath/versions/miniproject_bash -i wlp3s0  -c /etc/snort/test_snort.conf
public class snortStartModel {
    private String snortMode;
    private String compArg;

    private String logMode;
    private String logFilePath = "/home/hariprasaath/MainProject/";

    private String inface = "wlp3s0";

    private String confFilePath = "/etc/snort/test_snort.conf";

    private String logComp;

    public String getSnortMode() {
        return snortMode;
    }

    public void setSnortMode(String snortMode) {
        this.snortMode = snortMode;
    }

    public String getLogMode() {
        return logMode;
    }

    public void setLogMode(String logMode) {
        this.logMode = logMode;
    }

    public String getInface() {
        return inface;
    }

    public void setInface(String inface) {
        if (inface != "") {
            this.inface = inface;
        } else {
            this.inface = "wlp3s0";
        }
    }

    //ProcessBuilder ps = new ProcessBuilder("snort", "-A", "console", "-i", "wlp3s0", "-q", "-c", "/etc/snort/test_snort.conf");
    public String snortStarter() throws IOException, InterruptedException {
        String alert = "";

        if (Objects.equals(snortMode, "logger")) {
            logComp = "full";

        } else if (Objects.equals(snortMode, "sniffer")) {
            logComp = "";
            snifferHandle();
        } else if (Objects.equals(snortMode, "IDS")) {
            logComp = "console";

        }
        if (Objects.equals(logMode, "alerttoconsole")) {
            alert = idsHandle(false);
            return alert;
        } else if (Objects.equals(logMode, "alerttoweb")) {
            System.out.println("IDS MODE WITH ALERT TO WEB");
            alert = idsHandle(true);
            return alert;
        }
        return alert;
    }

    public String idsHandle(boolean webwrite) throws IOException, InterruptedException {

        System.out.println("Staring SNORT IN IDS MODE");
        ProcessBuilder ps = new ProcessBuilder("snort", "-A", logComp, "-i", inface, "-q", "-c", confFilePath);
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = in.readLine()) != null) {

            if (webwrite) {
                pr.waitFor();
                System.out.println("ok!");
                in.close();
                return line;
            } else {
                System.out.println(line);
            }
        }
        pr.waitFor();
        System.out.println("ok!");
        in.close();
        return line;
    }

    public void snifferHandle() throws IOException, InterruptedException {

        ProcessBuilder ps = new ProcessBuilder("snort", "-A", logComp, "-i", inface);
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        pr.waitFor();
        System.out.println("ok!");

        in.close();
    }
}
