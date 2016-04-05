package sentinel;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.ArrayList;

class ConfigurationSyntaxError extends Exception
{}

public class ObjectLoader
{
  
  private HashMap<String,float[]> vecfs=new HashMap<String,float[]>();
  
  private float[] parseVECF(Scanner fdata) throws ConfigurationSyntaxError
    {
      ArrayList<Float> list=new ArrayList<Float>();
      while(fdata.hasNextFloat()) list.add(fdata.nextFloat());
      float[] result=new float[list.size()];
      for(int i=0;i<list.size();i++) result[i]=list.get(i);
      return result;
    }

  private void parseDataDefinition(Scanner fdata) throws ConfigurationSyntaxError
    {
      String type=fdata.next();
      String name=fdata.next();
      switch(type)
        {
          case "VECF": vecfs.put(name,parseVECF(fdata));break;
          default: throw new ConfigurationSyntaxError();
        }
      if(!fdata.next().equals(";")) throw new ConfigurationSyntaxError();
    }

  private void parseDataFile(Scanner fdata) throws ConfigurationSyntaxError
    {
      while(fdata.hasNext()) parseDataDefinition(fdata);
    }
    
  public ObjectLoader()
    {
      try
        {
          parseDataFile(new Scanner(
              getClass().getResourceAsStream("/GameData.txt")
            ).useDelimiter("(\\s+(#[^\\n]*\\n)?)+"));
        }
      catch(Exception e)
        {
          System.err.print("Error while loading game data: ");
          System.err.println(e);
          System.exit(0);
        }
    }
}
