/*
問題文
高橋学級には N 人の生徒がいます。 生徒は 1 から N まで出席番号が振られています。 i 番目の生徒の身長は ai です。 ai はすべて相異なります。

高橋先生は N 人の生徒を背の高い方から順に並べました。 N 人の生徒の出席番号を背の高い方から順に出力してください。

制約
2≦N≦105
ai は整数である。
1≦ai≦109
ai はすべて相異なる。
部分点
30 点分のテストケースでは、N≦1000 を満たす。

http://abc041.contest.atcoder.jp/tasks/abc041_c
*/


import java.io.*;

class Student {
  int index;
  int height;

  public Student(int index, int height) {
    this.index = index;
    this.height = height;
  }

  public int getIndex() {
    return this.index + 1;
  }

  public int getHeight() {
    return this.height;
  }
}

class OrderInBack {
  public static void main(String[] args) throws IOException {
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);  
    
    int cout = Integer.parseInt(br.readLine());
    String[] nums = br.readLine().split(" ", 0);

    Student[] students = new Student[cout];
    for (int i = 0; i < cout; i++) {
      Student std = new Student(i, Integer.parseInt(nums[i]));
      students[i] = std;
    }

    mergeSort(students);
    printStudentIndex(students);
  }

  public static void mergeSort(Student[] arr) {
    
    if (arr.length > 1) {
      int leftCout = arr.length / 2;
      int rightCout = arr.length - leftCout;

      Student[] leftArr = new Student[leftCout];
      Student[] rightArr = new Student[rightCout];

      for (int i = 0; i < leftCout; i++) {
        leftArr[i] = arr[i];
      }
      for (int i = 0; i < rightCout; i++) {
        rightArr[i] = arr[i + leftCout];
      }

      mergeSort(leftArr);
      mergeSort(rightArr);

      merge(leftArr, rightArr, arr);
    }
  }

  public static void merge(Student[] leftArr, Student[] rightArr, Student[] arr) {
    int i = 0;
    int j = 0;

    while (leftArr.length > i && rightArr.length > j) {
      if (leftArr[i].getHeight() < rightArr[j].getHeight()) {
        arr[i + j] = rightArr[j];
        j++;
      } else {
        arr[i + j] = leftArr[i];
        i++;
      }
    }

    while (leftArr.length > i) {
      arr[i + j] = leftArr[i];
      i++;
    }

    while (rightArr.length > j) {
      arr[i + j] = rightArr[j];
      j++;
    }

  }

  public static void printStudentIndex(Student[] arr) {
    System.out.println();
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i].getIndex());
    }
  }
}
