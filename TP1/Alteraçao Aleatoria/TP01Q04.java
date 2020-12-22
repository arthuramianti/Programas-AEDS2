import java.util.Random;

class TP01Q04{
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String alteraString(String s, char a, char b){
        String resp = "";

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == a){
                resp += b;
            }
            else
                resp += s.charAt(i);
        }

        return resp;
    }

    public static void main(String args[]){
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Random gerador = new Random();
        gerador.setSeed(4);

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            char letraGerada = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            char letraGerada2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            MyIO.println(alteraString(entrada[i], letraGerada, letraGerada2));
        }

    }
}
