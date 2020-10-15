package com.example.mycalculater;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    结果集
    private EditText editText;

    //数字1-9
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;

    //运算符
    private  Button btn_add;// +
    private  Button btn_mis;  // -
    private  Button btn_mul;  // *
    private  Button btn_div;  // /
    private  Button btn_mod;  // %
    private  Button btn_point;  //小数点
    private  Button btn_equ;  //=

    //清除
    private  Button btn_back;
    private  Button btn_c;//clear

    boolean clear_flag;//清空标识
    private EditText et;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //数字1-9
        View btn1 = findViewById(R.id.btn_1);
        View btn2 = findViewById(R.id.btn_2);
        View btn3= findViewById(R.id.btn_3);
        View btn4 = findViewById(R.id.btn_4);
        View btn5 = findViewById(R.id.btn_5);
        View btn6 = findViewById(R.id.btn_6);
        View btn7 = findViewById(R.id.btn_7);
        View btn8 = findViewById(R.id.btn_8);
        View btn9 = findViewById(R.id.btn_9);
        View btn0 = findViewById(R.id.btn_0);
        //运算符
        View btn_add = findViewById(R.id.btn_add);// +
        View btn_mis = findViewById(R.id.btn_mis);// -
        View btn_mul = findViewById(R.id.btn_mul);// *
        View btn_div = findViewById(R.id.btn_div); // /
        View btn_mod = findViewById(R.id.btn_mod); // %
        View btn_point = findViewById(R.id.btn_point);//小数点
        View btn_equ = findViewById(R.id.btn_equ);//=
        View btn_c = findViewById(R.id.btn_c);//清空
        View btn_back = findViewById(R.id.btn_back);//回格
        View btn_more = findViewById(R.id.btn_more);//跳转

        editText = (EditText) findViewById(R.id.et);//结果集


        //添加点击事件
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btn_equ.setOnClickListener(this);
        btn_point.setOnClickListener(this);

        btn_add.setOnClickListener(this);
        btn_mis.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_mod.setOnClickListener(this);

        btn_c.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_more.setOnClickListener(this);
    }



    private double calculate(double num1,double num2,char operate){
        return 1.1;
    }

    @Override
    public void onClick(View view) {
        //获取文本内容
        String input = editText.getText().toString();
        switch (view.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if(clear_flag){
                    clear_flag = false;
                    editText.setText("");//赋值为空
                }
//                if(editText.getText().toString().substring(editText.getText().toString().length()-1).equals('0')){
 //                   editText.setText("0");break;
 //               }
                editText.setText(input + ((Button)view).getText());//结果集就为本身
                break;
            case R.id.btn_point:
                if(clear_flag){
                    clear_flag = false;
                    editText.setText("");//赋值为空
                }
                editText.setText(input + ((Button)view).getText());//结果集就为本身
                break;
            case R.id.btn_add:
            case R.id.btn_mis:
            case R.id.btn_mul:
            case R.id.btn_div:
                if(clear_flag){
                    clear_flag = false;
                    input = "";
                    editText.setText("");
                }
                String str = input;
//                Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",""+str + ":::" + str.substring(str.length()-1));
//                Log.d("XXXX","" + str.substring(str.length()-1).getClass().toString());
                if(str.substring(str.length()-1).equals("+") || str.substring(str.length()-1).equals("*")
                    || str.substring(str.length()-1).equals("/") ||str.substring(str.length()-1).equals("-")){
//                    Log.d("xxxxxxx",""+str);
//                    Log.d("yyyyyyyyyyyyyyyyyyyyyyyyyy","input");
                    editText.setText(input);
                    break;
                }
                editText.setText(input + " " + ((Button)view).getText());
                break;
            case R.id.btn_mod:
                handleMod();break;
            case R.id.btn_back:
                if(clear_flag){
                    clear_flag = false;
                    input = "";
                    editText.setText("");
                }else if(input != null || !input.equals("")) {//如果获取到的内容为空
                    editText.setText(input.substring(0, input.length() - 1));//结果集为空
                }
                break;
            case R.id.btn_equ://运算结果  =
                finalResult();//调用处理结果集的方法
                break;
            case R.id.btn_c:
                editText.setText("");
        }
    }


    public void handleMod(){
        String exp = editText.getText().toString();


        editText.setText( handlePreNum(exp,handleLastNum(exp)) + (int)handleLastNum(exp) * 0.01 + "");
    }
    public String handlePreNum(String str1,double lastNum){//处理前置字符
        int iLastNum = (int)lastNum;
        String str2 = "" + iLastNum;
        Log.d("XXXXXXXXXXX","" + iLastNum);
        Object[] result = deleteSubString(str1, str2);
        String rst = result[0] + "";
        Log.d("xxxxx","" + rst);
        return rst;
    }

    public double handleLastNum(String str){//获取最后数字
        int len = str.length();
        int[] num = new int[len];
        int i = len - 1;
        int count = 0;
        String rst = "";
        for(;i > 0;i--){
            if(str.charAt(i) == '+'||str.charAt(i) == '-'
                    ||str.charAt(i) == '*'||str.charAt(i) == '/'){
                //Log.d("xxxxxxxxxx",count + "+-*/" + i + str.charAt(i));
                for(int j = count ;j >= 0 ;j--){
                    //Log.d("nnnnnn","num"+ j + " " + num[j]);
                    rst = rst + num[j];
                }
                //Log.d("rrrrr","rst" + rst);
                return Double.parseDouble(rst);
            } else if (str.charAt(i) == ' '){

            } else {                                                       //如：+456  45.6
                int mid = Integer.parseInt(str.charAt(i) + "");
                //Log.d("midmidmid"," "  +  mid);
                num[count] = mid;                         // 6 5 4 count == 3    6 . 5 4   c = 4
                //Log.d("mid"," " + num[count]);
                //Log.d("pppppppppppppppppp", "charAt:" + str.charAt(i) + " count:" + count + " "+ i);
                count ++;
            }
        }
        //Log.d("rrrrr","rst" + rst);
        //缺无符号
        return Double.parseDouble(str);
    }

    public Object[] deleteSubString(String str1,String str2) {//删除子字符串
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {
            int index = sb.indexOf(str2);
            if(index == -1) {
                break;
            }
            sb.delete(index, index+str2.length());
            delCount++;

        }
        if(delCount!=0) {
            obj[0] = sb.toString();
            obj[1] = delCount;
        }else {
            //不存在返回-1
            obj[0] = -1;
            obj[1] = -1;
        }

        return obj;
    }



    /**
     *
     * @Title: PrepareParam
     * @Desc: 准备计算的数据，符号
     *
     * @param str 计算式
     * @return 计算结果
     *
     */
    public Double prepareParam(String str) {
        // 空值校验
        if (null == str || "".equals(str)) {
            return null;
        }
        // 长度校验
        if (str.length() > MyUtils.FORMAT_MAX_LENGTH) {
            System.out.println("表达式过长！");
            return null;
        }
        // 预处理
        str = str.replaceAll(" ", "");// 去空格
        if ('-' == str.charAt(0)) {// 开头为负数，如-1，改为0-1
            str = 0 + str;
        }
        // 校验格式
        if (!MyUtils.checkFormat(str)) {
            System.out.println("表达式错误！");
            return null;
        }
        // 处理表达式，改为标准表达式
        str = MyUtils.change2StandardFormat(str);
        // 拆分字符和数字
        String[] nums = str.split("[^.0-9]");
        List<Double> numLst = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (!"".equals(nums[i])) {
                numLst.add(Double.parseDouble(nums[i]));
            }
        }
        String symStr = str.replaceAll("[.0-9]", "");
        return doCalculate(symStr, numLst);
    }

    /**
     *
     * @Title: doCalculate
     * @Desc: 计算
     *
     * @param symStr 符号串
     * @param numLst 数字集合
     * @return 计算结果
     *
     */
    public Double doCalculate(String symStr, List<Double> numLst) {
        LinkedList<Character> symStack = new LinkedList<>();// 符号栈
        LinkedList<Double> numStack = new LinkedList<>();// 数字栈
        double result = 0;
        int i = 0;// numLst的标志位
        int j = 0;// symStr的标志位
        char sym;// 符号
        double num1, num2;// 符号前后两个数
        while (symStack.isEmpty() || !(symStack.getLast() == '=' && symStr.charAt(j) == '=')) {// 形如：
            // =8=
            // 则退出循环，结果为8
            if (symStack.isEmpty()) {
                symStack.add('=');
                numStack.add(numLst.get(i++));
            }
            if (MyUtils.symLvMap.get(symStr.charAt(j)) > MyUtils.symLvMap.get(symStack.getLast())) {// 比较符号优先级，若当前符号优先级大于前一个则压栈
                if (symStr.charAt(j) == '(') {
                    symStack.add(symStr.charAt(j++));
                    continue;
                }
                numStack.add(numLst.get(i++));
                symStack.add(symStr.charAt(j++));
            } else {// 当前符号优先级小于等于前一个 符号的优先级
                if (symStr.charAt(j) == ')' && symStack.getLast() == '(') {// 若（）之间没有符号，则“（”出栈
                    j++;
                    symStack.removeLast();
                    continue;
                }
                if (symStack.getLast() == '(') {// “（”直接压栈
                    numStack.add(numLst.get(i++));
                    symStack.add(symStr.charAt(j++));
                    continue;
                }
                num2 = numStack.removeLast();
                num1 = numStack.removeLast();
                sym = symStack.removeLast();
                switch (sym) {
                    case '+':
                        numStack.add(MyUtils.plus(num1, num2));
                        break;
                    case '-':
                        numStack.add(MyUtils.reduce(num1, num2));
                        break;
                    case '*':
                        numStack.add(MyUtils.multiply(num1, num2));
                        break;
                    case '/':
                        if (num2 == 0) {// 除数为0
                            System.out.println("存在除数为0");
                            return null;
                        }
                        numStack.add(MyUtils.divide(num1, num2));
                        break;
                }
            }
        }
        return numStack.removeLast();
    }






    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private void finalResult(){
        String exp = editText.getText().toString();//获取文本框的内容
        editText.setText("" + Calculate.result(exp));
    }


}
class Calculate {
    //计算结果
    public static Double result(String str) {
        List<String> strList = strToStrList(str);
        List<String> postList = toPostOrder(strList);
        Double result = getResult(postList);
        return result;
    }
    //将字符串转换为字符串List
    private static List<String> strToStrList(String str) {
        List<String> strList = new ArrayList<>();
        int begin=0,end=0,post=0,i=0;
        for (; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*'
                    || str.charAt(i) == '/' || str.charAt(i) == '(' || str.charAt(i) == ')') {
                end = i;
                if (begin < end) {
                    strList.add(str.substring(begin, end));
                    strList.add(str.substring(i,i+1));
                }
                //连续符号时
                else {
                    strList.add(str.substring(i,i+1));
                }
                begin = i + 1;
            }
        }
        //如果没有if包裹，当最后一个字符为操作符时，List末尾会添加一个空字符串
        if (!"".equals(str.substring(begin, str.length()))){
            strList.add(str.substring(begin, str.length()));
        }
        return strList;
    }
    /*
    将中缀表达式转换为后缀表达式
     */
    private static List<String> toPostOrder(List<String> strList) {
        /*规则：
 53         *1，运算数直接输出
 54         *2，左括号压入堆栈
 55         *3，右括号 将栈顶的运算符弹出并输出，括号出栈不输出
 56         *4，运算符：
 57         *    若优先级大于栈顶运算符，压入栈
 58         *    若优先级小于或等于栈顶运算符，栈顶运算符弹出并输出，
 59         *       继续和新栈顶比较，直到比栈顶运算符优先级大，将它压入栈
 60         *5，对象处理完毕后，将栈中运算符弹出并输出
 61         */
        Stack<String> operStack = new Stack<>();
        List<String> postList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            if (isOper(strList.get(i), i, strList)) {
                //堆栈为空时操作符直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(strList.get(i));
                } else {
                    //操作符为"("时直接入栈
                    if ("(".equals(strList.get(i))) {
                        operStack.push(strList.get(i));
                    } else if (")".equals(strList.get(i))) {
                        //操作符为")"时栈顶出栈并输出，直到遇到"(", "("出栈,")"不入栈
                        while (!"(".equals(operStack.peek())) {
                            postList.add(operStack.pop());
                        }
                        operStack.pop();
                    } else {
                        //其他操作符需要比较与栈顶元素的优先级
                        if ("(".equals(operStack.peek())) {
                            operStack.push(strList.get(i));
                        } else {
                            //优先级高，直接入栈
                            if (!operStack.isEmpty() && getPriority(strList.get(i)) > getPriority(operStack.peek())) {
                                operStack.push(strList.get(i));
                            } else {
                                if (!operStack.isEmpty() && getPriority(strList.get(i)) <= getPriority(operStack.peek()) && !"(".equals(operStack.peek())){

                                }
                                //优先级低或者相等，栈顶元素出栈，直到优先级比栈顶元素高
                                while (!operStack.isEmpty() && getPriority(strList.get(i)) <= getPriority(operStack.peek()) && !"(".equals(operStack.peek())) {
                                    postList.add(operStack.pop());
                                }

                                if (operStack.isEmpty()) {
                                    //若堆栈元素全部被弹出，直接入栈
                                    operStack.push(strList.get(i));
                                } else if (getPriority(strList.get(i)) > getPriority(operStack.peek()) || "(".equals(operStack.peek())) {
                                    //若优先级高，入栈
                                    operStack.push(strList.get(i));
                                }
                            }
                        }
                    }
                }
            } else {
                //操作数直接添加
                if ("-".equals(strList.get(i))){

                }else if (i >= 2 && "-".equals(strList.get(i - 1)) && "(".equals(strList.get(i - 2)) ) {
                    postList.add(strList.get(i - 1) + strList.get(i));
                } else if (i == 1 && "-".equals(strList.get(i - 1))) {
                    postList.add(strList.get(i - 1) + strList.get(i));
                } else {
                    postList.add(strList.get(i));
                }
            }
        }
        //最后将所有元素出栈
        while (!operStack.isEmpty()) {
            postList.add(operStack.pop());
        }
        return postList;
    }
    /**
     * 计算后缀表达式
     */
    private static double getResult(List<String> postList) {
        /*规则：
157         *中缀表达式不用比较优先级
158         *将运算数入栈，每读到一个运算符
159         *就弹出栈顶的两个运算数，运算完毕后将结果压入栈
160         */
        Stack<String> numStack = new Stack();
        for(int i =0 ; i < postList.size(); i++) {
            if (isNum(postList.get(i))) {
                numStack.push(postList.get(i));
            } else if (isOper(postList.get(i), i, postList)) {

                if ("+".equals(postList.get(i))) {
                    Double num2 = Double.parseDouble(numStack.pop());
                    Double num1 = Double.parseDouble(numStack.pop());
                    Double result = num1 + num2;
                    numStack.push(result.toString());
                } else if ("-".equals(postList.get(i))) {
                    Double num2 = Double.parseDouble(numStack.pop());
                    Double num1;
                    //能计算负数开头
                    if (numStack.isEmpty()) {
                        num1 = 0.0;
                    } else {
                        num1 = Double.parseDouble(numStack.pop());
                    }
                    Double result = num1 - num2;
                    numStack.push(result.toString());
                } else if ("*".equals(postList.get(i))) {
                    Double num2 = Double.parseDouble(numStack.pop());
                    Double num1 = Double.parseDouble(numStack.pop());
                    Double result = num1 * num2;
                    numStack.push(result.toString());
                } else if ("/".equals(postList.get(i))) {
                    Double num2 = Double.parseDouble(numStack.pop());
                    Double num1 = Double.parseDouble(numStack.pop());
                    Double result = num1 / num2;
                    numStack.push(result.toString());
                }
            }
        }
        return Double.parseDouble(numStack.pop());
    }
    /*
   判断该字符串是否为操作符
   */
    private static boolean isOper(String str, int i, List<String> stringList){
        if("*".equals(str)|| "/".equals(str)||
                "+".equals(str)|| "%".equals(str) ||
                "(".equals(str)|| ")".equals(str)){
            return true;
        } else if ("-".equals(str)) {
            return !(i>=1 && "(".equals(stringList.get(i-1)));
        }
        else{
            return false;
        }
    }
    /*
    判断该字符串是否为操作数
    */
    private static boolean isNum(String str){
        if("*".equals(str)|| "/".equals(str)||
                "+".equals(str)|| "-".equals(str)||
                "(".equals(str)|| ")".equals(str)){
            return false;
        }
        else{
            return true;
        }
    }
    /*
    得到操作符的优先级
    */
    private static int getPriority(String c){
        if("*".equals(c) || "/".equals(c)){
            return 2;
        } else if ("+".equals(c) || "-".equals(c)){
            return 1;
        } else {
            return 999;
        }
    }
}
class MyUtils {
    public static final int FORMAT_MAX_LENGTH = 500;// 表达式最大长度限制
    public static final int RESULT_DECIMAL_MAX_LENGTH = 8;// 结果小数点最大长度限制
    public static final Map<Character, Integer> symLvMap = new HashMap<Character, Integer>();// 符号优先级map
    static {
        symLvMap.put('=', 0);
        symLvMap.put('-', 1);
        symLvMap.put('+', 1);
        symLvMap.put('*', 2);
        symLvMap.put('/', 2);
        symLvMap.put('(', 3);
        symLvMap.put(')', 1);
        // symLvMap.put('^', 3);
        // symLvMap.put('%', 3);
    }

    /**
     *
     * @Title: checkFormat
     * @Desc: 检查表达式格式是否正确
     *
     * @param str 表达式
     * @return true表达式正确，false表达式错误
     *
     */
    public static boolean checkFormat(String str) {
        // 校验是否以“=”结尾
        if ('=' != str.charAt(str.length() - 1)) {
            return false;
        }
        // 校验开头是否为数字或者“(”
        if (!(isCharNum(str.charAt(0)) || str.charAt(0) == '(')) {
            return false;
        }
        char c;
        // 校验
        for (int i = 1; i < str.length() - 1; i++) {
            c = str.charAt(i);
            if (!isCorrectChar(c)) {// 字符不合法
                return false;
            }
            if (!(isCharNum(c))) {
                if (c == '-' || c == '+' || c == '*' || c == '/') {
                    if (c == '-' && str.charAt(i - 1) == '(') {// 1*(-2+3)的情况
                        continue;
                    }
                    if (!(isCharNum(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {// 若符号前一个不是数字或者“）”时
                        return false;
                    }
                }
                if (c == '.') {
                    if (!isCharNum(str.charAt(i - 1)) || !isCharNum(str.charAt(i + 1))) {// 校验“.”的前后是否位数字
                        return false;
                    }
                }
            }
        }
        return isBracketCouple(str);// 校验括号是否配对
    }

    /**
     *
     * @Title: change2StandardFormat
     * @Desc: 处理表达式格式为标准格式，如2(-1+2)(3+4)改为2*(0-1+2)*(3+4)
     *
     * @param str
     * @return 标准表达式
     *
     */
    public static String change2StandardFormat(String str) {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (i != 0 && c == '(' && (isCharNum(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {
                sb.append("*(");
                continue;
            }
            if (c == '-' && str.charAt(i - 1) == '(') {
                sb.append("0-");
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     *
     * @Title: isBracketCouple
     * @Desc: 校验括号是否配对
     * @param str
     * @return 参数
     *
     */
    public static boolean isBracketCouple(String str) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.removeLast();
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @Title: formatResult
     * @Desc: 处理计算结果的显示
     *
     * @param str 计算结果
     * @return 规范的计算结果
     *
     */
    public static String formatResult(String str) {
        String[] ss = str.split("\\.");
        if (Integer.parseInt(ss[1]) == 0) {
            return ss[0];// 整数
        }
        String s1 = new StringBuilder(ss[1]).reverse().toString();
        int start = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != '0') {
                start = i;
                break;
            }
        }
        return ss[0] + "." + new StringBuilder(s1.substring(start, s1.length())).reverse();
    }

    /**
     *
     * @Title: isCorrectChar
     * @Desc: 校验字符是否合法
     *
     * @param c
     * @return 参数
     *
     */
    public static boolean isCorrectChar(Character c) {
        if (('0' <= c && c <= '9') || c == '-' || c == '+' || c == '*' || c == '/' || c == '(' || c == ')'
                || c == '.') {
            return true;
        }
        return false;
    }

    /**
     *
     * @Title: isCharNum
     * @Desc: 判断是否为数字
     *
     * @param c
     * @return
     *
     */
    public static boolean isCharNum(Character c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;

    }

    /**
     *
     * @Title: plus
     * @Desc: 加
     *
     * @param num1
     * @param num2
     * @return 计算结果
     *
     */
    public static double plus(double num1, double num2) {
        return num1 + num2;
    }

    /**
     *
     * @Title: reduce
     * @Desc: 减
     *
     * @param num1
     * @param num2
     * @return 计算结果
     *
     */
    public static double reduce(double num1, double num2) {
        return num1 - num2;
    }

    /**
     *
     * @Title: multiply
     * @Desc: 乘
     *
     * @param num1
     * @param num2
     * @return 计算结果
     *
     */
    public static double multiply(double num1, double num2) {
        return num1 * num2;

    }
    /**
     *
     * @Title: divide
     * @Desc: 除
     *
     * @param num1
     * @param num2
     * @return 计算结果
     *
     */
    public static double divide(double num1, double num2) {
        return num1 / num2;
    }
}