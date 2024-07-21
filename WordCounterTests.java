/* Katie Bernard
 * 11/14
 */
import java.util.ArrayList;
public class WordCounterTests{
    public static void main(String[] args){
        

         //case 1: testing wordCounter(data_structure)
         {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");

            //test
            assert w1 != null : "Error in WordCounter:wordCounter()";
            assert w2 != null : "Error in FizzBuzz:wordCounter()";   
        }

        //case 2: testing readWords(filename)
        {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");

            //test
            ArrayList<String> words = new ArrayList<String>();
            words.add("it");
            words.add("was");
            words.add("the");
            words.add("best");
            words.add("of");
            words.add("times");
            words.add("it");
            words.add("was");
            words.add("the");
            words.add("worst");
            words.add("of");
            words.add("times");
            words.add("it");
            words.add("was");
            words.add("the");
            words.add("age");
            words.add("of");
            words.add("wisdom");
            words.add("it");
            words.add("was");
            words.add("the");
            words.add("age");
            words.add("of");
            words.add("foolishness");

            assert w1.readWords("counttest.txt").equals(words) : "Error in WordCounter:readWords()";
            assert w2.readWords("counttest.txt").equals(words) : "Error in WordCounter:readWords()";
        }

        //case 3: testing buildMap(ArrayList<String> words)
        {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");
            
            ArrayList<String> words = w1.readWords("counttest.txt");
            //test
            assert w1.buildMap(words) != 0 : "Error in WordCounter:buildMap()";
            assert w2.buildMap(words) != 0 : "Error in WordCounter:buildMap()";
            assert w1.map != null : "Error in WordCounter:buildMap()";
            assert w2.map != null : "Error in WordCounter:buildMap()";

        }

         //case 4: testing clearMap()
         {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");
            
            ArrayList<String> words1 = w1.readWords("counttest.txt");
            ArrayList<String> words2 = w2.readWords("counttest.txt");
            w1.buildMap(words1);
            w2.buildMap(words2);
            
            w1.clearMap();
            w2.clearMap();

            //test
            assert w1.wordCount == 0 : "Error in WordCounter:clear()";
            assert w2.wordCount == 0 : "Error in WordCounter:clear()";

        }

        //case 5: testing getTotalWordCount()
        {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");

            ArrayList<String> words1 = w1.readWords("counttest.txt");
            ArrayList<String> words2 = w2.readWords("counttest.txt");
            w1.buildMap(words1);
            w2.buildMap(words2);


            //test
            assert w1.getTotalWordCount() == 24 : "Error in WordCounter:getTotalWordCount()";
            assert w2.getTotalWordCount() == 24 : "Error in WordCounter:getTotalWordCount()";

        }

        //case 6: testing getUniqueWordCount()
        {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");

            ArrayList<String> words1 = w1.readWords("counttest.txt");
            ArrayList<String> words2 = w2.readWords("counttest.txt");
            w1.buildMap(words1);
            w2.buildMap(words2);

            //test
            assert w1.getUniqueWordCount() == 10 : "Error in WordCounter:getUniqueWordCount()";
            assert w2.getUniqueWordCount() == 10 : "Error in WordCounter:getUniqueWordCount()";

        }

        //case 7: testing getCount(String word)
        {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");

            //test
            assert w1.getCount("the") == 2 : "Error in WordCounter:getCount()";
            assert w2.getCount("the") == 2 : "Error in WordCounter:getCount()";

        }

        
        //case 8: testing getFrequency(String word)
        {
            //set up
            WordCounter w1 = new WordCounter("bst");
            WordCounter w2 = new WordCounter("hashmap");

            //test
            assert w1.getFrequency("the") == 0.16 : "Error in WordCounter:getFrequency()";
            assert w2.getFrequency("the") == 0.16 : "Error in WordCounter:getFrequency()";

        }


        System.out.println("Tests all done!");
        }

    }

