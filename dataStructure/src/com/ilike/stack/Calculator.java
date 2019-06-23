package com.ilike.stack;

/**
 * 计算器
 */
public class Calculator {

    public static void main(String[] args) {
       String expression="700+2*6-4";
       //先创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量，用于扫描
        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        //将每次扫描到的char保存到ch中
        char ch=' ';
        //用于拼接多位数
        String keepNum="";
        //用while循环扫描expression
      while (true){
          //依次得到expression中的每一个字符
          ch=expression.substring(index,index+1).charAt(0);
          //判断ch是什么，然后做相应的处理
          if(operStack.isOper(ch)){//如果是运算符
              //判断当前的符号栈是否为空
              if(!operStack.isEmpty()){
                  //如果当前的操作符优先级小于或者等于栈中的操作符优先级，就从数栈中pop出两个数字，
                  // 从符号栈中pop一个操作符进行运算，得到结果，入数栈，然后将当前的符号入符号栈
                  if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                      num1=numStack.pop();
                      num2=numStack.pop();
                      oper=operStack.pop();
                      //计算
                      int cal = numStack.cal(num1, num2, (char) oper);
                      //运算结果入数栈
                      numStack.push(cal);
                      //当前的操作符入符号栈
                      operStack.push(ch);
                  }else{
                      //如果当前符号的优先级大于栈中符号的优先级，直接入栈
                      operStack.push(ch);
                  }
              }else{
                  //直接入栈
                  operStack.push(ch);
              }
          }else{
              //如果是数字，直接入数栈
              //numStack.push(ch-48);
              //分析思路
              //1.当处理多位数时，不能发现是一个数就立即入栈，因为他有可能是个多位数
              //2.在处理数时，需要向expression的表达式index后再看一位，如果是数就进行扫描，是符号才入栈
              //3.我们需要定义一个变量，用于拼接


              //多位数处理
              keepNum +=ch;
              //如果ch已经是expression的最后一位，直接入栈
              if(index==expression.length()-1){
                  numStack.push(Integer.parseInt(keepNum));
              }else{
                  //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                  //注意，只是往后看一位，index本身的值不变
                  if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                      //如果后一位是操作符,则入栈keepNum
                      numStack.push(Integer.parseInt(keepNum));
                      //重要的！！！！，keepNum清空
                      keepNum="";
                  }
              }
          }
          //让index+1,并判断是否扫描到expression的最后一位
          index++;
          if(index>=expression.length()){
              break;
          }
      }
      //当表达式扫描完毕，就顺序的从符号栈和数栈中pop出相应的数和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，则数栈中只有一个结果
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            //计算
            int cal = numStack.cal(num1, num2, (char) oper);
            //运算结果入数栈
            numStack.push(cal);
        }
        System.out.printf("表达式的结果%d\n",numStack.pop());
    }
}

/**
 * 先创建一个栈，直接使用前面创建好的
 */
class ArrayStack2{
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组模拟栈，数据放在该数组中
     */
    private int [] stack;
    /**
     * 表示栈顶，初始化为-1
     */
    private int top=-1;

    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }

    /**
     * 增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 栈是否满
     * @return
     */
    public boolean isFull(){
        return top==maxSize-1;
    }

    /**
     * 栈是否是空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        //判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    /**
     * 出栈
     */
    public int pop(){
        //判断栈是否已空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     * 遍历栈需要从栈顶开始
     */
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }

    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示，
     * 数组越大，则优先级就越高，
     */
    public int priority(int oper){
       if(oper=='*'||oper=='/'){
           return 1;
       }else if(oper=='+'||oper=='-'){
           return 0;
       }else{
           return -1;//假定目前的表达式中只有+，-，*，/；
        }
    }

    /**
     * 判断是不是一个运算符
     */
    public boolean isOper(char oper){
      return oper=='+'||oper=='-'||oper=='*'||oper=='/';
    }
    /**
     * 计算方法
     */
    public int cal(int num1,int num2,char oper){
        int res=0;//用于存放计算结果
        switch (oper){
            case '+':
                res= num1+ num2;
                break;
            case '-':
                res= num2-num1 ;
                break;
            case '*':
                res= num1* num2;
                break;
            case '/':
                res= num2/ num1;
                break;
            default:
                    break;
        }
        return res;
    }
}