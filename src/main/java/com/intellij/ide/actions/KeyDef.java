package com.intellij.ide.actions;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class KeyDef {

    static void init() {

        Map<String, String> defaultKey = new LinkedHashMap<>();

        for (String txt : Arrays.asList("/cmd/BASE.txt", "/cmd/COMMAND1.txt", "/cmd/HJKL.txt", "/cmd/Refactor.txt", "/cmd/NAV.txt","/cmd/OO.txt")) {
            for (String configItem : FileLoader.load(txt)) {
                String[] split = configItem.split(" ");
                String key = split[0];
                String value = split[1];
                defaultKey.put(key, value);
            }
        }

        TypedHandler.modeMap.put(EditorMode.CMD, defaultKey);


        Map<String, String> COMMAND2 = new LinkedHashMap<>();

        for (String txt : Arrays.asList("/cmd/BASE.txt", "/cmd/COMMAND2.txt")) {
            for (String configItem : FileLoader.load(txt)) {
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
            for (String configItem : FileLoader.load(txt)) {
                String[] split = configItem.split(" ");
                String key = split[0];
                String value = split[1];
                COMMAND3.put(key, value);
            }
        }

        //I
        TypedHandler.modeMap.put(EditorMode.SELECT, COMMAND3);

    }

}
