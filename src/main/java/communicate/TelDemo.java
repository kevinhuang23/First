package communicate;

import java.io.IOException;
import java.util.Scanner;

import org.dom4j.DocumentException;

public class TelDemo {
    public static void main(String[] args) throws DocumentException, IOException {
        while(true){
        System.out.println("欢迎使用通讯录,请输入相应数字选择功能:");
        System.out.println("1.添加联系人");
        System.out.println("2.编辑联系人");
        System.out.println("3.删除联系人");
        System.out.println("4.查找联系人");

        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();

        //创建工具类对象
        TelTool tt=new TelTool();

        switch(i){
        case 1:
            System.out.println("请输入联系人姓名");
            String name=sc.next();
            System.out.println("请输入电话号码");
            String num=sc.next();
            tt.addNum(name, num);
            break;
        case 2:
            System.out.println("请输入要修改的联系人姓名");
            String name2=sc.next();
            System.out.println("请输入要修改的电话号码");
            String num2=sc.next();
            tt.setNum(name2, num2);
            break;
        case 3:
            System.out.println("请输入要删除的联系人姓名");
            String name3=sc.next();
            tt.delNum(name3);
            break;
        case 4:
            System.out.println("查询结果如下:");
            tt.queryAll();
            break;
        default:
            System.out.println("输入错误");
        }
    }
    }

}