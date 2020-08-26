import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONObject;

public class Usecase1Memory {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br=new BufferedReader(new FileReader(new File("E:\\Memory.txt")));
		DecimalFormat decimelformat=new DecimalFormat("#.00");
		List<Double>list=new ArrayList<Double>();
		JSONObject obj1=new JSONObject();
		double sum=0.0;
		
		String s;
		int count=1,i=1;
		while((s=br.readLine())!=null)
		{
			if(s.isEmpty()||s.trim().equals(""))
			{
				continue;
			}
			else
			{
				if(count%2!=0)
				{
					count++;
					continue;
				}
				else
				{
					count++;
					String str[]=s.split("\\s+");
					double data=(Double.parseDouble(str[2]))/1024;
					//data=data/1024;
					String stringdecimalobj=decimelformat.format(data);
					
					list.add(data);
					sum+=data;
					obj1.put((i)+"s",stringdecimalobj);
					i++;
				
				}
			}
			
		}
		String avgerage=decimelformat.format(sum/list.size());
		
		Comparator<Double> comp=(n1,n2)->n1.compareTo(n2);
		String Maximum=decimelformat.format(list.stream().max(comp).get());
		JSONObject obj2=new JSONObject();
		obj2.put("values",obj1);
		obj2.put("AverageMemory(MB)",avgerage);
		obj2.put("MaxMemory(MB)",Maximum);
		obj2.put("Usecasename","HomePage");
		FileWriter fw=new FileWriter("E:\\MemoryOutputFile.json");
		fw.write(obj2.toJSONString());
		fw.close();
		System.out.println(obj2);
	}

}