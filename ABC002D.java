
'''
import java.util.Scanner;
import java.util.LinkedList;

public class ABC002D {
	public static void main(String[] args) {
		Faction f = new Faction();
		f.run();
	}
}

class Faction {
	
	int N;
	int M;
	int[][] adj;
	int ans;
	
	public Faction() {
		Scanner cin = new Scanner(System.in);
		this.N = cin.nextInt();
		this.M = cin.nextInt();
		
		this.adj = new int[N][N];
		for (int i = 0; i < M; i++) {
			int x = cin.nextInt() - 1;
			int y = cin.nextInt() - 1;
			adj[x][y] = 1;
			adj[y][x] = 1;
		}
		this.ans = 0;
	}
	
	void run() {
		for (int i = 0; i < 1<<N; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			
			//フラグが立っている要素だけリストに追加
			int count = 0;
			for (int j = 0; j < N; j++) {
				if ((1 & i >> j) == 1) { 
					list.add(j);
					count++;
				}
			}
			
			//リスト内の要素がそれぞれ知り合いか確認
			int listSize = list.size(); 
			breakLabel: for (int k = 0; k < listSize; k++) {
				for (int s = k + 1; s < listSize; s++) {
					if (adj[list.get(k)][list.get(s)] == 0) {
						count = 0;
						break breakLabel;
					}
				}
			}
			ans = Math.max(ans, count);
		}
		System.out.println(ans);
	}
}
'''
