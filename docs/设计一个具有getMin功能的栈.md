#### 设计一个有 getMin 功能的栈

**题目描述**

设计一个特殊的栈，除了具有push、pop基本功能外，还能实现返回栈中最小元素的操作。

可以使用现成的栈结构。

**解析**

定义一个类，其属性包含两个栈，一个栈保存当前栈（也就是需要我们设计的栈）的元素，和普通的栈没有区别，记为 **statckData** ，另一个栈用来保存每个操作后的最小值，记为 **statckMin** 。实现方案有两种。

**方案一**

1. push方法的实现

   设当前需要压入的数据为 **num** ，先将其压入 **statckData** 栈（这一步无论怎样都是要操作的），然后判断 **statckMin** 栈是否为空。

    - 如果为空，则直接将 num 压入 stackMin。
    - 如果不为空，判断 num 和 stackMin 栈顶元素哪个值比较小，如果 num 更小则将其压入 stackMin 栈顶，否则不做操作。

   ![](https://picbed-for-typora.oss-cn-beijing.aliyuncs.com/image/image-20220714163037569.png)

2. pop方法的实现

   出栈的方法，需要返回栈顶元素的值，直接返回 **stackData** 栈的栈顶即可（如果stackData栈为空，抛出异常）。同时需要兼顾 **stackMin** 中元素的情况。

   这里已经知道了入栈时如果元素值大于 **stackMin** 栈的栈顶，是不会压入 **stackMin** 栈的，因此 **stackMin** 栈从栈底到栈顶元素是依次变小的，而且弹出的 **stackData** 栈的栈顶元素必然大于等于 **stackMin** 的栈顶元素。

   设 **stackData** 栈顶元素为 **value** ，如果 **value** 等于 **stackMin** 的栈顶元素，则 **stackMin** 栈也要弹出。

   这和 **push** 方法是相对应。

3. getMin方法的实现

   从前面的分析知道， **stackMin** 栈的栈顶元素就是我们需要的栈的最小元素。

**方案一代码实现**

```java
package com.xblzer.algorithm.stackandqueue;

import java.util.Stack;

/**
 * 实现一个特殊的栈，除了push、pop外，还能够返回栈中的最小元素
 * 可以使用现有的栈结构
 * 方案一
 * @author 行百里者
 * @date 2022-07-14 15:31
 */
public class SpecialStack1 {
    /**
     * 数据操作的栈
     */
    private Stack<Integer> stackData;

    /**
     * 最小值栈
     */
    private Stack<Integer> stackMin;

    public SpecialStack1() {
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
        int value = stackData.pop();
        //需要同步操作最小值栈
        if (value == getMin()) {
            stackMin.pop();
        }
        return value;
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
        SpecialStack1 specialStack = new SpecialStack1();
        specialStack.push(2);
        specialStack.push(5);
        specialStack.push(1);
        specialStack.push(3);
        System.out.println(specialStack.getMin());
        int i = specialStack.pop();
        System.out.println(i);
    }
}
```

**方案二**

1. push操作

   **num** 入栈时，同方案一一样，先压入 **stackData** 栈。同时拿 **num** 和 **stackMin** 栈顶元素比较，如果小于栈顶元素，则也将 **num** 压入 **stackMin** 栈，否则继续压入 **stackMin** 的栈顶元素一次，也即在 **stackMin** 的栈顶再压入一个栈顶元素。

   ![](https://picbed-for-typora.oss-cn-beijing.aliyuncs.com/image/image-20220714170813499.png)

2. pop操作

   出栈还是先弹出 **stackData** 栈顶，设其值为 **value** ，这个 **value** 就是返回值。同时 **stackMin** 也要弹出栈顶，因为 **stackMin** 和 **stackData** 的元素个数是一致的，并且 **stackData** 的栈顶一定是 **大于等于** **stackMin** 的栈顶的。所以弹出 **stackData** 的同时弹出 **stackMin** 的操作并不影响最小值元素的获取。

3. getMin操作

   **stackMin** 的栈顶元素就是最小元素。

**方案二代码实现**

```java
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
```

**小结**

方案一和方案二都是用借助两个栈来操作， **stackMin** 保存着 **stackData** 每一个操作的最小值。

这两个方案的所有操作时间复杂度都是 **O(1)** ，空间复杂度都是 **O(n)** 。

区别是，方案一 **stackMin** 的压栈稍省空间，但是弹出操作稍费时间，方案二则相反， **stackMin** 压栈稍非空间而出栈稍省时间。
