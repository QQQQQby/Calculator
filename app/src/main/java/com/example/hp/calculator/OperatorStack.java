package com.example.hp.calculator;

/**
 * Created by hp on 2018/3/21.
 */

public class OperatorStack{
    private char[]oper;
    private int maxLength;
    private int top;
    private final int DEFAULT_LENGTH=50;				        //默认长度

    public OperatorStack() {
        this.oper=new char[DEFAULT_LENGTH];
        this.maxLength=DEFAULT_LENGTH;
        this.top=-1;
    }

    public OperatorStack(int maxLength){
        this.oper=new char[maxLength];
        this.maxLength=maxLength;
        this.top=-1;
    }

    public boolean push(char oper){							//判断是否成功将元素压入栈
        if(top==maxLength-1){
            System.out.println("The stack is full.");
            return false;
        }

        this.top++;
        this.oper[this.top]=oper;
        return true;
    }

    public boolean isEmpty(){								    //判断栈是否为空
        if(top==-1){
            return true;
        }
        else
            return false;
    }

    public char getTop(){									    //取栈顶元素
        if(!this.isEmpty())
            return this.oper[this.top];
        return '\0';
    }

    public boolean pop(){									    //判断是否成功出栈
        if(this.isEmpty())
            return false;
        this.oper[top]='\0';
        this.top--;
        return true;
    }

    public boolean destroy(){								    //判断是否销毁栈
        if(this.isEmpty())
            return false;
        this.maxLength=0;
        this.top=-1;
        this.oper=null;
        return true;
    }

    public boolean print(){
        if(this.isEmpty())
            return false;
        System.out.print("The Stack is ");
        for (int i = 0; i <= this.top; i++) {
            System.out.print(this.oper[i]+" ");
        }
        System.out.println();
        return true;
    }

    public String toReversePolishExpression(String exp){
        char[]resultExp=new char[DEFAULT_LENGTH];
        int last=-1;
        boolean isNum=false;
        int[]pri=new int[48];
        pri['*']=pri['/']=2;
        pri['+']=pri['-']=1;
        pri['#']=0;
        this.push('#');
        for (int i = 0; i < exp.length(); i++) {
            switch (exp.charAt(i)) {
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                case '.':
                    if(!isNum){
                        last++;
                        resultExp[last]=' ';
                    }
                    isNum=true;
                    last++;
                    resultExp[last]=exp.charAt(i);
                    break;
                case '(':
                    isNum=false;
                    this.push(exp.charAt(i));
                    break;
                case ')':
                    isNum=false;
                    while (this.getTop()!='('){
                        last++;
                        resultExp[last]=this.getTop();
                        this.pop();
                    }
                    this.pop();
                    break;

                case '*':
                case '/':
                case '+':
                case '-':
                    isNum=false;
                    while(pri[this.getTop()]>=pri[exp.charAt(i)]&&this.getTop()!='('){
                        last++;
                        resultExp[last]=this.getTop();
                        this.pop();
                    }
                    push(exp.charAt(i));
                    break;

                case ' ':
                    isNum=false;
                    continue;
                default:
                    return "#";
            }
        }
        while (!this.isEmpty()) {
            last++;
            resultExp[last]=this.getTop();
            this.pop();
        }
        return String.valueOf(resultExp).replace("\0","");
    }

}
