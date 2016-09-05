*/
問題文
http://abc035.contest.atcoder.jp/tasks/abc035_c
黒の面に0、白の面に1が書かれた N 個のオセロの駒が、どの駒も黒の面が上を向くように一列に並べられています。その後、ある区間にある駒を全て裏返すという操作が Q 回だけ行なわれました。 具体的には i 回目の操作においては、左から li 番目の駒から ri 番目の駒までの駒全てが裏返されました。

最終的な盤面を求めてください。


解説
http://www.slideshare.net/chokudai/abc035
/*

import java.util.Scanner;

class Hand {
  int fromId;
  int toId;

  public Hand(int fromId, int toId) {
    this.fromId = fromId;
    this.toId = toId;
  }
}

class OthelloRecerse {
  Hand[] hands;
  int size;
  int[] pices;

  public OthelloRecerse(Hand[] hands, int size) {
    this.hands = hands;
    this.size = size;
    this.pices = new int[size];
  }

  //解答
  public void reversePices() {
    // いもす法
    // 加算
    for (int i = 0; i < hands.length; i++) {
      pices[hands[i].fromId - 1] += 1;
      if (hands[i].toId < pices.length - 1) {
        pices[hands[i].toId] -= 1;
      }
    }
    
    // 累積和を取る
    for (int i = 0; i < size - 1; i++) {
      pices[i + 1] += pices[i];
    }

    //プリント
    System.out.println("=========");
    for (int i = 0; i < size; i++) {
      if (pices[i] % 2 == 0) {
        System.out.print("0");
      } else {
        System.out.print("1");
      }
    }
    System.out.println();
  }
}

class Othello {
  public static void main(String[] args) {
    OthelloRecerse othello = input();
    othello.reversePices(); 
  }


  public static OthelloRecerse input() {

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
    System.out.println(inputText);

    int size = Integer.parseInt(inputText[0].split(" ")[0]);
    Hand[] hands = new Hand[Integer.parseInt(inputText[0].split(" ")[1])];

    for (int i = 1; i < inputText.length; i++) {
      int fromId = Integer.parseInt(inputText[i].split(" ")[0]);
      int toId = Integer.parseInt(inputText[i].split(" ")[1]);
      Hand hand = new Hand(fromId, toId);
      hands[i-1] = hand;
    }

    OthelloRecerse othello = new OthelloRecerse(hands, size);
    return othello;
  }
}
