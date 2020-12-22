class TP01Q03{
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String Ciframento(String s, int chave){
        String resp = "";

        for(int i = 0; i < s.length(); i++){
            resp += (char) (s.charAt(i) + chave);
        }

        return resp;
    }

    public static void main(String args[]){
        String[] entrada = new String[1000];
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            MyIO.println(Ciframento(entrada[i], 3));
        }
    }
}
