import java.io.BufferedReader;
import java.io.FileReader;

public class read_notepad {

public static void main(String[] args) throws Exception
{
FileReader fr=new FileReader("E:\\sample1.txt");
BufferedReader br= new BufferedReader(fr);
String x="";
while ((x=br.readLine()) != null)
{
System.out.println(x +"\n");
}
br.close();
}
}

