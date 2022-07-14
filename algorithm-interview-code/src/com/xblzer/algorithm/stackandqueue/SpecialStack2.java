package com.xblzer.algorithm.stackandqueue;

import java.util.Stack;

/**
 * 实现一个特殊的栈，除了push、pop外，还能够返回栈中的最小元素
 * 可以使用现有的栈结构
 * 方案二
 * @author 行百里者
 * @date 2022-07-14 15:31
 */
public class SpecialStack2 {
    /**
     * 数据操作的栈
     */
    private Stack<Integer> stackData;

    /**
     * 最小值栈
     */
    private Stack<Integer> stackMin;

    public SpecialStack2() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    /**
     * 实现压栈的方法
     * @param num 需要入栈的数
     */
    public void push(int num) {
        if (stackMin.isEmpty()) {
            stackMin.push(num);
        } else if (num <= this.getMin()) {
            //如果待添加的元素比栈的最小元素还小，则将该元素放到最小值栈的栈顶
            stackMin.push(num);
        } else {
            //如果num大于栈的最小元素，则重复压入最小元素到最小值栈的栈顶
            stackMin.push(this.getMin());
        }
        //数据栈正常压入
        stackData.push(num);
    }

    /**
     * 实现出栈方法
     * @return 栈顶元素
     */
    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("栈空！");
        }
        //最小值栈和数据栈元素个数一致，stackData弹出时，stackMin也需要弹出。这并不影响获取栈的最小值
        stackMin.pop();
        return stackData.pop();
    }

    /**
     * 实现获取栈中的最小元素
     * @return 栈的最小元素
     */
    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("栈空！");
        }
        return stackMin.peek();
    }

    public static void main(String[] args) {
        SpecialStack2 specialStack = new SpecialStack2();
        specialStack.push(2);
        specialStack.push(2);
        specialStack.push(5);
        specialStack.push(1);
        specialStack.push(4);
        specialStack.push(3);
        specialStack.push(1);
        System.out.println(specialStack.getMin());
        int i = specialStack.pop();
        System.out.println(i);
    }
}
