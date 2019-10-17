package com.yee;

import java.io.*;
import java.util.*;

public class ReadFile {

    public List<String> loadVertex(String filename) throws IOException {
        FileInputStream in = new FileInputStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String lineTxt = null;
        List list = new ArrayList();
        while ((lineTxt = bufferedReader.readLine()) != null) {
            list.add(lineTxt);
        }
        bufferedReader.close();
        in.close();
        return list;
    }

    public Map<String, Edge> loadData(String filename) throws IOException {
        FileInputStream in = new FileInputStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String lineTxt = null;
        Map<String, Edge> mdata = new HashMap<String, Edge>();

        while ((lineTxt = bufferedReader.readLine()) != null) {
            String [] lines =  lineTxt.split(" ");
            mdata.put(lines[0], new Edge(new Vertex(lines[1]), Double.parseDouble(lines[2])));
        }
        bufferedReader.close();
        in.close();
        return mdata;
    }


}
