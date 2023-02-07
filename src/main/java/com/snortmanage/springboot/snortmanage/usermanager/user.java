package com.snortmanage.springboot.snortmanage.usermanager;

import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class user {
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

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Transient
    private String logFilePath;
    @Transient
    private String confFilePath;
    @Transient
    private String ruleFilePath;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String username;
    private String password;
    private String operatingSystem;


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
        this.password = hashPassword(password);
    }
    public String getConfFilePath() { return confFilePath; }

    public String getRuleFilePath() { return ruleFilePath; }

    public String getLogFilePath() { return logFilePath; }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOpertatingSystem() {
        this.operatingSystem = String.valueOf(getOS());
    }

    @Override
    public String toString() {
        return  email + ',' + username +',' + operatingSystem;
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

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public void pathSetter() {
        operatingSystem = operatingSystem.toLowerCase();
        if (this.operatingSystem.contains("win")) {
            setRuleFilePath("/etc/snort/rules/local.rules");
            setLogFilePath("/home/hariprasaath/MainProject/");
            setConfFilePath("/etc/snort/test_snort.conf");
        } else if (operatingSystem.contains("nix") || operatingSystem.contains("nux")
                || operatingSystem.contains("aix")) {

            setRuleFilePath("/etc/snort/rules/local.rules");
            setLogFilePath("/home/hariprasaath/MainProject/");
            setConfFilePath("/etc/snort/test_snort.conf");

        } else if (this.operatingSystem.contains("mac")) {// Need to be implemented
        } else if (this.operatingSystem.contains("sunos")) { // Need to be implemented
        }

    }

    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    }


}
