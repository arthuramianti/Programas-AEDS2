import java.io.*;
import java.net.*;

class TP01Q08{
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String getHtml(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
  
        try {
           url = new URL(endereco);
           is = url.openStream();  // throws an IOException
           br = new BufferedReader(new InputStreamReader(is));
  
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
           }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } 
  
        try {
           is.close();
        } catch (IOException ioe) {}
  
        return resp;
     }

     public static boolean isNumero(char c){
        return (c >= '0' && c <= '9');
     }

     public static boolean isMaiuscula(char c){
         return (c >= 'A' && c <= 'Z');
     }

     public static int isConsoante(String s){
        int resp = 0;
        int aux = 0;

        for(int i = 0; i < s.length(); i++){
            aux = i;
            if(aux + 4 < s.length())
                if(s.substring(aux, aux+4).equals("table"))
              i = i + 4;        
            else if (s.charAt(i) != 'A' && s.charAt(i) != 'a' && s.charAt(i) != 'E' && s.charAt(i) != 'e' && s.charAt(i) != 'I' && s.charAt(i) != 'i' && s.charAt(i) != 'O' && s.charAt(i) != 'o' &&
            s.charAt(i) != 'U' && s.charAt(i) != 'u' && s.charAt(i) != '<' && s.charAt(i) != '>' && isNumero(s.charAt(i)) == false && s.charAt(i) != 'á' && s.charAt(i) != 'é'
            && s.charAt(i) != 'í' && s.charAt(i) != 'ó' && s.charAt(i) != 'ú' && s.charAt(i) != 'à' && s.charAt(i) != 'è' && s.charAt(i) != 'ì' && s.charAt(i) != 'ò' && s.charAt(i) != 'ù' &&
            s.charAt(i) != 'ã' && s.charAt(i) != 'õ' && s.charAt(i) != 'â' && s.charAt(i) != 'ê' && s.charAt(i) != 'î' && s.charAt(i) != 'ô' && s.charAt(i) != 'û' && s.charAt(i) != '?' && isMaiuscula(s.charAt(i)) == false && s.charAt(i) != '&'){
                resp++;
            }    
        }    
        
         return resp;
    }

    public static boolean isTable(String s){
        return (s.equals("table"));
    }


    public static String formataString(String html){
        String newString = html.replace("\"", "&#");
        newString = newString.replace(";", "");
        newString = newString.replace(":", "");
        newString = newString.replace(",", "");
        newString = newString.replace(".", "");
        newString = newString.replace("=", "");
        newString = newString.replace("-", "");
        newString = newString.replace("_", "");
        newString = newString.replace("+", "");
        newString = newString.replace(":", "");
        newString = newString.replace("^", "");
        newString = newString.replace("\\", "");
        newString = newString.replace("%", "");
        newString = newString.replace("/", "");
        newString = newString.replace("'", "");
        newString = newString.replace("(", "");
        newString = newString.replace(")", "");
        newString = newString.replace("#", "");
        newString = newString.replace("{", "");
        newString = newString.replace("}", "");
        newString = newString.replace("|", "");
        newString = newString.replace("[", "");
        newString = newString.replace("]", "");
        newString = newString.replace("\n", "");
        newString = newString.replace("$", "");
        newString = newString.replace("*", "");
        newString = newString.replace("!", "");
        newString = newString.replace("@", ""); 
        newString = newString.replace("\t", "");
        newString = newString.replace("<", "");
        newString = newString.replace(">", "");
        newString = newString.replace(" ", "");
        newString = newString.replace("«", "");
        newString = newString.replace("»", "");
        newString = newString.replace("\r", "");
        
        
        
        return newString;
    }

    

    public static void extraiDados(String html, String consoante){
       int a = 0, e = 0, i = 0, o = 0, u = 0, á = 0, é = 0, í = 0, ó = 0, ú = 0, à = 0, è = 0, ì = 0, ò = 0, ù = 0, ã = 0, õ = 0, â = 0, ê = 0, î = 0, ô = 0, û = 0, br = 0, table = 0;
       int aux = 0; 
       String tab = "";
        for(int j = 0; j < html.length(); j++){
            aux = j;
            if(html.charAt(j) == 'a')
                a++;
            else if (html.charAt(j) == 'e')
                e++;
            else if (html.charAt(j) == 'i')
                i++;
            else if (html.charAt(j) == 'o')
                o++;
            else if (html.charAt(j) == 'u')
                u++;   
            else if (html.charAt(j) == 'á')
                á++;
            else if (html.charAt(j) == 'é')
                é++;
            else if (html.charAt(j) == 'í')
                í++;
            else if (html.charAt(j) == 'ó')
                ó++;
            else if (html.charAt(j) == 'ú')
                ú++;
            else if (html.charAt(j) == 'à')
                à++;
            else if (html.charAt(j) == 'è')
                è++;
            else if (html.charAt(j) == 'ì')
                ì++;
            else if (html.charAt(j) == 'ò')
                ò++;
            else if (html.charAt(j) == 'ù')
                ù++;
            else if (html.charAt(j) == 'ã')
                ã++;
            else if (html.charAt(j) == 'õ')
                õ++;
            else if (html.charAt(j) == 'â')
                â++;
            else if (html.charAt(j) == 'ê')
                ê++;
            else if (html.charAt(j) == 'î')
                î++;
            else if (html.charAt(j) == 'ô')
                ô++;
            else if (html.charAt(j) == 'û')
                û++;
            else if (aux + 1 < html.length())
                if(html.substring(aux, aux+1).equals("br"))
                br++;
            else if (aux + 4 < html.length()){
                 tab = html.substring(aux, aux+4);
                if(html.substring(aux, aux+4).equals("table"))        
                table++;                 
            }                     
                
       }
       MyIO.print("a" + "(" + a + ")");
       MyIO.print(" e" + "(" + e + ")");
       MyIO.print(" i" + "(" + i + ")");
       MyIO.print(" o" + "(" + o + ")");
       MyIO.print(" u" + "(" + u + ")");
       MyIO.print(" á" + "(" + á + ")");
       MyIO.print(" é" + "(" + é + ")");
       MyIO.print(" í" + "(" + í + ")");
       MyIO.print(" ó" + "(" + ó + ")");
       MyIO.print(" ú" + "(" + ú + ")");
       MyIO.print(" à" + "(" + à + ")");
       MyIO.print(" è" + "(" + è + ")");
       MyIO.print(" ì" + "(" + ì + ")");
       MyIO.print(" ò" + "(" + ò + ")");
       MyIO.print(" ù" + "(" + ù + ")");
       MyIO.print(" ã" + "(" + ã + ")");
       MyIO.print(" õ" + "(" + õ + ")");
       MyIO.print(" â" + "(" + â + ")");
       MyIO.print(" ê" + "(" + ê + ")");
       MyIO.print(" î" + "(" + î + ")");
       MyIO.print(" ô" + "(" + ô + ")");
       MyIO.print(" û" + "(" + û + ")");
       MyIO.print(" consoante" + "(" + isConsoante(formataString(consoante)) + ")");
       MyIO.print(" <br>" + "(" + br + ")");
       MyIO.print(" <table>" + "(" + table + ")");

    }

    public static String escapeHTML(String s) {
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '"' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static String escapeHTML2(String s) {
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }
    

    public static void main(String[] args) throws Exception {
        String[] entrada = new String[100];
        int numEntrada = 0;
        String endereco, nome = "";

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            nome = entrada[i];
            i++;
            endereco = entrada[i];
            String html = getHtml(endereco);
            String consoante = escapeHTML2(html);
            html = escapeHTML(html); 
            html = formataString(html);
            extraiDados(html, consoante);
            MyIO.println(" " + nome);
        }
        
        

    }
    
}
