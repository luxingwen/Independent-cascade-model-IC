package com.yee;

import java.io.IOException;
import java.util.*;

public class Main {



    public static void main(String[] args) throws IOException {

        Graph graph = new Graph();

        ReadFile r = new ReadFile();
        // 添加顶点
        r.loadVertex("/Users/luxingwen/Lewis/helix/huang/src/com/yee/alluserlist.txt").forEach(itemNode -> graph.addVertex(itemNode));
        // 添加与顶点相连的边
        r.loadData("/Users/luxingwen/Lewis/helix/huang/src/com/yee/links.txt").forEach((itemNode, edge) -> graph.addEdge(itemNode, edge));

        // 打印图信息
        graph.printGraph();

        System.out.println("start-----> 当前时间戳:"+System.currentTimeMillis()/1000);
        maxsubNodes(graph, 10);
        System.out.println("end");
    }


    // 单次扩散
    // @Param
    // graph 图信息
    // vertexList  已经激活的节点列表
    public static Map<Integer, Integer> singleDiffusion(Graph graph, List<Vertex> vertexList) {
        Random ran = new Random();
        Set<Vertex> active = new HashSet(); //存储被激活的节点
        Stack<Vertex> target = new Stack<Vertex>(); // 存储目标被激活节点
        Map<Integer, Integer> res = new HashMap<Integer, Integer>();//
        //
        for(int i = 0 ;i <vertexList.size(); i++){
            Vertex vertex = vertexList.get(i);
            target.push(vertex);
            while(target.size()>0){
                Vertex vertex1 = target.pop();
                active.add(vertex1);// 添加激活节点
                // 与它相连的节点
                List<Edge> listEdge  = graph.getG().get(vertex1);

                // 遍历与它相连的节点
                for(int j = 0; j < listEdge.size(); j++){
                    double randnum = ran.nextDouble();
                    Edge e = listEdge.get(j);
                    // 如果 有概率被激活
                    if(randnum <= e.getWeight()){
                        if(!active.contains(e.getVertex())){
                            // 添加被激活目标节点
                            target.push(e.getVertex());
                        }
                    }
                }

            }
            res.put(new Integer(res.size()+1), new Integer(active.size()));
        }
        return res;
    }


    // 计算平均值
    // @Param
    // graph 图信息
    // list 已经被激活的列表
    public static double calculAvg(Graph graph, List list){
        List<Map<Integer, Integer>>  results =new ArrayList<Map<Integer, Integer>>();
        double[] avg = new double[list.size()];
        int seedNum= 5000;// 模拟5k次
        for(int i = 0; i < seedNum; i++) {
            results.add(singleDiffusion(graph, list));
            for (int j = 0; j < list.size(); j++) {
                avg[j] += results.get(i).get(j + 1);
            }
        }
        for(int i = 0; i < list.size(); i++){
            avg[i] = avg[i] / seedNum;
        }
        return avg[list.size()-1];
    }

    // 最大子节点
    public static void maxsubNodes(Graph graph, int k){

        List<Vertex> seedNodes = new ArrayList<Vertex>(); // 存储最大子节点集合
        Map<Vertex, Boolean>  signVertex = new HashMap<Vertex, Boolean>(); // 标记已经加载进来的节点

        for(int i = 0; i < k; i++){
            System.out.println("第"+(i+1)+"轮, time:"+System.currentTimeMillis()/1000);
            Map<Integer, Vertex> maxMap = new HashMap<Integer, Vertex>();
            Integer maxIndex = new Integer(0);
            Vertex maxVex = new Vertex("null"); // 最大子节点
            double maxVal = 0;
            Map<Vertex, List<Edge>> graphs = graph.getG();
            for(Vertex vertex : graphs.keySet()){ // 遍历所有节点
                if(signVertex.containsKey(vertex)){// 排查已经获取到的节点
                    continue;
                }
                List<Vertex> list = new ArrayList<Vertex>();//
                list.addAll(seedNodes);
                list.add(vertex);
                double value = calculAvg(graph, list);//刚刚加入的节点的值

                if (maxVal < value){// 找到最大值的节点
                    maxVal = value;
                    maxVex = vertex;
                }
            }
            seedNodes.add(maxVex);
            signVertex.put(maxVex, new Boolean(true));
        }

        for(int i = 0; i<seedNodes.size(); i++){
            Vertex v = seedNodes.get(i);
            System.out.println(v.label);
        }
    }
}
