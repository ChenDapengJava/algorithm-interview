package com.xblzer.algorithm.stackandqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 牛客网在线编辑版
 *
 * 输入描述：
 * 第一行输入一个整数N，表示对栈进行的操作总数。
 * 下面N行每行输入一个字符串S，表示操作的种类。
 * 如果S为"push"，则后面还有一个整数X表示向栈里压入整数X。
 * 如果S为"pop"，则表示弹出栈顶操作。
 * 如果S为"getMin"，则表示询问当前栈中的最小元素是多少。
 * 输出描述：对于每个getMin操作，输出一行表示当前栈中的最小元素是多少。
 *
 * @author 行百里者
 * @date 2022-07-14 18:51
 */
public class SpecialStack {
    public static void main(String[] args) {
        SpecialStack specialStack = new SpecialStack();

        List<Integer> resultList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        //接收键盘输入
        if (scanner.hasNext()) {
            int N = scanner.nextInt();
            Scanner commandScanner = new Scanner(System.in);
            while (N > 0 && commandScanner.hasNextLine()) {
                String s = commandScanner.nextLine();
                if (s.startsWith("push")) {
                    int newNum = Integer.parseInt(s.split(" ")[1]);
                    specialStack.push(newNum);
                } else {
                    if (s.startsWith("pop")) {
                        specialStack.pop();
                    }
                    if (s.equals("getMin")) {
                        resultList.add(specialStack.getMin());
                    }
                }
                N--;
            }
            commandScanner.close();
        }
        scanner.close();
        resultList.forEach(System.out::println);
    }

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public SpecialStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (stackMin.isEmpty()) {
            stackMin.push(newNum);
        } else if (newNum < this.getMin()) {
            stackMin.push(newNum);
        }
        stackData.push(newNum);
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("栈空，无法弹出元素");
        }

        int value = stackData.pop();
        if (value == this.getMin()) {
            stackMin.pop();
        }
        return value;
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("栈空，不能获取最小元素");
        }
        return stackMin.peek();
    }

}
