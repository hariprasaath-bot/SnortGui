package com.snortmanage.springboot.snortmanage.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snortmanage.springboot.snortmanage.usermanager.UserController;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Entity
public class rule extends UserController {

    @Id
    private int sid;
    private String protocol;
    private String srcip;
    private String src_port;
    private String dst_ip;
    private String dst_port;
    private String message;
    private String num_pkts;
    private String user;


    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }
    @Transient
    private  String rule;

    @JsonIgnore
    public com.snortmanage.springboot.snortmanage.usermanager.user getLogobj() {
        return logobj;
    }

    @Transient
    private com.snortmanage.springboot.snortmanage.usermanager.user logobj;
    public String getProtocol() {

        return protocol;
    }

    public void setProtocol(String protocol) {
        if (protocol != "") {
            this.protocol = protocol;
        }else{
            this.protocol = "TCP";
        }
    }

    public String getSrcip() {

        return srcip;
    }

    public void setSrcip(String srcip) {
        if (srcip != "") {
            this.srcip = srcip;
        }else{
            this.srcip = "any";
        }
    }
    public void setLogobj(com.snortmanage.springboot.snortmanage.usermanager.user logobj) {
        this.logobj = logobj;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getSrc_port() {
        return src_port;
    }

    public void setSrc_port(String src_port) {
        if (src_port != "") {
            this.src_port = src_port;
        }else{
            this.src_port = "any";
        }
    }
    @JsonIgnore
    public String getRule() {
        return rule;
    }
    public String getDst_ip() {
        return dst_ip;
    }

    public void setDst_ip(String dst_ip) {
        if (dst_ip != "") {
            this.dst_ip = dst_ip;
        }else{
            this.dst_ip = "any";
        }
    }

    public String getDst_port() {
        return dst_port;
    }

    public void setDst_port(String dst_port) {
        if (dst_port != "") {
            this.dst_port = dst_port;
        }else{
            this.dst_port = "any";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNum_pkts() {
        return num_pkts;
    }

    public void setNum_pkts(String num_pkts) {
        this.num_pkts = num_pkts;
    }

    @Override
    public String toString() {
        return Integer.toString(sid)+','+ protocol + ','+ srcip + ',' + src_port + ',' + dst_ip + ','+ dst_port + ','+ message + ',' + user;
    }

    public String ruleGenerator(){
        //model rule alert tcp any any ->  any any (msg:"DDoS detected ";  flow: stateless; threshold: type both, track by_src, count 3, seconds 8 ; priority: 1; sid:1000003; rev:2)
        this.rule = "alert "+protocol+" "+srcip+" "+src_port+" <> "+dst_ip+" "+dst_port+" "+"(msg: "+message+";"+ " priority: 1; sid: "+sid+";"+"rev:1)";
        return rule;
    }

    public void addRuleFile() throws IOException {
        System.out.println("hello "+rule);
        try {


            FileWriter fWriter = new FileWriter(logobj.getRuleFilePath(),true);

            FileWriter fWriter1 = new FileWriter(logobj.getRuleFilePath(),true);
            BufferedWriter br = new BufferedWriter(fWriter1);
            br.newLine();
            br.write(rule);
            br.close();
            fWriter1.close();
            System.out.println( "File is created successfully with the content.");
        }
        // Catch block to handle if exception occurs
        catch (IOException e) {
            // Print the exception
            System.out.print(e.getMessage());
        };
    }
}
