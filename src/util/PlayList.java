package util;

import java.util.Hashtable;
import java.util.Scanner;


public class PlayList implements KVStore {
  public static Hashtable<String, String> pl;

  public PlayList () {
    pl = new Hashtable<String, String>();
  }

  public PlayList(String str) {
    pl = new Hashtable<String, String>();
    unmarshal(str);
  }
  
  public boolean add(String song, String url){
    pl.put(song, url);
    return true;
  }

  public boolean delete(String song, String url){
    if(pl.containsKey(song)){
      pl.remove(song);
      return true;
    } else {
      return false;
    }
  }

  public boolean edit(String song, String url) {
    if(pl.containsKey(song)) {
      pl.remove(song);
      pl.put(song, url);
      return true;
    } else {
      return false;
    }
  }

  public String marshal() {
    StringBuilder sb = new StringBuilder();
    for (String song : pl.keySet()) {
      sb.append(song + ":" + pl.get(song) + '\n');
    }
    return sb.toString();
  }

  public void unmarshal(String str) {
    Scanner lineSc = new Scanner(str);
    while (lineSc.hasNextLine()) {
      String line = lineSc.nextLine();
      int kvBreaker = line.indexOf(':');
      add(line.substring(0, kvBreaker), line.substring(kvBreaker + 1));
    }
    lineSc.close();    
  }
}
