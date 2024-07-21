/* Katie Bernard
 * 11/14
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WordCounter  {
    //the class that creates and manages a BSTMap of word-count pairs KeyValuePair<String, Integer>
    int wordCount;
    MapSet<String, Integer> map;
    String dataStructure;

    public WordCounter(String data_structure){
        dataStructure = data_structure;
        if(data_structure == "bst"){ 
            map = new BSTMap<String, Integer>();
        } else if (data_structure == "hashmap"){
            map = new HashMap<String, Integer>();    
        }
        wordCount = 0;
    }

    
    /**
     * Generates an array list of words from a given file
     */
    public ArrayList<String> readWords(String filename){
        ArrayList<String> toReturn = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            // split line into words.
            
            while(line != null){ 
                String[] words = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();

                    //updates map with each word
                    if(word.length() != 0){
                        wordCount++;
                        toReturn.add(word);               
                    }
                }
                line = br.readLine();
            } br.close();
            return toReturn;
        } catch (FileNotFoundException ex) {
            System.out.println("WordCounter.analyze():: unable to open file " + filename);
            return toReturn;
        } catch (IOException ex) {
            System.out.println("WordCounter.analyze():: error reading file " + filename);
            return toReturn;
        }
    }

    public double buildMap(ArrayList<String> words){
        double initialTime = System.nanoTime();
        for(String word : words){
            if(map.containsKey(word)){
                int value = map.get(word);
                map.put(word, value+1); 
            } else{
                map.put(word, 1);
            }     
        }
        double finalTime = System.nanoTime();
        double difference = finalTime - initialTime;
        return difference * 1000000;
    }

    public void clearMap(){
        wordCount = 0;
        map.clear();
    }

    public int getTotalWordCount(){
        return wordCount;
    }

    public int getUniqueWordCount(){
        return map.size();
    }

    public int getCount(String word){
        return map.get(word);
    }
    
    public double getFrequency(String word){
        double uniqueCount = getCount(word);
        double totalCount = getTotalWordCount();
        return (uniqueCount / totalCount);
    }

    // private void writeBstCsv(String newFile, String commentFile){
    //     ArrayList<String> words = readWords(commentFile);
    //     buildMap(words);
    //     try{ 
    //         FileWriter fw = new FileWriter(newFile);
    //         fw.write(average(commentFile) + ", " + getTotalWordCount() + ", " + getUniqueWordCount() + ", " + map.depth()+"\n");
    //         fw.close();
    //     }catch (IOException e) {
    //             System.out.println("An error occurred.");
    //             e.printStackTrace();
    //     }
    // }

    // private void writeHashCsv(String newFile, String commentFile){
    //     ArrayList<String> words = readWords(commentFile);
    //     buildMap(words);
    //     try{ 
    //         FileWriter fw = new FileWriter(newFile);
    //         fw.write(average(commentFile) + ", " + getTotalWordCount() + ", " + getUniqueWordCount() + ", " + map.collisions()+"\n");
    //         fw.close();
    //     }catch (IOException e) {
    //             System.out.println("An error occurred.");
    //             e.printStackTrace();
    //     }
    // }

    // public void writeCsv( String filename, String struct){
    //     if(struct == "bst"){ 
    //         try{ 
    //             FileWriter fw = new FileWriter(filename);
    //             fw.write("Average RunTime (ms), Total Words, Total Unique Words, Depth of Tree"+"\n");
    //             writeBstCsv(filename, "reddit_comments_2008.txt");
    //             writeBstCsv(filename, "reddit_comments_2009.txt");
    //             writeBstCsv(filename, "reddit_comments_2010.txt");
    //             writeBstCsv(filename, "reddit_comments_2011.txt");
    //             writeBstCsv(filename, "reddit_comments_2012.txt");
    //             writeBstCsv(filename, "reddit_comments_2013.txt");
    //             writeBstCsv(filename, "reddit_comments_2014.txt");
    //             writeBstCsv(filename, "reddit_comments_2015.txt");
                
    //             fw.close();
    //         }catch (IOException e) {
    //             System.out.println("An error occurred.");
    //             e.printStackTrace();
    //             }
    //     }
    //     else if (struct == "hashmap"){
    //         try{ 
    //             FileWriter fw = new FileWriter(filename);
    //             fw.write("Average RunTime (ms), Total Words, Total Unique Words, Number of Collisions"+"\n");
    //             writeHashCsv(filename, "reddit_comments_2008.txt");
    //             writeHashCsv(filename, "reddit_comments_2009.txt");
    //             writeHashCsv(filename, "reddit_comments_2010.txt");
    //             writeHashCsv(filename, "reddit_comments_2011.txt");
    //             writeHashCsv(filename, "reddit_comments_2012.txt");
    //             writeHashCsv(filename, "reddit_comments_2013.txt");
    //             writeHashCsv(filename, "reddit_comments_2014.txt");
    //             writeHashCsv(filename, "reddit_comments_2015.txt");
                
    //             fw.close();
    //         }catch (IOException e) {
    //             System.out.println("An error occurred.");
    //             e.printStackTrace();
    //             }
    //     }
    // }


    private String average(String file){
        double[] times = new double[5];
        int counter = 0;

        while(counter<5){ 
            ArrayList<String> words = readWords(file);
            System.out.println("Finished reading words");
            double time = buildMap(words);
            System.out.println("Finished building the map");
            times[counter] = time;
            clearMap();
            counter++;
        }

        double highest = 0;
        int highestIndex = 0;
        double lowest = 999999999;
        int lowestIndex = 0;
        for(int i = 0; i<5; i++){
            if(times[i] < lowest){
                lowest = times[i];
                lowestIndex = i;
            } if(times[i] > highest){
                highest = times[i];
                highestIndex = i;
            }
        }
        ArrayList<Double> thinnedTimes = new ArrayList<Double>();
        for(int i = 0; i<5; i++){
            if(i != lowestIndex && i != highestIndex ){
                thinnedTimes.add(times[i]);
            }
        }

        double sum = 0;
        for(int i = 0; i<thinnedTimes.size(); i++){
            sum += thinnedTimes.get(i);
        }
        double average = sum/thinnedTimes.size();

        ArrayList<String> words = readWords(file);
        buildMap(words);

        return ( average/1000000 + ", " + getTotalWordCount() + ", " + getUniqueWordCount() + ", " + map.depth());
    }


    public static void main(String args[]){
        WordCounter wc = new WordCounter("bst");
        System.out.println("Average run time, word count, unique word count, depth");
        System.out.println(wc.average("reddit_comments_2015.txt"));
    
    }


}