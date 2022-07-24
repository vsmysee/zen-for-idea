package com.intellij.ide.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class KeyDef {

  private static List<String> load(String file) {
    InputStream is = null;
    BufferedReader reader = null;
    List<String> list = new ArrayList<>();
    try {
      is = KeyDef.class.getResourceAsStream(file);
      reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.equals("")) {
          list.add(line);
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (is != null) {
          is.close();
        }
        if (reader != null) {
          reader.close();
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  static void init() {

    Map<String, String> defaultKey = new LinkedHashMap<>();

    for (String txt : Arrays.asList("/cmd/BASE.txt", "/cmd/COMMAND1.txt")) {
      for (String configItem : load(txt)) {
        String[] split = configItem.split(" ");
        String key = split[0];
        String value = split[1];
        defaultKey.put(key, value);
      }
    }

    TypedHandler.modeMap.put(EditorMode.CMD, defaultKey);


    Map<String, String> COMMAND2 = new LinkedHashMap<>();

    for (String txt : Arrays.asList("/cmd/BASE.txt", "/cmd/COMMAND2.txt")) {
      for (String configItem : load(txt)) {
        String[] split = configItem.split(" ");
        String key = split[0];
        String value = split[1];
        COMMAND2.put(key, value);
      }
    }

    //y
    TypedHandler.modeMap.put(EditorMode.MOVE, COMMAND2);


    Map<String, String> COMMAND3 = new LinkedHashMap<>();


    for (String txt : Arrays.asList("/cmd/BASE.txt", "/cmd/COMMAND3.txt")) {
      for (String configItem : load(txt)) {
        String[] split = configItem.split(" ");
        String key = split[0];
        String value = split[1];
        COMMAND3.put(key, value);
      }
    }

    //S
    TypedHandler.modeMap.put(EditorMode.SELECT, COMMAND3);

  }

}
