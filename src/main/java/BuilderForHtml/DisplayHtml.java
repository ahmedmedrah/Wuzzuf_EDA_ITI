package BuilderForHtml;



import com.mycompany.spmyleaf.Job;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;

public class DisplayHtml {

    private static HTMLTableBuilder builder ;

    public static String displayrows(String []head,List<String> ls){

      builder=new HTMLTableBuilder(null,false,3,head.length);
      builder.addTableHeader(head);
      for (String ob : ls) {
			String[] s = ob.toString().replace("[","").replace("]","")
                    .split(",", head.length);
			builder.addRowValues(s);

		}
		return builder.build();


    }
    public static String viewchart(String path){

        FileInputStream img ;
		try {
			File f= new File(path);
			 img = new FileInputStream(f);
			byte[] bytes =  new byte[(int)f.length()];
			img.read(bytes);
			String encodedfile = new String(Base64.encodeBase64(bytes) , "UTF-8");


			return "<div>" +
					"<img src=\"data:image/png;base64, "+encodedfile+"\" alt=\"Red dot\" />" +
					"</div>";
		} catch (IOException e) {
			return "error";
		}



    }

}
