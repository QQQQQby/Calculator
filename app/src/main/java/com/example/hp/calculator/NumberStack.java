package com.example.hp.calculator;

/**
 * Created by hp on 2018/3/21.
 */


public class NumberStack {
    private double num[];
    private int maxLength;
    private int top;
    private final int DEFAULT_LENGTH=1000;					//默认长度

    public NumberStack(){
        this.num=new double[DEFAULT_LENGTH];
        this.maxLength=DEFAULT_LENGTH;
        this.top=-1;
    }

    public NumberStack(int maxLength){
        this.num=new double[maxLength];
        this.maxLength=maxLength;
        this.top=-1;
    }

    public boolean push(double Num){						//判断是否成功将元素压入栈
        if(top==maxLength-1){
            System.out.println("The stack is full.");
            return false;
        }
        this.top++;
        this.num[this.top]=Num;
        return true;
    }

    public boolean isEmpty(){								//判断栈是否为空
        if(top==-1)
            return true;
        else
            return false;
    }

    public double getTop(){									//取栈顶元素
        if(!this.isEmpty()){
            return this.num[this.top];
        }
        return 0;
    }

    public boolean pop(){									//判断是否成功出栈
        if(this.isEmpty())
            return false;
        this.num[this.top]=0;
        this.top--;
        return true;
    }

    public boolean destroy(){								//判断是否销毁栈
        if(this.isEmpty())
            return false;
        this.maxLength=0;
        this.top=-1;
        this.num=null;
        return true;
    }

    public boolean print(){
        if(this.isEmpty())
            return false;
        System.out.print("The Stack is ");
        for (int i = 0; i <= this.top; i++) {
            System.out.print(this.num[i]+" ");
        }
        System.out.println();
        return true;
    }

    public double calculate(String exp){
        if(exp.isEmpty()){
            System.out.println("The expression is empty.");
            return 0;
        }
        exp=exp.replace("#", "");
        this.destroy();
        this.maxLength=DEFAULT_LENGTH;
        this.num=new double[DEFAULT_LENGTH];
        boolean isNum=false,isFloat=false;
        double num=0,lNum,rNum,res;
        int f=10;
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
                    if(!isFloat)
                        num=num*10+Double.parseDouble(""+exp.charAt(i));
                    else{
                        num=num+Double.parseDouble(""+exp.charAt(i))/f;
                        f*=10;
                    }
                    isNum=true;
                    break;
                case ' ':
                    f=10;
                    isFloat=false;
                    if(isNum)
                        this.push(num);
                    isNum=false;
                    num=0;
                    break;
                case '+':
                case '/':
                case '-':
                case '*':
                    f=10;
                    isFloat=false;
                    if(isNum)
                        this.push(num);
                    isNum=false;
                    num=0;
                    rNum=this.getTop();
                    this.pop();
                    lNum=this.getTop();
                    this.pop();
                    res=oper(lNum,rNum,exp.charAt(i));
                    this.push(res);
                    break;
                case '.':
                    isFloat=true;
                    break;
                default:
                    System.out.println("Not a expression.");
                    return 0;
            }
        }
        if(isNum)
            this.push(num);
        if(this.top>0){
            System.out.println("Wrong expression.");
            return 0;
        }
        return this.getTop();
    }

    private double oper(double lNum, double rNum,char o) {
        switch (o) {
            case '*':
                return lNum*rNum;
            case '/':
                return lNum/rNum;
            case '+':
                return lNum+rNum;
            case '-':
                return lNum-rNum;
            default:
                return 0.0;
        }
    }


}