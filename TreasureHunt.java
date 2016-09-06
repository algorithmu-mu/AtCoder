*/
問題: http://abc035.contest.atcoder.jp/tasks/abc035_d
解説: http://www.slideshare.net/chokudai/abc035

ポイント
ダイクストラ法を用いた最短経路探索
/*

import java.util.ArrayList;
import java.util.Scanner;

class Graph {
  int[][] adj; //格納する値はノード間の距離(時間)
  Node[] nodes;
  int limitTime;

  public Graph(int[][] adj, Node[] nodes, int limitTime) {
    this.adj = adj;
    this.nodes = nodes;
    this.limitTime = limitTime;
  }

  //ダイクストラ法を使ってスタートノードからの最短距離を記憶
  public void disjkstra(int startId) {
    makeAllNodesUnConfirm();
    nodes[startId].minDistance = 0;
    int pivotId = startId;

    while (pivotId >= 0) {
      nodes[pivotId].makeConfrim();
      ArrayList<Integer> linkedNodeIds = getIdsLinkedFrom(pivotId);
      updateMinDistance(pivotId, linkedNodeIds);
      pivotId = getMinDistanceNodeId();  
    }
  }

  //引数に指定したノードと繋がっているノードを取得
  public ArrayList<Integer> getIdsLinkedFrom(int fromId) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < nodes.length; i++) {
      if (adj[fromId][i] > 0 && nodes[i].isNotConfrim()) {
        list.add(i);
      }
    }
    return list;
  }

  //２つのノード間の距離を取得
  public int getDistance(int fromId, int toId) {
    return adj[fromId][toId];
  }

  //未確定のノードから最も距離が小さいノードを取得
  public int getMinDistanceNodeId() {
    int minId = -1;
    for (int i = 0; i < nodes.length; i++) {
      if (nodes[i].isNotConfrim()) {
        minId = i;
        break;
      }
    }

    if (minId == -1) return minId;

    for (int i = minId + 1; i < nodes.length; i++) {
      if (nodes[i].isNotConfrim() && nodes[minId].minDistance > nodes[i].minDistance) {
        minId = i;
      }
    }
    return minId;
  }

  //全てのノードを未確定にする
  public void makeAllNodesUnConfirm() {
    for (Node node: nodes) {
      node.makeUnConfrim();
    }
  }

  public void updateMinDistance(int pivotId, ArrayList<Integer> ids) {
    for (int id: ids) {
      Node pivot = nodes[pivotId];
      Node node = nodes[id];
      int distance = getDistance(pivotId, id) + pivot.minDistance;
      if (node.minDistance > distance) {
        node.minDistance = distance;
      }
    }
  }

  //隣接行列の有向辺を逆向きにする
  public int[][] getReverseAdj() {
    int[][] reverseAdj = new int[adj.length][adj.length];
    for (int i = 0; i < adj.length; i++) {
      for (int j = 0; j < adj.length; j++) {
        reverseAdj[i][j] = adj[j][i];
      }
    }
    return reverseAdj;
  }

  public Node[] getResetMinDistanceNodes() {
    Node[] copyNodes = new Node[nodes.length];
    for (int i = 0; i < nodes.length; i++) {
      copyNodes[i] = new Node(nodes[i].value);
    }
    return copyNodes;
  }

}

class Node {
  boolean confirm;
  int minDistance;
  int value;

  public Node(int value) {
    this.value = value;
    this.minDistance = Integer.MAX_VALUE;
  }

  public boolean isNotConfrim() {
    return !confirm;
  }

  public void makeConfrim() {
    confirm = true;
  }

  public void makeUnConfrim() {
    confirm = false;
  }
}

class GraphCreater {
  public Graph createGraph(String[] inputs) {

    int nodeCount = Integer.valueOf(inputs[0].split(" ")[0]);
    int edgeCount = Integer.valueOf(inputs[0].split(" ")[1]);
    int limitTime = Integer.valueOf(inputs[0].split(" ")[2]);

    String[] nodeValues = inputs[1].split(" ");

    //ノード作成
    Node[] nodes = new Node[nodeCount];
    for (int i = 0; i < nodes.length; i++) {
      int value = Integer.valueOf(nodeValues[i]);
      nodes[i] = new Node(value);
    }

    //有向辺の情報を作成
    int[][] adj = new int[nodeCount][nodeCount];
    for (int i = 2; i < edgeCount + 2; i++) {
      int fromId = Integer.valueOf(inputs[i].split(" ")[0]);
      int toId = Integer.valueOf(inputs[i].split(" ")[1]);
      int distance = Integer.valueOf(inputs[i].split(" ")[2]);
      adj[fromId - 1][toId - 1] = distance;
    }

    Graph graph = new Graph(adj, nodes, limitTime);


    //グラフ情報を出力
    System.out.println("====adj====");
    int count = graph.nodes.length;
    for (int i = 0; i < count; i++) {
      for (int j = 0; j < count; j++) {
        System.out.print(adj[i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("====node value====");
    for (Node node: graph.nodes) {
      System.out.print(node.value + " ");
    }
    System.out.println();

    System.out.println("====limit time====");
    System.out.println(graph.limitTime);

    return graph;
  }
}

class TreasureHunt {
  public static void main(String[] args) {
    GraphCreater graphCreater = new GraphCreater();
    Graph graph = graphCreater.createGraph(input());
    graph.disjkstra(0);

    Node[] copyNodes = graph.getResetMinDistanceNodes();
    Graph reverseGraph = new Graph(graph.getReverseAdj(), copyNodes, graph.limitTime);
    reverseGraph.disjkstra(0);

    //お金計算
    int[] money = new int[graph.nodes.length];
    for (int i = 0; i < graph.nodes.length; i++) {
      int goingTime = graph.nodes[i].minDistance;
      int returnTime = reverseGraph.nodes[i].minDistance;
      int stayTime = graph.limitTime - (goingTime + returnTime);
      money[i] = graph.nodes[i].value * stayTime;
    }

    //お金出力
    System.out.println("=====お金=====");
    for (int m : money) {
      System.out.print(m + " ");
    }
    System.out.println();
  }

  public static String[] input() {
    Scanner in = new Scanner(System.in);
    String str = new String();
    while(in.hasNext()) {
      String buf = in.nextLine();
      if (buf.matches("EOS")) {
        break;
      }
      str = str + buf + ",";
    }
    String[] inputText = str.split(",");
    return inputText;
  }

  
}








