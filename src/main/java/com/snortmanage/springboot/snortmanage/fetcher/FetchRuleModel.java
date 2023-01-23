package com.snortmanage.springboot.snortmanage.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FetchRuleModel {

    private String confFilePath = "/etc/snort/test_snort.conf";
    public String ruleValidation() throws IOException, InterruptedException {


        ProcessBuilder ps = new ProcessBuilder("snort", "-T", "-c", confFilePath);
        Process pr = ps.start();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        String line;
        while ((line = in.readLine()) != null) {
               System.out.println(line);
               if (line.contains("Error")){
                   return line;
               }
        }
        pr.waitFor();
        in.close();
        return "Config file verified";
    }
}
