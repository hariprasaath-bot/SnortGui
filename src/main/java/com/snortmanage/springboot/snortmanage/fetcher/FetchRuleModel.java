package com.snortmanage.springboot.snortmanage.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FetchRuleModel {

    private String confFilePath = "/etc/snort/test_snort.conf";
    public void ruleValidation() throws IOException, InterruptedException {


        ProcessBuilder ps = new ProcessBuilder("snort", "-T", "-c", confFilePath);
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
               System.out.println(line);
        }
        pr.waitFor();
        System.out.println("ok!");

        in.close();

    }
}
