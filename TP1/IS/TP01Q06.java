
class TP01Q06{
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isVogal(String s){
        boolean resp = true;
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != 'A' && s.charAt(i) != 'a' && s.charAt(i) != 'E' && s.charAt(i) != 'e' && s.charAt(i) != 'I' && s.charAt(i) != 'i' && s.charAt(i) != 'O' && s.charAt(i) != 'o' &&
            s.charAt(i) != 'U' && s.charAt(i) != 'u'){
                resp = false;
                i = s.length();
            }
        }
        return resp;
    }

    public static boolean isConsoante(String s){
        boolean resp = true;
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'A' || s.charAt(i) == 'a' || s.charAt(i) == 'E' || s.charAt(i) == 'e' || s.charAt(i) == 'I' || s.charAt(i) == 'i' || s.charAt(i) == 'O' || s.charAt(i) == 'o' ||
            s.charAt(i) == 'U' || s.charAt(i) == 'u'){
                resp = false;
                i = s.length();
            }else if(s.charAt(i) >= '0' || s.charAt(i) <= '9')
                resp = false;
        }
        return resp;
    }

    public static boolean isInteiro(String s){
        boolean resp = true;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) < '0' || s.charAt(i) > '9'){
                resp = false;
                i = s.length();
            }else if(s.charAt(i) - (int)s.charAt(i) != 0)
            resp = false;
        }
        return resp;
    }

    public static boolean isReal(String s){
        boolean resp = true;
        int aux = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) < '0' || s.charAt(i) > '9'){
                if(s.charAt(i) == '.' || s.charAt(i) == ',')
                    aux++;
                else{
                    resp = false;
                    i = s.length();
                }
            }    
        } 
        if(aux > 1){
            resp = false;
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
            if(isVogal(entrada[i]) == true)
               MyIO.print("SIM ");
            else
               MyIO.print("NAO ");

            if(isConsoante(entrada[i]) == true)
               MyIO.print("SIM ");
            else
               MyIO.print("NAO ");
               
            if(isInteiro(entrada[i]) == true)
               MyIO.print("SIM ");
            else
               MyIO.print("NAO ");
               
            if(isReal(entrada[i]) == true)
               MyIO.println("SIM ");
            else
               MyIO.println("NAO ");   
        }
    }
}
