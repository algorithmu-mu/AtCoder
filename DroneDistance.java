/*
問題文
無限に広い二次元グリッドの原点 (0,0) に高橋君と 1 台のドローンがいます。このドローンは文字列が与えられた時、文字列の先頭から末尾までのそれぞれの文字を 1 つの命令と解釈して順に実行します。命令は以下の 4 種類です。

L 現在のドローンの位置を (x,y) として (x−1,y) に移動する
R 現在のドローンの位置を (x,y) として (x+1,y) に移動する
U 現在のドローンの位置を (x,y) として (x,y+1) に移動する
D 現在のドローンの位置を (x,y) として (x,y−1) に移動する
今、ドローンに何らかの命令が与えられ、どこかへと移動しました。高橋君はドローンに送られた命令を表す文字列である S を手に入れたものの、いくつかの箇所は破損し?になり分からなくなってしまいました。ただし、?が元々はL、R、U、Dのいずれかの文字だったことが分かっています。

ドローンと高橋君の距離はドローンの位置を (x,y) としてマンハッタン距離 |x|+|y| で表されます。求める値の種類を表す整数 T が与えられるので、移動を終えたあとのドローンと高橋君の距離としてありうる値のうち、 T=1 ならば最大値を、 T=2 ならば最小値を求めてください。

*/



import java.util.LinkedList;

class Drone {
  int x;
  int y;
  int kuesshonCount;

  public Drone(int x, int y) {
    this.x = x;
    this.y = y;
    this.kuesshonCount = 0;
  }

  public int distance(String str, int t) {
    //文字列をchar型の配列に変換
    char[] charArr = str.toCharArray();
    LinkedList<String> list = new LinkedList<String>();
    for (char ch: charArr) {
      if (ch == '?') {
        kuesshonCount++;
      } else {
        list.add(String.valueOf(ch));
      }
    }
    
    for (String s: list) {
      move(s);
    }

    if (t == 1) {
      return Math.abs(getDistance() + kuesshonCount);
    } else {
      return Math.abs(getDistance() - kuesshonCount);
    }
  }

  public void move(String str) {
    switch(str) {
      case "U":
        this.y++;
        break;
      case "R":
        this.x++;
        break;
      case "D":
        this.y--;
        break;
      case "L":
        this.x--;
        break;
      default:
        System.out.println("other word");
    }
  }

  public int getDistance() {
    return Math.abs(x) + Math.abs(y);
  }
}


class DroneDistance {

  public static void main(String[] args) {
    Drone drone = new Drone(0, 0);
    int distance = drone.distance("UULL?", 2);
    System.out.println(distance);
  }

}
