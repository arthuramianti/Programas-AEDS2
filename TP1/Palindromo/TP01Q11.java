class TP01Q11{
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String s){
        return (isPalindromo(s, 0));
    }

    public static boolean isPalindromo(String s, int i){
        boolean resp = true;

        if(i < s.length()/2){
            if(s.charAt(i) != s.charAt(s.length()-1-i)){
                resp = false;
                i = s.length();
            }else 
                resp = isPalindromo(s, i+1);             
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
            if(isPalindromo(entrada[i]) == true)
                MyIO.println("SIM");
            else
                MyIO.println("NAO");    
        }
    }
}
