import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Extension{

    /* 
     * This extension reads a file while excluding certain popular or irrelevant words from the word count 
     * and from the binary search tree. This file references the WordCounter file for its many methods instead of 
     * rewriting all of them. The biggest change is to the analyze method, which is completely redeveloped and 
     * rewritten for the extension. It takes an additional parameter, an array of strings called commonWords, 
     * which provides flexibility to exclude different common words for different files. So this array can easily
     * be updated with each run.
     * 
     */
    
    WordCounter wc;
    public Extension(WordCounter wordCounter){
        wc = wordCounter;
    }

    //generates word count from a file of words
    public long analyze(String filename, String[] commonWords){
        //The tests for the analyze method are commented out. They can be seen as print statements buried within this method
        long initialTime = System.currentTimeMillis();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            // split line into words.
            
            while(line != null){ 
                String[] words = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();
                    
                    //Check to see if the word is a common word
                    boolean commonWord = false;
                   // System.out.println("commonWord" + commonWord);

                    for(int j = 0; j<commonWords.length; j++){
                      //  System.out.println("j = " + j + " word testing " + commonWords[j] + " the word: " + word);
                        if(word.equals(commonWords[j])){
                            commonWord = true;
                         //   System.out.println("its true");
                        }
                    }

                    if(!commonWord){ 
                        //updates map with each word
                        if(word.length() != 0){
                            //System.out.println("updating map");
                            wc.wordCount++;
                            if(wc.bst.containsKey(word)){
                                int value = wc.bst.get(word);
                                wc.bst.put(word, value+1); 
                            } else{
                                wc.bst.put(word, 1);
                            } 
                         //   System.out.println(wc.wordCount);                
                        }
                    }
                }
                line = br.readLine();
            } br.close();
            long finalTime = System.currentTimeMillis();
            long difference = finalTime - initialTime;
            return difference;
        } catch (FileNotFoundException ex) {
            System.out.println("WordCounter.analyze():: unable to open file " + filename);
            long finalTime = System.currentTimeMillis();
            long difference = finalTime - initialTime;
            return difference;
        } catch (IOException ex) {
            System.out.println("WordCounter.analyze():: error reading file " + filename);
            long finalTime = System.currentTimeMillis();
            long difference = finalTime - initialTime;
            return difference;
        }
    }

    //THIS FILE IS RUN THROUGH THE WORDCOUNTER FILE. UNCOMMENT THE EXTENSION CODE IN WORDCOUNTER MAIN METHOD TO SEE THIS FILE RUN
}
