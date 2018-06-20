package com.slanguist.exquisitecorpsemaker.ui;

import com.slanguist.exquisitecorpsemaker.equisitecorpseapp.ExquisiteCorpse;
import com.slanguist.exquisitecorpsemaker.equisitecorpseapp.ExquisiteCorpseStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Slanguist on 6/7/2018.
 */
public class ExquisiteCorpseUI {

    private static final ExquisiteCorpseStore exquisiteCorpseStore = new ExquisiteCorpseStore();

    private boolean quit = false;

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public void runUI() {
        while (!this.isQuit()) {
            this.mainMenu();
        }
    }

    public void mainMenu(){
        System.out.println("EXQUISITE CORPSE GENERATOR");
        System.out.println("Please choose an option: (type number and hit enter)");
        System.out.println("1. Create New Exquisite Corpse");
        System.out.println("2. View an Existing Exquisite Corpse");
        System.out.println("3. Edit an Existing Exquisite Corpse");
        System.out.println("4. Scramble an existing Exquisite Corpse");
        System.out.println("5. Delete an existing Exquisite Corpse");
        System.out.println("6. Change Exquisite Corpse Title");
        System.out.println("Or enter 0 to quit: ");

        Scanner mainMenuScanner = new Scanner(System.in);
        int selection = Integer.parseInt(mainMenuScanner.next());

        switch (selection){
            case 1:
                this.createNew();
                break;
            case 2:
                this.viewCorpse();
                break;
            case 3:
                this.editExisting();
                break;
            case 4:
                this.scrambleAPoem();
                break;
            case 5:
                this.deletePoem();
                break;
            case 6:
                this.changeTitle();
                break;
            case 0:
                this.quit = true;
                break;
            default:
                System.out.println("Invalid input! Try again");
                this.mainMenu();
                break;
        }

    }

    private ExquisiteCorpse selectPoemFromStore(){
        System.out.println("Enter the number of a poem to select it: ");
        for (ExquisiteCorpse corpse: exquisiteCorpseStore.getCollectionOfECorpses()){
            System.out.println(exquisiteCorpseStore.getCollectionOfECorpses().indexOf(corpse) + ". " + corpse.getTitle());
        }
        Scanner poemSelectionScanner = new Scanner(System.in);
        int selection = Integer.parseInt(poemSelectionScanner.next());

        return exquisiteCorpseStore.getCollectionOfECorpses().get(selection);
    }

    private void changeTitle() {
        ExquisiteCorpse corpseToChange = this.selectPoemFromStore();
        int indexOfCorpse = this.exquisiteCorpseStore.getCollectionOfECorpses().indexOf(corpseToChange);
        System.out.println("Enter the new title: ");
        Scanner titleScanner = new Scanner(System.in);
        String newTitle = titleScanner.next();

        corpseToChange.setTitle(newTitle);
        exquisiteCorpseStore.getCollectionOfECorpses().set(indexOfCorpse, corpseToChange);
        System.out.println("Success");
    }

    private void deletePoem() {
        ExquisiteCorpse corpseToDelete = this.selectPoemFromStore();
        int indexOfCorpse = this.exquisiteCorpseStore.getCollectionOfECorpses().indexOf(corpseToDelete);
        exquisiteCorpseStore.getCollectionOfECorpses().remove(indexOfCorpse);
        System.out.println("Success");
    }

    private void scrambleAPoem() {
        ExquisiteCorpse corpseToScramble = this.selectPoemFromStore();
        int indexOfCorpse = this.exquisiteCorpseStore.getCollectionOfECorpses().indexOf(corpseToScramble);
        ExquisiteCorpse scrambledCorpse = corpseToScramble.scramblePoem();
        System.out.println("Enter a title for the new poem: ");
        scrambledCorpse.printPoem();

        Scanner titleScanner = new Scanner(System.in);
        String title = titleScanner.next();
        scrambledCorpse.setTitle(title);

        System.out.println("Save scrambled poem? Y/N");
        String selection = titleScanner.next();
        switch (selection){
            case "Y":
                exquisiteCorpseStore.getCollectionOfECorpses().add(scrambledCorpse);
                System.out.println("SUCCESS");
                break;
            case "N":
                System.out.println("OK, maybe next time!");
                break;
        }

    }

    private void editExisting() {
        ExquisiteCorpse corpseToEdit = this.selectPoemFromStore();
        int indexOfCorpse = this.exquisiteCorpseStore.getCollectionOfECorpses().indexOf(corpseToEdit);
        System.out.println("Please enter a line number to edit, or enter 888 to add a new line: ");
        for(String line : corpseToEdit.getLines()){
            System.out.println(corpseToEdit.getLines().indexOf(line) + ". " + line);
        }

        Scanner lineIndexScanner = new Scanner(System.in);
        int lineIndexToChange = Integer.parseInt(lineIndexScanner.next());

        switch(lineIndexToChange) {
            case 888:
                System.out.println("Enter the new line: ");
                String newLine = lineIndexScanner.next();
                corpseToEdit.getLines().add(newLine);
                break;
            default:
                System.out.println("Enter the new line: ");
                String anotherNewLine = lineIndexScanner.next();
                corpseToEdit.setLine(lineIndexToChange, anotherNewLine);
                break;
        }

        System.out.println("SUCCESS");

    }

    private void viewCorpse() {
        ExquisiteCorpse corpseToDisplay = this.selectPoemFromStore();
        int indexOfCorpse = this.exquisiteCorpseStore.getCollectionOfECorpses().indexOf(corpseToDisplay);
        corpseToDisplay.printPoem();
    }

    private void createNew() {

        Scanner titleScanner = new Scanner(System.in);

        System.out.println("How many lines in the Poem?");
        int numLines = Integer.parseInt(titleScanner.next());

        List<String> linesOfNewPoem = new ArrayList<>();
        for(int i = 0; i < numLines; i++){
            System.out.println("Enter line: ");
            String line = titleScanner.next();
            linesOfNewPoem.add(line);
        }

        ExquisiteCorpse newCorpse = new ExquisiteCorpse(linesOfNewPoem);
        System.out.println("Enter a title for the new poem: ");
        String title = titleScanner.next();
        newCorpse.setTitle(title);

        System.out.println("Save poem? Y/N");
        String selection = titleScanner.next();
        switch (selection){
            case "Y":
                exquisiteCorpseStore.getCollectionOfECorpses().add(newCorpse);
                System.out.println("SUCCESS");
                break;
            case "N":
                System.out.println("OK, maybe next time!");
                break;
            default:
                System.out.println("Invalid Selection! Saving...");
                exquisiteCorpseStore.getCollectionOfECorpses().add(newCorpse);
        }
    }
}
