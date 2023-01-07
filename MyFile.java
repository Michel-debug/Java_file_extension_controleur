package subClasses;
import java.io.File;
import java.io.IOException;
/**
 *@Description: a class to store the file info
 *@name MyFile class
 *@Author: echo
 *@Date: 21:05 2021/1/4
 **/
public class MyFile {
private String filePath;//文件路径
private String fileExtension;//文件扩展
private String fileNoExtension;//去除文件扩
private String fileRealType;//文件头对应的扩展
private String fileSize ;//文件大小
    public MyFile(String filePath) throws IOException {
        //根据文件路径创建文件对象
        File file=new File(filePath);
        //更新此对象文件路径
        this.filePath = filePath;
        String temp[]=filePath.split("\\.");
        this.fileExtension =temp[temp.length-1];
        this.fileNoExtension=temp[0];
        //this.fileName=filePath.substring(filePath.lastIndexOf("\\")+1,filePath.indexOf("."));
        //创建console对象进行文件处理
        Console mym=new Console();
        //更新此对象文件类型
        this.fileRealType = mym.getFileType(filePath);
        //更新此对象文件大小（以B/KB/GB计算）
        this.fileSize = mym.getPrintSize(file.length());
    }
    /**
     *@Description: to critical if extension is right and output the info
     *@name critical
     *@Author: echo
     *@Date: 21:05 2021/1/4
    **/
    public String critical(){
        if(this.fileRealType.indexOf(this.fileExtension)!=-1&&this.fileRealType!="no file"){
            System.out.print("\n|--"+this.filePath+"   "+this.fileSize);
            return "\n|--"+this.filePath+"   "+this.fileSize;
        }else{
            System.out.print("\n|--"+this.fileNoExtension);
            System.err.print("."+this.fileExtension);
            System.err.print("   wrong extension   "+this.fileSize);
            return "\n|--"+this.filePath+"   "+"   wrong extension   "+this.fileSize;
        }
    }
}
