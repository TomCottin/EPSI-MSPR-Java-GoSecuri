package com.groupe5.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Executable {
    public static void main(String[] args) {

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(
                    "D:\\cours_epsi\\B3\\DevOps\\devops-g5-app\\src\\main\\java\\com\\groupe5\\app\\config.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        TextToHtml txtToHtml = new TextToHtml();
        String txtToString = txtToHtml.getFileContent(prop.getProperty("equipement.list.txt"));
        HashMap<String, String> dicoGears = txtToHtml.getTxtFileContentToDico(txtToString);

        List<String> listeAgentId = Arrays
                .asList(txtToHtml.getFileContent(prop.getProperty("staff.list.txt")).split("\\n"));

        try {
            txtToHtml.toHtmlPage(null, "listeAgent", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String agentId : listeAgentId) {
            Agent agent = new Agent();
            if (txtToHtml.isExistingFileForAgentId(prop.getProperty("staff.directory"), agentId)) {
                agent.fillData(prop.getProperty("staff.directory") + agentId + ".txt");
                try {
                    txtToHtml.toHtmlPage(dicoGears, "ficheAgent", agent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}