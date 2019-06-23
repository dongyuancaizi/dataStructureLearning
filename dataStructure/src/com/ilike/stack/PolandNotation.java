package com.ilike.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰（后缀）表达式计算器
 */
public class PolandNotation {

    public static void main(String[] args) {
        //完成将中缀表达式转为后缀表达式
        //说明：
        //1. 1+((2+3)*4)-5 转成 1 2 3 + 4 * + 5 -
        //2.直接对一个字符串进行操作不方便，现将"1+((2+3)*4)-5"字符串转成一个中缀的list
        // 即 “1+((2+3)*4)-5” =》ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式的list转成后缀表达式对应的list
        // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] =》ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfexExpressionList(expression);
        System.out.println("中缀表达式："+list);
        List<String> list1 = parseSuffexExpressionList(list);
        System.out.println("后缀表达式："+list1);
        int result = calculate(list1);
        System.out.println(result);
    }

    /**
     * 将得到的中缀表达式的list转成后缀表达式对应的list
     */
    public static List<String> parseSuffexExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明s2在整个转换过程中没有pop操作,而且后面我们换需要逆序输出
        //比较麻烦，这里我们就不适用Stack<String> s2=new Stack<>()而适用List<String> s2
        // Stack<String> s2=new Stack<>();//储存中间结果的栈
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数，就入栈
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2,知道遇到左括号为止，此时将一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //将“（”弹出s1,消除小括号
                s1.pop();
            } else {
                //当item的优先级小于等于s1栈顶运算符的优先级，将s1栈顶的运算符弹出并加入到s2,再次转到(4.1)与s1中新的栈顶元素进行比较
                //我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
                    s2.add(s1.pop());
                }
                //换需要将item压入栈顶
                s1.push(item);
            }
        }
        //将s1中剩余的运算符加入到s2中
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        //因为是存放到list，因此顺序输出就是对应的结果
        return s2;
    }

    /**
     * 将中缀表达式转换成list
     */
    public static List<String> toInfexExpressionList(String s) {
        //定义一个list,存放中缀表达式对应的数据
        List<String> ls = new ArrayList<>();
        //这是一个指针，用于遍历中缀表达式字符串
        int i = 0;
        //用来做多位数的拼接
        String str = " ";
        //每遍历到一个字符就放入到c
        char c = ' ';
        do {
            //如果c是非数字，直接加到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(c + "");
                i++;
            } else {
                //是数字,需要考虑多位数的问题
                //现将str置成""
                str = "";
                while (i < s.length() && ((c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57)) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 测试逆波兰表达式
     */
    private static void test01() {
        //先定义一个逆波兰表达式
        //(3+4)*5-6  => 3 4 + 5 * 6 -
        //为了方便。数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        //4 * 5 -8 +60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        String suffixExpression1 = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.现将3 4 + 5 * 6 -放到一个ArrayList中
        //2.将ArrayList传递给一个方法，遍历 配合栈，完成计算
        List<String> list = getListString(suffixExpression1);
        System.out.println(list);
        int res = calculate(list);
        System.out.println(res);
    }

    /**
     * 将一个逆波兰表达式，依次将数字和字符放入到ArrayList中
     */
    public static List<String> getListString(String suffixExpression) {
        //1.将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的计算
     */
    public static int calculate(List<String> ls) {
        //创建栈，只需要一个即可
        Stack<String> stack = new Stack<>();
        //遍历lis
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        //最后留在栈中的就是运算的结果
        return Integer.parseInt(stack.pop());
    }
}

/**
 * 创建类Operation ,可以返回一个符号对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /*
     *写一个方法返回对应的优先级
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}