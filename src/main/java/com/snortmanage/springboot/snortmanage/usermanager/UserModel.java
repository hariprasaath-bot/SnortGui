package com.snortmanage.springboot.snortmanage.usermanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class UserModel {
    @Transient
    private static OS os = null;

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void setConfFilePath(String confFilePath) {
        this.confFilePath = confFilePath;
    }

    public void setRuleFilePath(String ruleFilePath) {
        this.ruleFilePath = ruleFilePath;
    }

    public void setOpertatingSystem(String opertatingSystem) {
        this.opertatingSystem = opertatingSystem;
    }

    @Transient
    private String logFilePath;
    @Transient
    private String confFilePath;
    @Transient
    private String ruleFilePath;
    @Id
    private String email;
    private String username;
    private String password;
    private String opertatingSystem;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfFilePath() { return confFilePath; }

    public String getRuleFilePath() { return ruleFilePath; }

    public String getLogFilePath() { return logFilePath; }

    public String getOperatingSystem() {
        return opertatingSystem;
    }

    public void setOpertatingSystem() {
        this.opertatingSystem = String.valueOf(getOS());
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "logFilePath='" + logFilePath + '\'' +
                ", confFilePath='" + confFilePath + '\'' +
                ", ruleFilePath='" + ruleFilePath + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", opertatingSystem='" + opertatingSystem + '\'' +
                '}';
    }

    //OS detection function
    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;

            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else if (operSys.contains("sunos")) {
                os = OS.SOLARIS;
            }
        }
        return os;
    }

    public void pathSetter() {
        opertatingSystem = opertatingSystem.toLowerCase();
        if (this.opertatingSystem.contains("win")) {
            setRuleFilePath("C:/Snort/rules/local.rules");
            setLogFilePath("C:/Snort/");
            setConfFilePath("C:/Snort/etc/test_snort.conf");
        } else if (opertatingSystem.contains("nix") || opertatingSystem.contains("nux")
                || opertatingSystem.contains("aix")) {

            setRuleFilePath("/etc/snort/rules/local.rules");
            setLogFilePath("/home/hariprasaath/MainProject/");
            setConfFilePath("/etc/snort/test_snort.conf");

        } else if (this.opertatingSystem.contains("mac")) {// Need to be implemented
        } else if (this.opertatingSystem.contains("sunos")) { // Need to be implemented
        }

    }

    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    }


}
