package cn.xieyy.utils.code.generator.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadWriterUtil {

	/**
	 * 读取文件方法，(每行读取一次)
	 * @param filePath 文件路径
	 * @return 文件内容字串
	 */
	public static StringBuffer readFileByLines(String filePath) {
        BufferedReader br = null;
        StringBuffer str = new StringBuffer();
        try {
        	InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
        	br = new BufferedReader(read);
            String tempString = null;
            while ((tempString = br.readLine()) != null) {
                str.append(tempString + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				if(br != null){
					br.close(); 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return str;
    }
	
	
	/**
	 * 输出文件内容的方法
	 * @param filePath 生成文件路径
	 * @param str 要生成的内容
	 * @return 操作是否成功
	 */
	public static int writerFile(String filePath,StringBuffer str){
		 
		File file = new File(filePath);
		BufferedWriter bw = null;
		int result = 1;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bw.write(str.toString());
			bw.flush();         
		} catch (IOException e) {
			result = 0; 
			e.printStackTrace();
		}finally{
			try {
				if(bw != null){
					bw.close(); 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
