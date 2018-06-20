package com.slanguist.exquisitecorpsemaker.equisitecorpseapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Slanguist on 6/7/2018.
 */

public class ExquisiteCorpse {
    private List<String> lines = new ArrayList<>();

    public ExquisiteCorpse() {
    }

    public ExquisiteCorpse(List<String> lines) {
        this.lines = lines;
    }

    private String title = "";

    public List<String> getLines () {
        return this.lines;
    }

    public void setLines(List<String> lines){
        this.lines = lines;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setLine(int lineToSet, String newLine){
        this.lines.set(lineToSet, newLine);
    }

    public String getLine(int lineToGet){
        return this.lines.get(lineToGet);
    }

    public ExquisiteCorpse scramblePoem() {
        List<String> copyOfLines = this.lines;
        Collections.shuffle(copyOfLines);
        ExquisiteCorpse newPoem = new ExquisiteCorpse(copyOfLines);
        return newPoem;
    }

    public void printPoem(){
        System.out.println(this.title + "\n");
        for(String line: this.lines){
            System.out.println(line);
        }
        System.out.println("\n" + "\n");
    }
}