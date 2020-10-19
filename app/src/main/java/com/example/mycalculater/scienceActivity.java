package com.example.mycalculater;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class scienceActivity extends Activity implements View.OnClickListener {
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
    private Button btn_add;// +
    private Button btn_mis;  // -
    private Button btn_mul;  // *
    private Button btn_div;  // /
    private Button btn_mod;  // %
    private Button btn_point;  //小数点
    private Button btn_equ;  //=

    //清除
    private Button btn_back;
    private Button btn_c;//clear
    private Button btn_more;

    //
    private Button btn_1_x_add;

    boolean clear_flag;//清空标识
    private EditText et;

    boolean isLastPoint = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.science_calculater);

        //数字1-9
        View btn1 = findViewById(R.id.btn_1);
        View btn2 = findViewById(R.id.btn_2);
        View btn3 = findViewById(R.id.btn_3);
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
        View btn_less = findViewById(R.id.btn_less);//跳转


        //科学计算器

        View btn_1_x_add = findViewById(R.id.btn_1_X_add);
        View btn_leftBrackets = findViewById(R.id.btn_leftBrackets);
        View btn_rightBrackets = findViewById(R.id.btn_rightBrackets);
        View btn_1divX = findViewById(R.id.btn_1divX);
        View btn_sin = findViewById(R.id.btn_sin);
        View btn_cos = findViewById(R.id.btn_cos);
        View btn_tan = findViewById(R.id.btn_tan);
        View btn_sqrt = findViewById(R.id.btn_sqrt);
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
        btn_less.setOnClickListener(this);

        btn_1_x_add.setOnClickListener(this);
        btn_leftBrackets.setOnClickListener(this);
        btn_rightBrackets.setOnClickListener(this);
        btn_1divX.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {


        //获取文本内容
        String input = editText.getText().toString();

        //boolean isLastPoint = false;
        switch (view.getId()) {
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
                if (clear_flag) {
                    clear_flag = false;
                    editText.setText("");//赋值为空
                }
//                if(editText.getText().toString().substring(editText.getText().toString().length()-1).equals('0')){
                //                   editText.setText("0");break;
                //               }
                editText.setText(input + ((Button) view).getText());//结果集就为本身
                break;
            case R.id.btn_point:
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                if (clear_flag) {
                    clear_flag = false;
                    editText.setText("");//赋值为空
                }
                if (editText.getText().toString() == null || editText.getText().toString().isEmpty()) {
                    editText.setText("请先输入数字，在输入小数点");
                    break;
                }
                if (editText.getText().toString().indexOf("sin") == -1 && editText.getText().toString().indexOf("cos") == -1
                        && editText.getText().toString().indexOf("tan") == -1 && editText.getText().toString().indexOf("/") == -1
                        && editText.getText().toString().indexOf("*") == -1 && editText.getText().toString().indexOf("+") == -1
                        && editText.getText().toString().indexOf("-") == -1 && editText.getText().toString().indexOf("√") == -1) {

                } else{
                    editText.setText("科学运算符不能进行复杂运算哦！");
                    break;
                }
                double lastNum = handleLastNum(editText.getText().toString());
                Log.d("xxxxx", "" + lastNum);
                double d = 0;
                if (!editText.getText().toString().equals("")) {
                    d = lastNum;
                } else {
                    editText.setText(input + ((Button) view).getText());//结果集就为本身
                    isLastPoint = true;
                    break;
                }
                //Log.d("xxxxxxx",editText.getText().toString().equals("") +"");
                if ((int) d == d) {
                    isLastPoint = false;
                } else {
                    isLastPoint = true;
                    editText.setText("err");
                    break;
                }
                if (isLastPoint) {
                    editText.setText("err");
                    break;
                }
                editText.setText(input + ((Button) view).getText());//结果集就为本身
                isLastPoint = true;
                break;
            case R.id.btn_add:
            case R.id.btn_mis:
            case R.id.btn_mul:
            case R.id.btn_div:
                if (editText.getText().toString().equals("")) {//处理开始时输入的符号
                    //Log.d("SSSSSSSSSSSSSSSSSSs","x");
                    break;
                }
                if (clear_flag) {
                    clear_flag = false;
                    input = "";
                    editText.setText("");
                }
                String str = input;
//                Log.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",""+str + ":::" + str.substring(str.length()-1));
//                Log.d("XXXX","" + str.substring(str.length()-1).getClass().toString());
                if (str.substring(str.length() - 1).equals("+") || str.substring(str.length() - 1).equals("*")
                        || str.substring(str.length() - 1).equals("/") || str.substring(str.length() - 1).equals("-")) {
//                    Log.d("xxxxxxx",""+str);
//                    Log.d("yyyyyyyyyyyyyyyyyyyyyyyyyy","input");
                    editText.setText(input);
                    break;
                }
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_mod:
                if (checkNameChese(editText.getText().toString())) {
                    editText.setText("请勿输入汉字");
                    break;
                }
                handleMod();
                break;
            case R.id.btn_back:
                if (editText.getText().toString().equals("")) {//处理连点
                    //Log.d("SSSSSSSSSSSSSSs","x");
                    break;
                }
                if (clear_flag) {
                    clear_flag = false;
                    input = "";
                    editText.setText("");
                } else if (input != null || !input.equals("")) {//如果获取到的内容为空
                    editText.setText(input.substring(0, input.length() - 1));//结果集为空
                }
                break;
            case R.id.btn_equ://运算结果  =
                //Log.d("XXXXXXXXXXXXXXXXXXXXX", "" + editText.getText().toString());
                if (checkNameChese(editText.getText().toString())) {
                    editText.setText("请勿输入汉字");
                    break;
                }
                if (haveSqrt()) {
                    //Log.d("XXXXXXXXXXXXXXXXXXXXX", "" + editText.getText().toString());
                    handleSqrtCalcu(editText.getText().toString());
                    break;
                } else if (haveSin()) {
                    sinCalcu();
                    break;
                } else if (haveCos()) {
                    cosCalcu();
                    break;
                } else if (haveTan()) {
                    tanCalcu();
                    break;
                }
                finalResult();//调用处理结果集的方法
                break;
            case R.id.btn_c:
                editText.setText("");
                isLastPoint = false;
                break;
            case R.id.btn_less:
                // 给bnt1添加点击响应事件
                Intent intent = new Intent(scienceActivity.this, MainActivity.class);
                //启动
                startActivity(intent);
                break;
            case R.id.btn_sqrt:
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_1divX:
                if (checkNameChese(editText.getText().toString())) {
                    editText.setText("请勿输入汉字");
                    break;
                }
                oneDivXCalcu();
                break;
            case R.id.btn_leftBrackets:
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_rightBrackets:
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_sin:
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_cos:
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_tan:
                editText.setText(input + " " + ((Button) view).getText());
                break;
            case R.id.btn_1_X_add:
                if (checkNameChese(editText.getText().toString())) {
                    editText.setText("请勿输入汉字");
                    break;
                }
                oneToXAdd();
                break;
        }
    }

    public void oneToXAdd() {
        String str = editText.getText().toString();
        if (str.indexOf("sin") == -1 && str.indexOf("cos") == -1 && str.indexOf("tan") == -1 && str.indexOf("/") == -1 &&
                str.indexOf("*") == -1 && str.indexOf("+") == -1 && str.indexOf("-") == -1 && str.indexOf("√") == -1) {
            double d = Double.parseDouble(str);
            if (d == (int) d) {
                editText.setText("" + (d + 1) * d / 2);
            } else {

            }
        } else {
            editText.setText("错误!只能输入整数哦！");
        }

    }

    public void oneDivXCalcu() {
        if (editText.getText().toString() == null || editText.getText().toString().isEmpty()) {
            editText.setText("请输入数据~~~");
            return;
        }
        if (checkNameChese(editText.getText().toString())) {
            editText.setText("请勿输入汉字哦");
            return;
        }
        String str = editText.getText().toString();
        //Log.d("XXXXXXXX","" + str);
        if (str.indexOf("sin") == -1 && str.indexOf("cos") == -1 && str.indexOf("tan") == -1 && str.indexOf("/") == -1 &&
                str.indexOf("*") == -1 && str.indexOf("+") == -1 && str.indexOf("-") == -1 && str.indexOf("√") == -1) {
            if (Double.parseDouble(str) == 0) {
                editText.setText("分母不能为0哦");
            } else {
                editText.setText("" + 1 / Double.parseDouble(str));
            }
        } else {
            editText.setText("错误!输入错误！");
        }
    }

    //三角函数
    public boolean haveSin() {
        String str = editText.getText().toString();
        int i = 0;
        if (str.indexOf("sin") != -1)
            return true;
        return false;
    }

    public void sinCalcu() {
        int i = 0;
        String str = editText.getText().toString();
        double a = 0;
        if (str.charAt(1) == 's') {
        } else {
            editText.setText("错误！复杂符号只能做简单计算哦！");
            return;
        }
        for (; i < str.length(); i++) {//判断是否只有一个运算符
            if (str.charAt(i) == '+' || str.charAt(i) == '-'
                    || str.charAt(i) == '*' || str.charAt(i) == '/') {
                editText.setText("错误！复杂符号只能做简单计算哦！");
                return;
            }
        }
        a = handleSpace(str);
        editText.setText("" + Math.sin(a));
    }

    public void cosCalcu() {
        int i = 0;
        String str = editText.getText().toString();
        double a = 0;
        if (str.charAt(1) == 'c') {
        } else {
            editText.setText("错误！复杂符号只能做简单计算哦！");
            return;
        }
        for (; i < str.length(); i++) {//判断是否只有一个运算符
            if (str.charAt(i) == '+' || str.charAt(i) == '-'
                    || str.charAt(i) == '*' || str.charAt(i) == '/') {
                editText.setText("错误！复杂符号只能做简单计算哦！");
                return;
            }
        }
        a = handleSpace(str);
        editText.setText("" + Math.cos(a));
    }

    public boolean haveCos() {
        String str = editText.getText().toString();
        int i = 0;
        if (str.indexOf("cos") != -1)
            return true;
        return false;
    }

    public void tanCalcu() {
        int i = 0;
        String str = editText.getText().toString();
        double a = 0;
        if (str.charAt(1) == 't') {
        } else {
            editText.setText("错误！复杂符号只能做简单计算哦！");
            return;
        }
        for (; i < str.length(); i++) {//判断是否只有一个运算符
            if (str.charAt(i) == '+' || str.charAt(i) == '-'
                    || str.charAt(i) == '*' || str.charAt(i) == '/') {
                editText.setText("错误！复杂符号只能做简单计算哦！");
                return;
            }
        }
        a = handleSpace(str);
        editText.setText("" + Math.tan(a));
    }

    public boolean haveTan() {
        String str = editText.getText().toString();
        int i = 0;
        if (str.indexOf("tan") != -1)
            return true;
        return false;
    }
//开平方

    public boolean haveSqrt() {
        String str = editText.getText().toString();
        int i = 0;
        if (str.indexOf("√") != -1)
            return true;
        for (; i < str.length(); i++) {
            if (str.charAt(i) == '√') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //true合法
    public boolean handleSqrtLegal(String str) {
        int i = 0;
        if (str.charAt(1) == '√') {
        } else {
            return false;
        }
        for (; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-'
                    || str.charAt(i) == '*' || str.charAt(i) == '/') {
                return false;
            }
        }
        return true;
    }

    public double handleSpace(String str) {
        int i = 2;
        if (str.indexOf("√") != -1)
            i = 2;
        else if (str.indexOf("tan") != -1 || str.indexOf("cos") != -1 || str.indexOf("sin") != -1)
            i = 4;
        String a = "";
        for (; i < str.length(); i++)
            a += str.charAt(i);
        return Double.parseDouble(a);
    }

    public void handleSqrtCalcu(String str) {//用string偷懒咯
        double a = 0;

        if (handleSqrtLegal(str)) {
            a = handleSpace(str);
            if (a < 0) {
                //root can't be negative
                editText.setText("错误！a要大于0哦！");
            } else {
                editText.setText(Math.sqrt(a) + "");
                return;
            }
        } else {
            editText.setText("错误！复杂符号只能做简单计算哦！");
        }
        return;
    }


    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    public void handleMod() {

        String exp = editText.getText().toString();

        editText.setText(handlePreNum(exp, handleLastNum(exp))
                + (int) handleLastNum(exp) * 0.01 + "");
    }

    public String handlePreNum(String str1, double lastNum) {//处理前置字符
        int iLastNum = (int) lastNum;
        String str2 = "" + iLastNum;
        Log.d("XXXXXXXXXXX", "" + iLastNum);
        Object[] result = deleteSubString(str1, str2);
        String rst = result[0] + "";
        Log.d("xxxxx", "" + rst);
        return rst;
    }

    public double handleLastNum(String str) {//获取最后数字
        int len = str.length();
        int[] num = new int[len];
        int i = len - 1;
        int count = 0;
        String rst = "";
        for (; i > 0; i--) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-'
                    || str.charAt(i) == '*' || str.charAt(i) == '/') {
                //Log.d("xxxxxxxxxx",count + "+-*/" + i + str.charAt(i));
                for (int j = count; j >= 0; j--) {
                    //Log.d("nnnnnn","num"+ j + " " + num[j]);
                    rst = rst + num[j];
                }
                //Log.d("rrrrr","rst" + rst);
                return Double.parseDouble(rst);
            } else if (str.charAt(i) == ' ') {

            } else {                                                       //如：+456  45.6
                int mid = Integer.parseInt(str.charAt(i) + "");
                //Log.d("midmidmid"," "  +  mid);
                num[count] = mid;                         // 6 5 4 count == 3    6 . 5 4   c = 4
                //Log.d("mid"," " + num[count]);
                //Log.d("pppppppppppppppppp", "charAt:" + str.charAt(i) + " count:" + count + " "+ i);
                count++;
            }
        }
        //Log.d("rrrrr","rst" + rst);
        //缺无符号
        return Double.parseDouble(str);
    }

    public Object[] deleteSubString(String str1, String str2) {//删除子字符串
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {

            int index = sb.indexOf(str2);
            if (index == -1) {
                break;
            }
            sb.delete(index, index + str2.length());
            delCount++;

        }
        if (delCount != 0) {

            obj[0] = sb.toString();
            obj[1] = delCount;
        } else {
            //不存在返回-1
            obj[0] = -1;
            obj[1] = -1;
        }

        return obj;
    }


    /**
     * @param str 计算式
     * @return 计算结果
     * @Title: PrepareParam
     * @Desc: 准备计算的数据，符号
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
     * @param symStr 符号串
     * @param numLst 数字集合
     * @return 计算结果
     * @Title: doCalculate
     * @Desc: 计算
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
    private void finalResult() {
        String exp = editText.getText().toString();//获取文本框的内容
        editText.setText("" + Calculate.result(exp));
    }


}

