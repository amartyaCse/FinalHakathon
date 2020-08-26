
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;

public class Usecase2Battery {

	public static void main(String[] args) {
		try {
			FileReader fr=new FileReader("E:\\Battery.txt");
			BufferedReader br=new BufferedReader(fr);
			String line="";
			JSONObject obj=new JSONObject();
			String Foregroundactivities="";
			String s="";
			String batteryDreain="";
			while((line=br.readLine())!=null)
			{
				//System.out.println(line);
				
				if(line.contains("Foreground activities:"))
				{
					Foregroundactivities=line;
					s=Foregroundactivities.trim();
					String a[]=s.split("\\s+");
					s=a[2]+" "+a[3]+" "+a[4]+" "+a[5]+" "+a[6]+" "+a[7];
					obj.put("Foreground_time", s);
				}
				else if(line.contains("Uid u0a202"))
				{
					String array[]=line.split("\\s+");
					batteryDreain=array[3];
					obj.put("Battery_drain", batteryDreain);
					float bp=Float.parseFloat(batteryDreain);
					float battery=bp/1000;
					obj.put("Battery_percentage", (String.format("%.3f",battery)));
				}
			}
			System.out.println(obj);
			FileWriter file=new FileWriter("E:\\BatteryoutputFile.json");
			file.write(obj.toJSONString());
			file.close();
			fr.close();
	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}